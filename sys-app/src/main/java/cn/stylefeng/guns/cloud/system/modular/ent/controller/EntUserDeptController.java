package cn.stylefeng.guns.cloud.system.modular.ent.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntUserDeptParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntUserDeptResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntUserDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * 用户部门关联表控制器
 *
 * @author stylefeng
 * @Date 2019-10-10 22:44:17
 */
@RestController
@ApiResource(name = "用户部门关联表管理", path = "/entUserDept")
@Api(tags = "用户部门关联表")
public class EntUserDeptController {


    @Autowired
    private EntUserDeptService entUserDeptService;

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "添加", path = "/add")
    @ApiOperation("新增")
    public ResponseData add(@RequestBody EntUserDeptParam param) {
        entUserDeptService.add(param);
        return ResponseData.success();
    }

    /**
     * 修改
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "修改", path = "/update")
    @ApiOperation("修改")
    public ResponseData update(@RequestBody EntUserDeptParam param) {
        entUserDeptService.update(param);
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
    public ResponseData delete(@RequestBody EntUserDeptParam param) {
        entUserDeptService.delete(param);
        return ResponseData.success();
    }

    /**
     * 查询单条详情
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "查询详情", path = "/queryDetail")
    @ApiOperation(value = "查询详情", response = EntUserDeptResult.class)
    public ResponseData queryDetail(@RequestBody EntUserDeptParam param) {
        EntUserDeptResult result = entUserDeptService.findBySpec(param);
        return ResponseData.success(result);
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "查询列表", path = "/queryList")
    @ApiOperation(value = "查询列表", response = EntUserDeptResult.class)
    public ResponseData queryList(@RequestBody EntUserDeptParam param) {
        List<EntUserDeptResult> listBySpec = entUserDeptService.findListBySpec(param);
        return ResponseData.success(listBySpec);
    }

    /**
     * 分页查询列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "分页查询列表", path = "/queryListPage")
    @ApiOperation(value = "分页查询列表", response = EntUserDeptResult.class)
    public ResponseData queryListPage(@RequestBody EntUserDeptParam param) {
        PageResult<EntUserDeptResult> pageBySpec = entUserDeptService.findPageBySpec(param);
        return ResponseData.success(pageBySpec);
    }

}
