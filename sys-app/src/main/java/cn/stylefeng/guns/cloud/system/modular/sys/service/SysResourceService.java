package cn.stylefeng.guns.cloud.system.modular.sys.service;

import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.resource.ResourceDefinition;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysResource;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.ResourceQueryParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysResourceParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysResourceResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
public interface SysResourceService extends IService<SysResource> {

    /**
     * 新增
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void add(SysResourceParam param);

    /**
     * 删除
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void delete(SysResourceParam param);

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void update(SysResourceParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    SysResourceResult findBySpec(SysResourceParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    List<SysResourceResult> findListBySpec(SysResourceParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    PageResult findPageBySpec(SysResourceParam param);

    /**
     * 删除某个应用的所有资源
     *
     * @author fengshuonan
     * @Date 2019-05-29 13:54
     */
    @Deprecated
    void deleteResourceByAppCode(String appCode);

    /**
     * 删除某个项目的所有资源
     *
     * @author fengshuonan
     * @Date 2020/3/12 23:22
     */
    void deleteResourceByProjectCode(String projectCode);

    /**
     * 保存资源
     *
     * @author fengshuonan
     * @Date 2019-05-29 13:54
     */
    void saveResource(ResourceDefinition resourceDefinition);

    /**
     * 批量保存资源
     *
     * @author fengshuonan
     * @Date 2019-05-29 13:54
     */
    void saveResourceList(List<SysResource> resourceList);

    /**
     * 根据资源编码查询资源
     *
     * @author fengshuonan
     * @Date 2019-05-29 13:54
     */
    SysResource getResourceByCode(String code);

    /**
     * 根据appCode获取资源
     *
     * @author fengshuonan
     * @Date 2019-05-29 13:38
     */
    List<SysResource> getResourceByAppCode(String appCode);

    /**
     * 获取资源列表
     *
     * @author fengshuonan
     * @Date 2019-05-29 13:38
     */
    Page getResourceList(ResourceQueryParam param);

    /**
     * 通过应用id获取获取资源下拉菜单列表
     *
     * @author fengshuonan
     * @since 2018-02-07
     */
    List<Map<String, Object>> getResourceSelectList(Long appId);

    /**
     * 获取资源
     *
     * @author fengshuonan
     * @Date 2019-05-29 13:38
     */
    List<Map<String, Object>> getResourceList4Permission(Long appId);

    /**
     * 获取资源code对应的资源url
     *
     * @author fengshuonan
     * @Date 2019-05-29 13:39
     */
    Set<String> getResourceUrls(Set<String> resCodes);

}
