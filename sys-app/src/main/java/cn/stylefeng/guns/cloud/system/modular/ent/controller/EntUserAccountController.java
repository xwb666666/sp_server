package cn.stylefeng.guns.cloud.system.modular.ent.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntUserAccountParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntUserAccountResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntUserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * 登录账号控制器
 *
 * @author stylefeng
 * @Date 2019-10-10 22:44:17
 */
@RestController
@ApiResource(name = "登录账号管理", path = "/entUserAccount")
@Api(tags = "登录账号")
public class EntUserAccountController {


    @Autowired
    private EntUserAccountService entUserAccountService;

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "添加", path = "/add")
    @ApiOperation("新增")
    public ResponseData add(@RequestBody EntUserAccountParam param) {
        entUserAccountService.add(param);
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
    public ResponseData update(@RequestBody EntUserAccountParam param) {
        entUserAccountService.update(param);
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
    public ResponseData delete(@RequestBody EntUserAccountParam param) {
        entUserAccountService.delete(param);
        return ResponseData.success();
    }

    /**
     * 查询单条详情
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "查询详情", path = "/queryDetail")
    @ApiOperation(value = "查询详情", response = EntUserAccountResult.class)
    public ResponseData queryDetail(@RequestBody EntUserAccountParam param) {
        EntUserAccountResult result = entUserAccountService.findBySpec(param);
        return ResponseData.success(result);
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "查询列表", path = "/queryList")
    @ApiOperation(value = "查询列表", response = EntUserAccountResult.class)
    public ResponseData queryList(@RequestBody EntUserAccountParam param) {
        List<EntUserAccountResult> listBySpec = entUserAccountService.findListBySpec(param);
        return ResponseData.success(listBySpec);
    }

    /**
     * 分页查询列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "分页查询列表", path = "/queryListPage")
    @ApiOperation(value = "分页查询列表", response = EntUserAccountResult.class)
    public ResponseData queryListPage(@RequestBody EntUserAccountParam param) {
        PageResult<EntUserAccountResult> pageBySpec = entUserAccountService.findPageBySpec(param);
        return ResponseData.success(pageBySpec);
    }

}
