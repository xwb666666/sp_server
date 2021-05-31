package cn.stylefeng.guns.cloud.system.modular.tenant.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.DbUtil;
import cn.hutool.db.sql.SqlExecutor;
import cn.stylefeng.guns.cloud.libs.config.properties.DruidProperties;
import cn.stylefeng.guns.cloud.libs.context.SpringContext;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.system.core.dbs.context.DataSourceContext;
import cn.stylefeng.guns.cloud.system.core.tenant.exception.TenantException;
import cn.stylefeng.guns.cloud.system.core.tenant.exception.enums.TenantExceptionEnum;
import cn.stylefeng.guns.cloud.system.core.tenant.util.DatabaseUtil;
import cn.stylefeng.guns.cloud.system.core.tenant.util.SqlRunUtil;
import cn.stylefeng.guns.cloud.system.modular.dbs.entity.DatabaseInfo;
import cn.stylefeng.guns.cloud.system.modular.dbs.param.DatabaseInfoParam;
import cn.stylefeng.guns.cloud.system.modular.dbs.service.DatabaseInfoService;
import cn.stylefeng.guns.cloud.system.modular.ent.utils.SaltUtil;
import cn.stylefeng.guns.cloud.system.modular.tenant.entity.TenantInfo;
import cn.stylefeng.guns.cloud.system.modular.tenant.factory.DataBaseInfoFactory;
import cn.stylefeng.guns.cloud.system.modular.tenant.mapper.TenantInfoMapper;
import cn.stylefeng.guns.cloud.system.modular.tenant.params.TenantInfoParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static cn.stylefeng.guns.cloud.model.consts.TenantConstants.INIT_SQL_FILE_NAME;
import static cn.stylefeng.guns.cloud.model.consts.TenantConstants.TENANT_DB_PREFIX;


/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-16
 */
@Service
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper, TenantInfo> implements TenantInfoService {

    /**
     * 更新语句
     */
    public static final String UPDATE_SQL = "update guns_ent_user_account set `password` = '{}' , salt = '{}' where `account` = 'admin'";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(TenantInfoParam param) {

        // 创建租户数据库
        DruidProperties druidProperties = SpringContext.getBean(DruidProperties.class);
        String databaseName = TENANT_DB_PREFIX + param.getCode();
        DatabaseUtil.createDatabase(druidProperties, databaseName);

        // 创建租户的数据源记录
        DatabaseInfoService databaseInfoService;
        try {
            databaseInfoService = SpringContext.getBean(DatabaseInfoService.class);
        } catch (Exception e) {
            throw new TenantException(TenantExceptionEnum.DBS_MODULAR_NOT_ENABLE_ERROR);
        }
        DatabaseInfoParam dataBaseInfo = DataBaseInfoFactory.createDataBaseInfo(druidProperties, databaseName);
        databaseInfoService.add(dataBaseInfo);

        // 初始化租户的数据库
        SqlRunUtil.runClassPathSql(INIT_SQL_FILE_NAME, databaseName);

        // 插入租户记录
        TenantInfo tenantInfo = new TenantInfo();
        BeanUtil.copyProperties(param, tenantInfo);
        tenantInfo.setDbName(databaseName);
        this.save(tenantInfo);

        // 切换数据源到新的租户,初始化新租户的用户名和密码
        String newSalt = SaltUtil.getRandomSalt();
        String newPassword = SaltUtil.md5Encrypt(param.getAdminPassword(), newSalt);
        DataSource dataSource = DataSourceContext.getDataSources().get(databaseName);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String sql = StrUtil.format(UPDATE_SQL, newPassword, newSalt);
            SqlExecutor.execute(connection, sql);
        } catch (SQLException e) {
            log.error(">>> 更新多租户的用户密码错误！", e);
            throw new TenantException(TenantExceptionEnum.UPDATE_TENANT_PASSWORD_ERROR);
        } finally {
            DbUtil.close(connection);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(TenantInfoParam param) {

        // 获取租户信息
        TenantInfo tenantInfo = this.getById(param.getId());
        if (tenantInfo == null) {
            return;
        }

        // 删除租户信息
        this.removeById(param.getId());

        // 删除对应的数据源
        DatabaseInfoService databaseInfoService;
        try {
            databaseInfoService = SpringContext.getBean(DatabaseInfoService.class);
        } catch (Exception e) {
            throw new TenantException(TenantExceptionEnum.DBS_MODULAR_NOT_ENABLE_ERROR);
        }

        // 删除对应的数据源信息
        LambdaQueryWrapper<DatabaseInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DatabaseInfo::getDbName, tenantInfo.getDbName());
        databaseInfoService.remove(queryWrapper);

        // 删除数据库
        DruidProperties druidProperties = SpringContext.getBean(DruidProperties.class);
        DatabaseUtil.dropDatabase(druidProperties, tenantInfo.getDbName());

        // 删除容器中的数据源记录
        DataSourceContext.removeDataSource(tenantInfo.getDbName());
    }

    @Override
    public void update(TenantInfoParam param) {

        // 找不到租户信息
        TenantInfo tenantInfo = this.getById(param.getId());
        if (tenantInfo == null) {
            throw new TenantException(TenantExceptionEnum.CNAT_FIND_TENANT_ERROR);
        }

        BeanUtil.copyProperties(param, tenantInfo);

        this.updateById(tenantInfo);
    }

    @Override
    public Page<TenantInfo> page(TenantInfoParam param) {
        LambdaQueryWrapper<TenantInfo> queryWrapper = new LambdaQueryWrapper<>();

        if (ObjectUtil.isNotNull(param)) {
            //根据名称模糊查询
            if (ObjectUtil.isNotEmpty(param.getName())) {
                queryWrapper.like(TenantInfo::getName, param.getName());
            }
        }
        return this.page(PageFactory.defaultPage(), queryWrapper);
    }

    @Override
    public TenantInfo getByCode(String code) {
        LambdaQueryWrapper<TenantInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TenantInfo::getCode, code);
        return this.getOne(queryWrapper);
    }

}
