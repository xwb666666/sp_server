package cn.stylefeng.guns.cloud.system.modular.ent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.enums.YesOrNotEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntUserDept;
import cn.stylefeng.guns.cloud.system.modular.ent.mapper.EntUserDeptMapper;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntUserDeptParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntUserDeptResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntUserDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户部门关联表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
@Service
public class EntUserDeptServiceImpl extends ServiceImpl<EntUserDeptMapper, EntUserDept> implements EntUserDeptService {

    @Override
    public void add(EntUserDeptParam param) {
        EntUserDept entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(EntUserDeptParam param) {

    }

    @Override
    public void update(EntUserDeptParam param) {

    }

    @Override
    public EntUserDeptResult findBySpec(EntUserDeptParam param) {
        return null;
    }

    @Override
    public List<EntUserDeptResult> findListBySpec(EntUserDeptParam param) {
        return null;
    }

    @Override
    public PageResult<EntUserDeptResult> findPageBySpec(EntUserDeptParam param) {
        Page pageContext = getPageContext();
        IPage<EntUserDeptResult> page = this.baseMapper.customPageList(pageContext, param);
        return new PageResult<>(page);
    }

    @Override
    public EntUserDept getUserDefaultDept(Long userId) {
        return this.getOne(
                new QueryWrapper<EntUserDept>()
                        .eq("default_flag", YesOrNotEnum.Y.name())
                        .and(i -> i.eq("user_id", userId)));
    }

    private Page getPageContext() {
        return PageFactory.defaultPage();
    }

    private EntUserDept getEntity(EntUserDeptParam param) {
        EntUserDept entity = new EntUserDept();
        BeanUtil.copyProperties(param, entity);
        return entity;
    }

}
