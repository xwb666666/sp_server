package cn.stylefeng.guns.cloud.product.controller;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProGroup;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProGroupParam;
import cn.stylefeng.guns.cloud.api.product.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.product.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.service.GunsProGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product/group")
@Api(tags = "分组信息")
public class GunsProGroupController {

    @Autowired
    private GunsProGroupService gunsProGroupService;


    /**
     * 查询所有分组
     * @return
     */
    @GetMapping("/select")
    @ApiOperation("查询所有分组信息")
    public ResponseData select(){

        return ResponseData.success(gunsProGroupService.getSelect());
    }

    /**
     * 添加分组
     */
    @PostMapping("/add")
    @ApiOperation("添加所有分组信息")
    public ResponseData add(@Validated({AddGroup.class}) @RequestBody GunsProGroupParam param){
        GunsProGroup gunsProGroup=new GunsProGroup();
        ToolUtil.copyProperties(param, gunsProGroup); //参数到实体copy
       Boolean result= gunsProGroupService.save(gunsProGroup);
       if (result){
           return ResponseData.success();
       }else {
           return ResponseData.error("添加失败");
       }

    }

    /**
     * 修改
     * @param param
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改所有分组信息")
    public ResponseData update(@Validated({UpdateGroup.class}) @RequestBody GunsProGroupParam param){


        GunsProGroup gunsProGroup =new GunsProGroup();

        ToolUtil.copyProperties(param, gunsProGroup); //参数到实体copy
        Boolean result=gunsProGroupService.updateById(gunsProGroup);
        gunsProGroup.setCreatDate(new DateTime());
        if (result) {
            return ResponseData.success();
        }else {
            return ResponseData.error("更新失败");
        }

    }

    /**
     * 修改回显
     */
    @PostMapping("/echo")
    @ApiOperation("修改分组信息详情")
    public ResponseData echo(@Validated @RequestBody Map<String,Long> para){

        GunsProGroup gunsProGroup= gunsProGroupService.getById(para.get("id"));

        return ResponseData.success(gunsProGroup);
    }


    /**
     * 删除
     * @param para
     * @return
     */
    @PostMapping("/delete")
    public ResponseData delete(@Validated @RequestBody  Map<String,Long> para){
        Boolean result= gunsProGroupService.removeById(para.get("id"));
        if (result) {
            return ResponseData.success();
        }else {
            return ResponseData.error("删除失败");
        }

    }






}
