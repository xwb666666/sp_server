package cn.stylefeng.guns.cloud.system.modular.ent.service;

import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompApp;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompany;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntCompanyParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntCompanyResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业信息 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntCompanyService extends IService<EntCompany> {

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void add(EntCompanyParam param);

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void delete(EntCompanyParam param);

    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void update(EntCompanyParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    PageResult<EntCompanyResult> findPageBySpec(EntCompanyParam param);

    /**
     * 获取公司详情
     *
     * @return
     * @author stylefeng
     * @date 2019/10/12
     */
    EntCompany detail(Long companyId);

    /**
     * 启用禁用
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    void changeStatus(Long companyId, StatusEnum statusEnum);

    /**
     * 获取公司树列表
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    List<Map<String, Object>> queryCompTree();

    /**
     * 保存公司配置的应用
     *
     * @param params
     * @author stylefeng
     * @date 2019/10/28
     */
    void saveCompApp(List<EntCompApp> params);

    /**
     * 获取应用列表
     *
     * @author stylefeng
     * @date 2019/10/29
     */
    List<Map<String, Object>> getAppList(Long companyId);
}
