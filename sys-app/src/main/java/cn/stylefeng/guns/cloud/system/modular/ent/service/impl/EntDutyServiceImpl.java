package cn.stylefeng.guns.cloud.system.modular.ent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.exp.RequestEmptyException;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.core.util.CodeUtil;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntDuty;
import cn.stylefeng.guns.cloud.system.modular.ent.mapper.EntDutyMapper;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntDutyParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntDutyResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntDutyService;
import cn.stylefeng.guns.cloud.system.modular.ent.utils.EntUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static cn.stylefeng.guns.cloud.system.modular.ent.enums.EntException.REQ_PARAM_EXIST_NULL;

/**
 * <p>
 * 职务管理 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
@Service
public class EntDutyServiceImpl extends ServiceImpl<EntDutyMapper, EntDuty> implements EntDutyService {

    /**
     * 职务新增
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    @Override
    public void add(EntDutyParam param) {
        if (ObjectUtil.isEmpty(param.getDutyName())) {
            throw new ServiceException(REQ_PARAM_EXIST_NULL);
        }
        EntDuty entity = getEntity(param);
        //业务编码
        String dutyCode = CodeUtil.getDutyCode(entity.getDutyName());
        entity.setDutyCode(dutyCode);
        //排序码
        if (ObjectUtil.isEmpty(param.getOrderNo())) {
            entity.setOrderNo(EntUtil.getMaxSort(EntDuty.class));
        }
        //默认启用
        if (ObjectUtil.isEmpty(param.getStatus())) {
            entity.setStatus(StatusEnum.ENABLE.getCode());
        }
        this.save(entity);
    }

    @Override
    public void delete(EntDutyParam param) {
        this.removeById(getKey(param));
    }

    /**
     * 职务更新
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    @Override
    public void update(EntDutyParam param) {
        EntDuty oldEntity = getOldEntity(param);
        EntDuty newEntity = getEntity(param);
        BeanUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    /**
     * 获取职务分页列表
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    @Override
    public PageResult<EntDutyResult> findPageBySpec(String dutyName, String dutyCode) {
        Page pageContext = getPageContext();
        IPage<EntDutyResult> page = this.baseMapper.customPageList(pageContext, dutyName, dutyCode);
        return new PageResult<>(page);
    }

    /**
     * 职务启用禁用
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    @Override
    public void changeStatus(Long dutyId, StatusEnum statusEnum) {
        if (ObjectUtil.isEmpty(dutyId)) {
            throw new RequestEmptyException("dutyId为空");
        }
        if (statusEnum == null) {
            throw new RequestEmptyException("状态请求不合法");
        }

        EntDuty duty = this.getById(dutyId);
        duty.setStatus(statusEnum.getCode());
        this.updateById(duty);
    }

    /**
     * 职务不分页列表
     *
     * @author stylefeng
     * @date 2019/10/14
     */
    @Override
    public List<Map<String, Object>> queryList() {
        return this.listMaps(new QueryWrapper<EntDuty>().select("duty_id as dutyId", "duty_name as dutyName")
                .eq("status", StatusEnum.ENABLE.getCode())
                .orderByAsc("order_no"));
    }

    private Serializable getKey(EntDutyParam param) {
        return param.getDutyId();
    }

    private Page getPageContext() {
        return PageFactory.defaultPage();
    }

    private EntDuty getOldEntity(EntDutyParam param) {
        return this.getById(getKey(param));
    }

    private EntDuty getEntity(EntDutyParam param) {
        EntDuty entity = new EntDuty();
        BeanUtil.copyProperties(param, entity);
        return entity;
    }

}
