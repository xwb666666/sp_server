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
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompApp;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompany;
import cn.stylefeng.guns.cloud.system.modular.ent.mapper.EntCompanyMapper;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntCompanyParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntCompanyResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntCompAppService;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntCompanyService;
import cn.stylefeng.guns.cloud.system.modular.ent.utils.EntUtil;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysApp;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysAppService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.stylefeng.guns.cloud.model.enums.StatusEnum.DISABLE;
import static cn.stylefeng.guns.cloud.system.modular.ent.common.EntConst.ROOT_ID;
import static cn.stylefeng.guns.cloud.system.modular.ent.common.EntConst.ROOT_PID;
import static cn.stylefeng.guns.cloud.system.modular.ent.enums.EntException.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
@Service
public class EntCompanyServiceImpl extends ServiceImpl<EntCompanyMapper, EntCompany> implements EntCompanyService {

    @Autowired
    private EntCompAppService compAppService;

    @Autowired
    private SysAppService sysAppService;

    /**
     * ????????????
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    @Override
    public void add(EntCompanyParam param) {
        EntCompany entity = getEntity(param);

        //????????????
        if (ObjectUtil.isEmpty(entity.getName()) || ObjectUtil.isEmpty(entity.getShortName())) {
            throw new ServiceException(REQ_PARAM_EXIST_NULL);
        }
        Boolean nullParentId = ObjectUtil.isEmpty(entity.getParentId()) ? TRUE : FALSE;
        if (nullParentId) {
            throw new ServiceException(PID_NOT_EXIST);
        }

        //???????????????ids
        String adversePids = getPids(entity.getParentId(), new StringBuffer());
        entity.setParentIds(EntUtil.transToPids(adversePids));

        //????????????
        entity.setCpCode(CodeUtil.getEntCode(entity.getName()));

        //??????
        entity.setOrderNo(EntUtil.getMaxSort(EntCompany.class));

        //???????????????
        entity.setStatus(StatusEnum.ENABLE.getCode());
        this.save(entity);
    }

    /**
     * ?????????ids
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    private String getPids(Long parentId, StringBuffer pids) {
        if (parentId.equals(ROOT_ID)) {
            return pids.append(ROOT_ID).append(",").toString();
        } else {
            pids.append(parentId).append(",");
        }
        EntCompany parentComp = this.getById(parentId);
        return getPids(parentComp.getParentId(), pids);
    }

    @Override
    public void delete(EntCompanyParam param) {
        this.removeById(getKey(param));
    }

    /**
     * ????????????
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    @Override
    public void update(EntCompanyParam param) {
        EntCompany oldEntity = getOldEntity(param);
        EntCompany newEntity = getEntity(param);
        BeanUtil.copyProperties(newEntity, oldEntity);

        //????????????pids
        String reversePids = getPids(oldEntity.getParentId(), new StringBuffer());
        oldEntity.setParentIds(EntUtil.transToPids(reversePids));
        this.updateById(oldEntity);
    }

    @Override
    public PageResult<EntCompanyResult> findPageBySpec(EntCompanyParam param) {
        Page pageContext = getPageContext();
        IPage<EntCompanyResult> page = this.baseMapper.customPageList(pageContext, param);
        return new PageResult<>(page);
    }

    /**
     * ??????????????????
     *
     * @return
     * @author stylefeng
     * @date 2019/10/12
     */
    @Override
    public EntCompany detail(Long companyId) {
        return this.getById(companyId);
    }

    /**
     * ??????????????????
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    @Override
    public void changeStatus(Long companyId, StatusEnum statusEnum) {
        if (ObjectUtil.isEmpty(companyId)) {
            throw new RequestEmptyException("companyId??????");
        }
        if (statusEnum == null) {
            throw new RequestEmptyException("?????????????????????");
        }
        if (statusEnum == DISABLE) {
            //????????????
            int sonCount = this.count(new QueryWrapper<EntCompany>()
                    .eq("parent_id", companyId)
                    .eq("status", StatusEnum.ENABLE.getCode()));
            if (sonCount != 0) {
                throw new ServiceException(CHECKED_FAILED);
            }
        }
        EntCompany company = this.getById(companyId);
        company.setStatus(statusEnum.getCode());
        this.updateById(company);
    }

    /**
     * ?????????????????????
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    @Override
    public List<Map<String, Object>> queryCompTree() {
        List<Map<String, Object>> compMaps = this.listMaps(new QueryWrapper<EntCompany>()
                .select("company_id as nodeId", "name as nodeName",
                        "short_name as shortName", "parent_id as nodePid")
                .eq("status", StatusEnum.ENABLE.getCode()));

        //???????????????
        Map<String, Object> rootNode = MapBuilder.create(new HashMap<String, Object>())
                .put("nodeId", ROOT_ID)
                .put("nodeName", "??????")
                .put("nodePid", ROOT_PID).build();
        compMaps.add(rootNode);
        return compMaps;
    }

    /**
     * ???????????????????????????
     *
     * @param params
     * @author stylefeng
     * @date 2019/10/28
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCompApp(List<EntCompApp> params) {
        params.forEach(entCompApp -> this.compAppService.save(entCompApp));
    }

    /**
     * ??????????????????
     *
     * @author stylefeng
     * @date 2019/10/29
     */
    @Override
    public List<Map<String, Object>> getAppList(Long companyId) {
        QueryWrapper<SysApp> entityWrapper = new QueryWrapper<>();
        entityWrapper = entityWrapper
                .eq("status", StatusEnum.ENABLE.getCode())
                .select("app_id as appId", "app_name as appName");
        List<Map<String, Object>> appSelect = this.sysAppService.listMaps(entityWrapper);
        List<Long> appIds = this.baseMapper.getCompAppIds(companyId);

        for (Map<String, Object> app : appSelect) {
            if (ObjectUtil.isEmpty(appIds)) {
                app.put("configFlag", "N");
            }
            Long appId = (Long) app.get("appId");
            if (appIds.contains(appId)) {
                app.put("configFlag", "Y");
            } else {
                app.put("configFlag", "N");
            }
        }
        return appSelect;
    }

    private Serializable getKey(EntCompanyParam param) {
        return param.getCompanyId();
    }

    private Page getPageContext() {
        return PageFactory.defaultPage();
    }

    private EntCompany getOldEntity(EntCompanyParam param) {
        return this.getById(getKey(param));
    }

    private EntCompany getEntity(EntCompanyParam param) {
        EntCompany entity = new EntCompany();
        BeanUtil.copyProperties(param, entity);
        return entity;
    }

}
