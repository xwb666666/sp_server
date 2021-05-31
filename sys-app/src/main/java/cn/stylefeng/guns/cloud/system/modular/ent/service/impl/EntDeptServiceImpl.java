package cn.stylefeng.guns.cloud.system.modular.ent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.exp.RequestEmptyException;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.core.util.CodeUtil;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompany;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntDept;
import cn.stylefeng.guns.cloud.system.modular.ent.mapper.EntDeptMapper;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntDeptParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntDeptResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntCompanyService;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntDeptService;
import cn.stylefeng.guns.cloud.system.modular.ent.utils.EntUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.stylefeng.guns.cloud.model.enums.StatusEnum.DISABLE;
import static cn.stylefeng.guns.cloud.system.modular.ent.enums.EntException.*;
import static cn.stylefeng.guns.cloud.system.modular.ent.enums.EntNodeTypeEnum.COMPANY;
import static cn.stylefeng.guns.cloud.system.modular.ent.enums.EntNodeTypeEnum.DEPT;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
@Service
public class EntDeptServiceImpl extends ServiceImpl<EntDeptMapper, EntDept> implements EntDeptService {
    @Autowired
    private EntCompanyService companyService;

    @Override
    public void add(EntDeptParam param) {
        EntDept entity = getEntity(param);
        //参数校验
        if (ObjectUtil.isEmpty(entity.getDeptName())
                || ObjectUtil.isEmpty(entity.getDeptShortName())
                || ObjectUtil.isEmpty(entity.getCompanyId())) {
            throw new ServiceException(REQ_PARAM_EXIST_NULL);
        }


        Boolean nullParentId = BeanUtil.isEmpty(entity.getParentId()) ? TRUE : FALSE;
        if (nullParentId) {
            throw new ServiceException(PID_NOT_EXIST);
        }
        //生成反序父ids
        String adversePids = getPids(entity.getParentId(), new StringBuffer());
        entity.setParentIds(EntUtil.transToPids(adversePids));

        //生成编码
        entity.setDeptCode(CodeUtil.getDeptCode(entity.getDeptName()));
        //排序
        entity.setOrderNo(EntUtil.getMaxSort(EntDept.class));
        //默认为启用
        entity.setStatus(StatusEnum.ENABLE.getCode());
        this.save(entity);
    }

    /**
     * 获取父ids
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    private String getPids(Long parentId, StringBuffer pids) {
        EntCompany parentNode = companyService.getById(parentId);
        Boolean pNodeIsCompany = ObjectUtil.isNotEmpty(parentNode) ? TRUE : FALSE;
        if (pNodeIsCompany) {
            return pids.append(parentNode.getCompanyId()).append(",").toString();
        } else {
            pids.append(parentId).append(",");
        }
        EntDept parentComp = this.getById(parentId);
        return getPids(parentComp.getParentId(), pids);
    }

    @Override
    public void delete(EntDeptParam param) {
        this.removeById(getKey(param));
    }

    /**
     * 更新部门
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    @Override
    public void update(EntDeptParam param) {
        EntDept oldEntity = getOldEntity(param);
        EntDept newEntity = getEntity(param);
        BeanUtil.copyProperties(newEntity, oldEntity);
        //更新部门pids
        String reversePids = getPids(oldEntity.getParentId(), new StringBuffer());
        oldEntity.setParentIds(EntUtil.transToPids(reversePids));
        this.updateById(oldEntity);
    }

    /**
     * 获取分页查询列表
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    @Override
    public PageResult<EntDeptResult> findPageBySpec(EntDeptParam param) {
        Page pageContext = getPageContext();
        IPage<EntDeptResult> page = this.baseMapper.customPageList(pageContext, param);
        return new PageResult<>(page);
    }

    /**
     * 获取部门详情
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    @Override
    public EntDeptResult detail(Long deptId) {
        EntDept entity = this.getById(deptId);
        EntDeptResult entDeptResult = new EntDeptResult();
        BeanUtil.copyProperties(entity, entDeptResult);
        return entDeptResult;
    }

    /**
     * 部门禁用启用
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    @Override
    public void changeStatus(Long deptId, StatusEnum statusEnum) {
        if (ObjectUtil.isEmpty(deptId)) {
            throw new RequestEmptyException("deptId为空");
        }
        if (statusEnum == null) {
            throw new RequestEmptyException("状态请求不合法");
        }
        if (statusEnum == DISABLE) {
            //禁用操作
            int sonCount = this.count(new QueryWrapper<EntDept>()
                    .eq("parent_id", deptId)
                    .eq("status", StatusEnum.ENABLE.getCode()));
            if (sonCount != 0) {
                throw new ServiceException(CHECKED_FAILED);
            }
        }
        EntDept dept = this.getById(deptId);
        dept.setStatus(statusEnum.getCode());
        this.updateById(dept);
    }

    /**
     * 获取部门树列表
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    @Override
    public List<Map<String, Object>> queryDeptTree(Long companyId) {
        List<Map<String, Object>> deptTree = this.baseMapper.queryDeptTree(companyId, DEPT.getCode());
        //拼接公司根节点
        EntCompany company = this.companyService.getById(companyId);
        if (ObjectUtil.isEmpty(company)) {
            throw new RequestEmptyException("companyId不存在！");
        }
        Map<String, Object> companyNode = MapBuilder.create(new HashMap<String, Object>())
                .put("nodeId", companyId)
                .put("nodeName", company.getName())
                .put("shortName", company.getShortName())
                .put("nodePid", company.getParentId())
                .put("nodeType", COMPANY.getCode()).build();
        deptTree.add(companyNode);
        return deptTree;
    }

    private Serializable getKey(EntDeptParam param) {
        return param.getDeptId();
    }

    private Page getPageContext() {
        return PageFactory.defaultPage();
    }

    private EntDept getOldEntity(EntDeptParam param) {
        return this.getById(getKey(param));
    }

    private EntDept getEntity(EntDeptParam param) {
        EntDept entity = new EntDept();
        BeanUtil.copyProperties(param, entity);
        return entity;
    }

}
