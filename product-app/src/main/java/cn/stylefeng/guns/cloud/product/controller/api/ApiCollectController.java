package cn.stylefeng.guns.cloud.product.controller.api;


import cn.stylefeng.guns.cloud.api.member.entity.GunsProCollect;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.model.api.param.AddCollectParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CollectParam;
import cn.stylefeng.guns.cloud.product.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/collect")
@Api(tags = "app-收藏")
public class ApiCollectController {

    @Autowired
    private CollectService collectService;

    @GetMapping("select")
    @ApiOperation("收藏商品")
    private ResponseData select(CollectParam param){

        return ResponseData.success(collectService.selectList(param));
    }


    @PostMapping("add")
    @ApiOperation("添加收藏")
    private ResponseData add(@RequestBody AddCollectParam param){
        GunsProCollect gunsProCollect=new GunsProCollect();

        ToolUtil.copyProperties(param, gunsProCollect); //参数到实体copy
        collectService.addSave(gunsProCollect);

        return ResponseData.success();
    }

    @PostMapping("remove")
    @ApiOperation("删除收藏")
    private ResponseData remove(@RequestBody Map<String,Long>param){
        if (param.get("id")==null){
            throw new ServiceException(500,"id不能为空");
    }
        collectService.removeId(param.get("id"));

        return ResponseData.success();
    }

}
