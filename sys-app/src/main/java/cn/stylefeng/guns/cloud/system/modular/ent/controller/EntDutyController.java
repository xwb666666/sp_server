package cn.stylefeng.guns.cloud.system.modular.ent.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntDutyParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntDutyResult;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntDutyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 职务管理控制器
 *
 * @author stylefeng
 * @Date 2019-10-10 22:44:17
 */
@RestController
@ApiResource(name = "职务管理管理", path = "/entDuty")
@Api(tags = "职务管理")
public class EntDutyController {


    @Autowired
    private EntDutyService entDutyService;

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "添加", path = "/add")
    @ApiOperation("新增")
    public ResponseData add(@RequestBody EntDutyParam param) {
        entDutyService.add(param);
        return ResponseData.success();
    }

    /**
     * 获取职务详情
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    @GetResource(name = "详情", path = "/detail")
    @ApiOperation("详情")
    public ResponseData detail(@RequestParam Long dutyId) {
        return ResponseData.success(entDutyService.getById(dutyId));
    }

    /**
     * 修改
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @PostResource(name = "修改", path = "/update")
    @ApiOperation("修改")
    public ResponseData update(@RequestBody EntDutyParam param) {
        entDutyService.update(param);
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
    public ResponseData delete(@RequestBody EntDutyParam param) {
        entDutyService.delete(param);
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
    public ResponseData changeStatus(@RequestParam Long dutyId, @RequestParam Integer status) {
        this.entDutyService.changeStatus(dutyId, StatusEnum.toEnum(status));
        return ResponseData.success();
    }

    /**
     * 分页查询列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    @GetResource(name = "分页查询列表", path = "/queryListPage")
    @ApiOperation(value = "分页查询列表", response = EntDutyResult.class)
    public ResponseData queryListPage(@RequestParam String dutyName,
                                                     @RequestParam String dutyCode) {
        PageResult<EntDutyResult> pageBySpec = entDutyService.findPageBySpec(dutyName, dutyCode);
        return ResponseData.success(pageBySpec);
    }

    /**
     * 获取职务列表
     *
     * @author stylefeng
     * @date 2019/10/14
     */
    @GetResource(name = "职务不分页列表", path = "/queryList")
    @ApiOperation(value = "职务不分页列表")
    public ResponseData queryList() {
        return ResponseData.success(this.entDutyService.queryList());
    }

}
