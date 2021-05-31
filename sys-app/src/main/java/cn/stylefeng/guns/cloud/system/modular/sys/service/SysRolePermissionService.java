package cn.stylefeng.guns.cloud.system.modular.sys.service;

import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysRolePermission;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysRolePermissionParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysRolePermissionResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    /**
     * 新增
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void add(SysRolePermissionParam param);

    /**
     * 删除
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void delete(SysRolePermissionParam param);

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void update(SysRolePermissionParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    SysRolePermissionResult findBySpec(SysRolePermissionParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    List<SysRolePermissionResult> findListBySpec(SysRolePermissionParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    PageResult findPageBySpec(SysRolePermissionParam param);

}
