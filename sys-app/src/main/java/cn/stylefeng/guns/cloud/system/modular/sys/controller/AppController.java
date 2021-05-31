package cn.stylefeng.guns.cloud.system.modular.sys.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysAppParam;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 应用平台控制器
 *
 * @author fengshuonan
 * @Date 2019/9/26 22:49
 */
@RestController
@Api(tags = "应用管理")
@ApiResource(name = "应用平台", path = "/app")
public class AppController {

    @Autowired
    private SysAppService appService;

    /**
     * 获取应用列表
     *
     * @author fengshuonan
     * @Date 2019/10/12 22:04
     */
    @ApiOperation("获取列表")
    @GetResource(path = "/list", name = "获取应用列表")
    public ResponseData list(@RequestParam(required = false, value = "appName") String appName) {
        return ResponseData.success(appService.findPageBySpec(appName));
    }

    /**
     * 新增应用
     *
     * @author fengshuonan
     * @Date 2019/10/12 23:13
     */
    @ApiOperation("新增")
    @PostResource(path = "/add", name = "新增应用")
    public ResponseData add(@RequestBody SysAppParam param) {
        appService.add(param);
        return ResponseData.success();
    }

    /**
     * 应用详情
     *
     * @author fengshuonan
     * @Date 2019/10/12 23:13
     */
    @ApiOperation("详情")
    @GetResource(path = "/detail", name = "应用详情")
    public ResponseData detail(Long appId) {
        return ResponseData.success(appService.detail(appId));
    }

    /**
     * 修改应用
     *
     * @author fengshuonan
     * @Date 2019/10/12 23:13
     */
    @ApiOperation("修改")
    @PostResource(path = "/update", name = "修改应用")
    public ResponseData uptate(@RequestBody SysAppParam param) {
        appService.update(param);
        return ResponseData.success();
    }

    /**
     * 删除应用
     *
     * @author fengshuonan
     * @Date 2019/10/12 23:13
     */
    @ApiOperation("删除")
    @GetResource(path = "/delete", name = "删除应用")
    public ResponseData delete(Long appId) {
        appService.delete(appId);
        return ResponseData.success();
    }

    /**
     * 禁用应用
     *
     * @author fengshuonan
     * @Date 2019/10/12 23:14
     */
    @ApiOperation("修改状态")
    @GetResource(path = "/changeStatus", name = "修改状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用id", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态(1:启用，2:禁用)", required = true, dataType = "int", paramType = "query")})
    public ResponseData changeStatus(@RequestParam("appId") Long appId, @RequestParam("status") Integer status) {
        appService.changeStatus(appId, StatusEnum.toEnum(status));
        return ResponseData.success();
    }

    /**
     * 应用下拉列表
     *
     * @author fengshuonan
     * @Date 2018/2/9 16:40
     */
    @GetResource(name = "应用下拉列表", path = "/getAppSelect")
    @ApiOperation("应用下拉列表")
    public ResponseData getAppSelect() {
        return ResponseData.success(appService.getAppSelect());
    }

    /**
     * 根据当前登录人获取应用下拉列表
     *
     * @author stylefeng
     * @date 2019/11/26
     */
    @GetResource(name = "根据当前登录人获取应用下拉列表", path = "/getAppSelectByCurrentUser")
    @ApiOperation("根据当前登录人获取应用下拉列表")
    public ResponseData getAppSelectByCurrentUser() {
        return ResponseData.success(appService.getAppSelectByCurrentUser());
    }


}
