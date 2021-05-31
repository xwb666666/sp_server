package cn.stylefeng.guns.cloud.system.modular.ent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntUserAccount;
import cn.stylefeng.guns.cloud.system.modular.ent.mapper.EntUserAccountMapper;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntUserAccountParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntUserAccountResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntUserAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 登录账号 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
@Service
public class EntUserAccountServiceImpl extends ServiceImpl<EntUserAccountMapper, EntUserAccount> implements EntUserAccountService {

    @Override
    public void add(EntUserAccountParam param) {
        EntUserAccount entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(EntUserAccountParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(EntUserAccountParam param) {
        EntUserAccount oldEntity = getOldEntity(param);
        EntUserAccount newEntity = getEntity(param);
        BeanUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public EntUserAccountResult findBySpec(EntUserAccountParam param) {
        return null;
    }

    @Override
    public List<EntUserAccountResult> findListBySpec(EntUserAccountParam param) {
        return null;
    }

    @Override
    public PageResult<EntUserAccountResult> findPageBySpec(EntUserAccountParam param) {
        Page pageContext = getPageContext();
        IPage<EntUserAccountResult> page = this.baseMapper.customPageList(pageContext, param);
        return new PageResult<>(page);
    }

    @Override
    public EntUserAccount getAccountInfoByAccount(String account) {
        return this.getOne(
                new QueryWrapper<EntUserAccount>().eq("account", account));
    }

    private Serializable getKey(EntUserAccountParam param) {
        return param.getAccountId();
    }

    private Page getPageContext() {
        return PageFactory.defaultPage();
    }

    private EntUserAccount getOldEntity(EntUserAccountParam param) {
        return this.getById(getKey(param));
    }

    private EntUserAccount getEntity(EntUserAccountParam param) {
        EntUserAccount entity = new EntUserAccount();
        BeanUtil.copyProperties(param, entity);
        return entity;
    }

}
