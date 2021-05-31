package cn.stylefeng.guns.cloud.system.modular.ent.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.libs.validator.stereotype.ParamValidator;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.*;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntUserQueryResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 用户信息控制器
 *
 * @author stylefeng
 * @Date 2019-10-10 22:44:17
 */
@RestController
@ApiResource(name = "用户信息管理", path = "/entUser")
@Api(tags = "用户信息")
public class EntUserController {

    @Autowired
    private EntUserService entUserInfoService;

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "添加", path = "/add")
    @ApiOperation("新增")
    @ParamValidator
    public ResponseData add(@RequestBody EntUserParam param) {
        entUserInfoService.add(param);
        return ResponseData.success();
    }

    /**
     * 人员详情接口
     *
     * @author stylefeng
     * @date 2019/10/15
     */
    @GetResource(name = "详情", path = "/detail")
    @ApiOperation("详情")
    public ResponseData detail(@RequestParam Long userId) {
        return ResponseData.success(this.entUserInfoService.detail(userId));

    }

    /**
     * 修改
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "修改", path = "/update")
    @ApiOperation("修改")
    @ParamValidator
    public ResponseData update(@RequestBody EntUserParam param) {
        entUserInfoService.update(param);
        return ResponseData.success();
    }

    /**
     * 禁用启用
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    @GetResource(name = "禁用启用", path = "/changeStatus")
    @ApiOperation("禁用启用")
    public ResponseData changeStatus(@RequestParam Long userId, @RequestParam Integer status) {
        this.entUserInfoService.changeStatus(userId, StatusEnum.toEnum(status));
        return ResponseData.success();
    }

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "删除", path = "/delete")
    @ApiOperation("删除")
    public ResponseData delete(@RequestBody EntUserParam param) {
        entUserInfoService.delete(param);
        return ResponseData.success();
    }

    /**
     * 分页查询列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @GetResource(name = "分页查询列表", path = "/queryListPage")
    @ApiOperation(value = "分页查询列表", response = EntUserQueryResult.class)
    public ResponseData queryListPage(EntUserQueryParam param) {
        PageResult<EntUserQueryResult> pageBySpec = entUserInfoService.findPageBySpec(param);
        return ResponseData.success(pageBySpec);
    }

    /**
     * 获取用户在字典表对应的信息
     *
     * @author stylefeng
     * @date 2019/10/14
     */
    @GetResource(name = "获取用户在字典表对应的信息", path = "/queryInfoByDictType")
    @ApiOperation(value = "获取用户在字典表对应的信息")
    public ResponseData queryInfoByDictType(@RequestParam String dictTypeCode) {
        return ResponseData.success(this.entUserInfoService.queryInfoByDictType(dictTypeCode));
    }

    /**
     * 获取公司下拉列表
     *
     * @author stylefeng
     * @date 2019/10/27
     */
    @GetResource(name = "获取公司下拉列表", path = "/getCompanySelect", requiredPermission = false)
    @ApiOperation(value = "获取公司下拉列表")
    public ResponseData getCompanySelect() {
        return ResponseData.success(this.entUserInfoService.getCompanySelect());
    }

    /**
     * 获取部门下拉列表
     *
     * @author stylefeng
     * @date 2019/10/27
     */
    @GetResource(name = "获取部门下拉列表", path = "/getDeptSelect", requiredPermission = false)
    @ApiOperation(value = "获取部门下拉列表")
    public ResponseData getDeptSelect(@RequestParam Long companyId) {
        return ResponseData.success(this.entUserInfoService.getDeptSelect(companyId));
    }

    /**
     * 获取职务下拉列表
     *
     * @author stylefeng
     * @date 2019/10/27
     */
    @GetResource(name = "获取职务下拉列表", path = "/getDutySelect", requiredPermission = false)
    @ApiOperation(value = "获取职务下拉列表")
    public ResponseData getDutySelect() {
        return ResponseData.success(this.entUserInfoService.getDutySelect());
    }

    /**
     * 获取所有的角色信息
     *
     * @author stylefeng
     * @date 2019/10/27
     */
    @GetResource(name = "获取所有的角色信息", path = "/getRoles", requiredPermission = false)
    @ApiOperation(value = "获取所有的角色信息")
    public ResponseData getRoles(@RequestParam Long userId) {
        return ResponseData.success(this.entUserInfoService.getRoles(userId));
    }

    /**
     * 保存人员配置好的角色
     *
     * @author stylefeng
     * @date 2019/10/27
     */
    @PostResource(name = "保存人员配置好的角色", path = "/saveRoles", requiredPermission = false)
    @ApiOperation(value = "保存人员配置好的角色")
    @ParamValidator
    public ResponseData saveRoles(@RequestBody EntUserRoleParam param) {
        this.entUserInfoService.saveRoles(param);
        return ResponseData.success();
    }

    /**
     * 获取当前登录人信息
     *
     * @author stylefeng
     * @date 2019/10/30
     */
    @GetResource(name = "获取当前登录人信息", path = "/getCurrentUser", requiredPermission = false)
    @ApiOperation(value = "获取当前登录人信息")
    public ResponseData getCurrentUser() {
        return ResponseData.success(this.entUserInfoService.getCurrentUser());
    }

    /**
     * 保存当前登录人的信息
     *
     * @author stylefeng
     * @date 2019/10/30
     */
    @PostResource(name = "保存当前登录人的信息", path = "/saveCurrentUser", requiredPermission = false)
    @ApiOperation(value = "保存当前登录人的信息")
    @ParamValidator
    public ResponseData saveCurrentUser(@RequestBody EntCurrentParam param) {
        this.entUserInfoService.saveCurrentUser(param);
        return ResponseData.success();
    }

    /**
     * 修改密码
     *
     * @author stylefeng
     * @date 2019/10/30
     */
    @PostResource(name = "修改密码", path = "/updatePassword", requiredPermission = false)
    @ApiOperation(value = "修改密码")
    @ParamValidator
    public ResponseData updatePassword(@RequestBody EntPasswordParam passwordParam) {
        this.entUserInfoService.updatePassword(passwordParam);
        return ResponseData.success();
    }

    /**
     * 重置密码
     *
     * @author stylefeng
     * @date 2019/10/30
     */
    @GetResource(name = "重置密码", path = "/resetPassword", requiredPermission = false)
    @ApiOperation(value = "重置密码")
    @ParamValidator
    public ResponseData resetPassword(@RequestParam Long uerId) {
        return ResponseData.success(this.entUserInfoService.resetPassword(uerId));
    }


}
