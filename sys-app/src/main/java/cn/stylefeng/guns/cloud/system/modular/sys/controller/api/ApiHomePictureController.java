package cn.stylefeng.guns.cloud.system.modular.sys.controller.api;


import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AdvertisingParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AdvertisingStatusParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.ShowcaseParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.ShowcaseStatusParam;
import cn.stylefeng.guns.cloud.system.modular.sys.service.AdvertisingService;
import cn.stylefeng.guns.cloud.system.modular.sys.service.ShowcaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@ApiResource(name = "app-查看首页图片", path = "api/homePicture")
@Api(tags = "app-查看首页图片")
public class ApiHomePictureController {

    @Autowired
    private AdvertisingService advertisingService;
    @Autowired
    private ShowcaseService showcaseService;


    @GetResource(name = "查看所有广告图片", path = "/ApiSelectAdvertisingImg")
    @ApiOperation("查看所有广告图片")
    public ResponseData selectAdvertisingImg(){
        return ResponseData.success(advertisingService.ApiSelectAdvertisingImg());
    }



    @GetResource(name = "查看所有橱窗图片", path = "/selectShowcaseImg")
    @ApiOperation("查看所有橱窗图片")
    public ResponseData selectShowcaseImg(){
        return ResponseData.success(showcaseService.selectShowcaseImg());
    }


}
