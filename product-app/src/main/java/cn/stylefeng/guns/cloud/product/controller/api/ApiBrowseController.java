package cn.stylefeng.guns.cloud.product.controller.api;


import cn.stylefeng.guns.cloud.api.member.entity.GunsProBrowse;
import cn.stylefeng.guns.cloud.api.member.entity.GunsProCollect;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.model.api.param.AddBrowseParam;
import cn.stylefeng.guns.cloud.product.model.api.param.AddCollectParam;
import cn.stylefeng.guns.cloud.product.model.api.param.BrowseParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CollectParam;
import cn.stylefeng.guns.cloud.product.service.BrowseService;
import cn.stylefeng.guns.cloud.product.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/api/browse")
@Api(tags = "app-浏览记录")
public class ApiBrowseController {

    @Autowired
    private BrowseService browseService;

    @GetMapping("select")
    @ApiOperation("浏览记录")
    private ResponseData select(BrowseParam param){

        return ResponseData.success(browseService.selectList(param));
    }


    @PostMapping("add")
    @ApiOperation("添加浏览记录")
    private ResponseData add(@RequestBody AddBrowseParam param){
        GunsProBrowse gunsProBrowse=new GunsProBrowse();

        ToolUtil.copyProperties(param, gunsProBrowse); //参数到实体copy
        browseService.addSave(gunsProBrowse);

        return ResponseData.success();
    }

    @PostMapping("remove")
    @ApiOperation("删除浏览记录")
    private ResponseData remove(@RequestBody Long[] ids){
        if (ids==null){
            throw new ServiceException(500,"id不能为空");
        }
        browseService.removeIds(ids);

        return ResponseData.success();
    }


}
