package cn.stylefeng.guns.cloud.system.modular.ent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompApp;
import cn.stylefeng.guns.cloud.system.modular.ent.mapper.EntCompAppMapper;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntCompAppParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntCompAppResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntCompAppService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 企业应用配置 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
@Service
public class EntCompAppServiceImpl extends ServiceImpl<EntCompAppMapper, EntCompApp> implements EntCompAppService {

    @Override
    public void add(EntCompAppParam param) {
        EntCompApp entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(EntCompAppParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(EntCompAppParam param) {
        EntCompApp oldEntity = getOldEntity(param);
        EntCompApp newEntity = getEntity(param);
        BeanUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public EntCompAppResult findBySpec(EntCompAppParam param) {
        return null;
    }

    @Override
    public List<EntCompAppResult> findListBySpec(EntCompAppParam param) {
        return null;
    }

    @Override
    public PageResult<EntCompAppResult> findPageBySpec(EntCompAppParam param) {
        Page pageContext = getPageContext();
        IPage<EntCompAppResult> page = this.baseMapper.customPageList(pageContext, param);
        return new PageResult<>(page);
    }

    private Serializable getKey(EntCompAppParam param) {
        return param.getId();
    }

    private Page getPageContext() {
        return PageFactory.defaultPage();
    }

    private EntCompApp getOldEntity(EntCompAppParam param) {
        return this.getById(getKey(param));
    }

    private EntCompApp getEntity(EntCompAppParam param) {
        EntCompApp entity = new EntCompApp();
        BeanUtil.copyProperties(param, entity);
        return entity;
    }

}
