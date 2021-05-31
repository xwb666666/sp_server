package cn.stylefeng.guns.cloud.system.modular.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysUserRole;
import cn.stylefeng.guns.cloud.system.modular.sys.mapper.SysUserRoleMapper;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.RoleInfo;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysUserRoleParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysUserRoleResult;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public void add(SysUserRoleParam param) {
        SysUserRole entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(SysUserRoleParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(SysUserRoleParam param) {
        SysUserRole oldEntity = getOldEntity(param);
        SysUserRole newEntity = getEntity(param);
        BeanUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public SysUserRoleResult findBySpec(SysUserRoleParam param) {
        return null;
    }

    @Override
    public List<SysUserRoleResult> findListBySpec(SysUserRoleParam param) {
        return null;
    }

    @Override
    public PageResult findPageBySpec(SysUserRoleParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return new PageResult(page);
    }

    @Override
    public List<RoleInfo> getUserRoleInfo(Long accountId) {
        return this.baseMapper.getUserRoleInfo(accountId);
    }

    private Serializable getKey(SysUserRoleParam param) {
        return param.getUserRoleId();
    }

    private Page getPageContext() {
        return PageFactory.defaultPage();
    }

    private SysUserRole getOldEntity(SysUserRoleParam param) {
        return this.getById(getKey(param));
    }

    private SysUserRole getEntity(SysUserRoleParam param) {
        SysUserRole entity = new SysUserRole();
        BeanUtil.copyProperties(param, entity);
        return entity;
    }

}
