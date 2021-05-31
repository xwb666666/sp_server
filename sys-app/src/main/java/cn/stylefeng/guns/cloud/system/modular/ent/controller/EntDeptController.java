package cn.stylefeng.guns.cloud.system.modular.ent.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntDept;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntDeptParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntDeptResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 部门表控制器
 *
 * @author stylefeng
 * @Date 2019-10-10 22:44:17
 */
@RestController
@ApiResource(name = "部门表管理", path = "/entDept")
@Api(tags = "部门表")
public class EntDeptController {


    @Autowired
    private EntDeptService entDeptService;

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "添加", path = "/add")
    @ApiOperation("新增")
    public ResponseData add(@RequestBody EntDeptParam param) {
        entDeptService.add(param);
        return ResponseData.success();
    }

    /**
     * 获取部门详情
     *
     * @author stylefeng
     * @date 2019/10/12
     */
    @GetResource(name = "详情", path = "/detail")
    @ApiOperation("详情")
    public ResponseData detail(@RequestParam Long deptId) {
        return ResponseData.success(this.entDeptService.detail(deptId));
    }

    /**
     * 修改
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "修改", path = "/update")
    @ApiOperation("修改")
    public ResponseData update(@RequestBody EntDeptParam param) {
        entDeptService.update(param);
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
    public ResponseData delete(@RequestBody EntDeptParam param) {
        entDeptService.delete(param);
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
    public ResponseData changeStatus(@RequestParam Long deptId, @RequestParam Integer status) {
        this.entDeptService.changeStatus(deptId, StatusEnum.toEnum(status));
        return ResponseData.success();
    }

    /**
     * 分页查询列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @GetResource(name = "分页查询列表", path = "/queryListPage")
    @ApiOperation(value = "分页查询列表", response = EntDept.class)
    public ResponseData queryListPage(EntDeptParam param) {
        PageResult<EntDeptResult> pageBySpec = entDeptService.findPageBySpec(param);
        return ResponseData.success(pageBySpec);
    }

    /**
     * 获取部门树列表
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    @GetResource(name = "获取部门树列表", path = "/queryDeptTree")
    @ApiOperation(value = "获取部门树列表")
    public ResponseData queryDeptTree(@RequestParam Long companyId) {
        return ResponseData.success(this.entDeptService.queryDeptTree(companyId));
    }

}
