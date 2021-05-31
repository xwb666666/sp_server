package cn.stylefeng.guns.cloud.system.modular.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.libs.web.base.BaseController;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysPermission;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysPermissionParam;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysPermissionResourceService;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysPermissionService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 权限控制器
 *
 * @author fengshuonan
 * @Date 2019/9/26 22:50
 */
@RestController
@ApiResource(name = "权限控制", path = "/permission")
@Api(tags = "权限管理接口")
public class PermissionController extends BaseController {

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysPermissionResourceService permissionResourceService;

    /**
     * 获取所有权限列表
     *
     * @author fengshuonan
     * @Date 2019/9/26 22:50
     */
    @GetResource(name = "获取所有权限列表", path = "/getPermissionList")
    @ApiOperation("获取所有权限列表")
    public ResponseData getPermissionList(
            @RequestParam("appId") Long appId,
            @RequestParam("status") Integer status) {
        List<Map<String, Object>> permissionList = permissionService.getPermissionList(appId, status);
        return ResponseData.success(permissionList);
    }

    /**
     * 分页获取权限列表
     *
     * @author fengshuonan
     * @Date 2019/9/26 22:50
     */
    @GetResource(name = "分页获取权限列表", path = "/getPermissionPageList")
    @ApiOperation("分页获取权限列表")
    public ResponseData getPermissionPageList(
            @RequestParam(value = "appId", required = false) Long appId,
            @RequestParam(value = "permissionName", required = false) String permissionName,
            @RequestParam(value = "status", required = false) Integer status) {

        Page permissionPageList = permissionService.getPermissionPageList(PageFactory.defaultPage(), appId,
                permissionName, status);
        return ResponseData.success(permissionPageList);
    }

    /**
     * 新增权限
     *
     * @author fengshuonan
     * @Date 2019/9/26 22:50
     */
    @PostResource(name = "新增权限", path = "/add")
    @ApiOperation("新增权限")
    public ResponseData addPermission(
            @RequestBody SysPermissionParam sysPermissionParam) {
        SysPermission parse = BeanUtil.toBean(sysPermissionParam, SysPermission.class);
        Long permissionId = permissionService.addPermission(parse);
        return ResponseData.success(permissionId);
    }

    /**
     * 更改权限状态
     * <p>
     * TODO TEST
     *
     * @author fengshuonan
     * @Date 2019/9/26 22:50
     */
    @PostResource(name = "更改权限状态", path = "/setPermissionStatus")
    @ApiOperation("更改权限状态")
    public ResponseData setPermissionStatus(@RequestBody JSONObject jsonObject) {
        List<Long> permissionIds = jsonObject.getJSONArray("permissionIds").toJavaList(Long.class);
        Integer status = jsonObject.getInteger("status");
        return ResponseData.success(permissionService.setPermissionStatus(permissionIds, StatusEnum.toEnum(status)));
    }

    /**
     * 分级加载权限
     *
     * @author fengshuonan
     * @Date 2019/9/26 22:50
     */
    @GetResource(name = "分级加载权限", path = "/getPermissionList4Level")
    @ApiOperation("分级加载权限")
    public ResponseData getPermissionList4Level(
            @RequestParam("appId") Long appId,
            @RequestParam(value = "parentId", required = false) Long parentId,
            @RequestParam("status") Integer status) {
        List<Map<String, Object>> permissionList4Level = permissionService.getPermissionList4Level(appId, parentId,
                status);
        return ResponseData.success(permissionList4Level);
    }

    /**
     * 权限绑定资源
     * <p>
     * TODO TEST
     *
     * @author fengshuonan
     * @Date 2019/9/26 22:50
     */
    @PostResource(name = "权限绑定资源", path = "/permissionBindResource")
    @ApiOperation("权限绑定资源")
    public ResponseData permissionBindResource(@RequestBody JSONObject jsonObject) {
        Long permissionId = jsonObject.getLong("permissionId");
        List<String> resourceIds = jsonObject.getJSONArray("resourceIds").toJavaList(String.class);
        return ResponseData.success(permissionResourceService.permissionBindResource(permissionId, resourceIds));
    }

    /**
     * 解除权限绑定资源
     * <p>
     * TODO TEST
     *
     * @author fengshuonan
     * @Date 2019/9/26 22:51
     */
    @PostResource(name = "解除权限绑定资源", path = "/cancelPermissionBindResource")
    @ApiOperation("解除权限绑定资源")
    public ResponseData cancelPermissionBindResource(@RequestBody JSONObject jsonObject) {
        Long permissionId = jsonObject.getLong("permissionId");
        List<String> resourceIds = jsonObject.getJSONArray("resourceIds").toJavaList(String.class);
        return ResponseData.success(permissionResourceService.cancelPermissionBindResource(permissionId, resourceIds));
    }

    /**
     * 获取权限绑定的资源
     *
     * @author fengshuonan
     * @Date 2019/9/26 22:51
     */
    @GetResource(name = "获取权限绑定的资源", path = "/getPermissionResourceList")
    @ApiOperation("获取权限绑定的资源")
    public ResponseData getPermissionResourceList(@RequestParam("permissionId") Long permissionId) {
        List<Map<String, Object>> resourceList = permissionResourceService.getPermissionBindResourceList(permissionId);
        return ResponseData.success(resourceList);
    }

    /**
     * 获取权限资源的关系
     *
     * @author fengshuonan
     * @Date 2019-09-12 09:31
     */
    @GetResource(name = "获取权限资源的关系", path = "/getPermissionResourceRelation")
    @ApiOperation("获取权限资源的关系")
    public ResponseData getPermissionResourceListDetail(
            @RequestParam("appId") Long appId,
            @RequestParam("permissionId") Long permissionId) {
        Map<String, Object> result = permissionResourceService.getPermissionResource(appId, permissionId);
        return ResponseData.success(result);
    }

    /**
     * 获取权限树
     *
     * @author fengshuonan
     * @Date 2019/9/26 22:51
     */
    @GetResource(name = "获取权限树", path = "/getPermissionTree")
    @ApiOperation("获取权限树")
    public ResponseData getPermissionTree(
            @RequestParam("appId") Long appId) {
        List<Map<String, Object>> permissionTree = permissionService.getPermissionTree(appId);
        return ResponseData.success(permissionTree);
    }

    /**
     * 获取权限资源列表
     *
     * @author fengshuonan
     * @Date 2019/9/26 22:51
     */
    @GetResource(name = "获取权限资源列表", path = "/getResourceList4Permission")
    @ApiOperation("获取权限资源列表")
    public ResponseData getResourceList4Permission(
            @RequestParam("appId") Long appId,
            @RequestParam("permissionId") Long permissionId) {
        Map<String, Object> result = permissionResourceService.getResourceList4Permission(appId, permissionId);
        return ResponseData.success(result);
    }

    /**
     * 权限删除
     *
     * @author stylefeng
     * @date 2019/10/27
     */
    @GetResource(name = "权限删除", path = "/removePermission", requiredPermission = false)
    @ApiOperation("权限删除")
    public ResponseData removePermission(
            @RequestParam("permissionId") Long permissionId) {
        this.permissionService.removePermission(permissionId);
        return ResponseData.success();
    }

    /**
     * 获取应用按钮状态(当前登录人)
     *
     * @author stylefeng
     * @date 2019/11/3
     */
    @GetResource(name = "获取应用按钮状态", path = "/getAppButtonStatus", requiredPermission = false)
    @ApiOperation("获取应用按钮状态")
    public ResponseData getAppButtonStatus(@RequestParam("appCode") String appCode) {
        return ResponseData.success(this.permissionService.getAppButtonStatus(appCode));
    }

}
