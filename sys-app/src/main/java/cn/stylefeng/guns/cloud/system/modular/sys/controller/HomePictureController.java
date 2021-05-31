package cn.stylefeng.guns.cloud.system.modular.sys.controller;


import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AdvertisingParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.ShowcaseParam;
import cn.stylefeng.guns.cloud.system.modular.sys.service.AdvertisingService;
import cn.stylefeng.guns.cloud.system.modular.sys.service.ShowcaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ApiResource(name = "首页图片", path = "/homePicture")
@Api(tags = "首页图片")
public class HomePictureController {

    @Autowired
    private AdvertisingService advertisingService;

    @Autowired
    private ShowcaseService showcaseService;

    @PostResource(name = "添加广告图片", path = "/saveAdvertisingImg")
    @ApiOperation("添加广告图片")
    public ResponseData saveHomePicture(@RequestBody AdvertisingParam param){

        advertisingService.saveHomePicture(param);

        return ResponseData.success();
    }

    @PostResource(name = "删除广告图片", path = "/removeAdvertisingImg")
    @ApiOperation("删除广告图片")
    public ResponseData removeAdvertisingImg(@RequestBody Long[] ids){
        if (ids==null){
            throw new ServiceException(500,"id不能为空");
        }
        advertisingService.removeAdvertisingImg(ids);
        return ResponseData.success();
    }

    @GetResource(name = "查看所有广告图片", path = "/selectAdvertisingImg")
    @ApiOperation("查看所有广告图片")
    public ResponseData selectAdvertisingImg(){
        return ResponseData.success(advertisingService.selectListAdvertisingImg());
    }

    @PostResource(name = "修改广告图片", path = "/updateAdvertisingImg")
    @ApiOperation("修改广告图片")
    public ResponseData updateAdvertisingImg(@RequestBody AdvertisingParam param){

        advertisingService.updateAdvertisingImg(param);
        return ResponseData.success();
    }

    @GetResource(name = "查看广告图片详情", path = "/selectAdvertisingImgDetail")
    @ApiOperation("查看广告图片详情")
    public ResponseData selectAdvertisingImgDetail(Long id){
        return ResponseData.success(advertisingService.selectAdvertisingImgDetail(id));
    }

    @PostResource(name = "添加橱窗图片", path = "/saveShowcaseImg")
    @ApiOperation("添加橱窗图片")
    public ResponseData saveShowcaseImg(@RequestBody ShowcaseParam param){
        showcaseService.saveShowcaseImg(param);

        return ResponseData.success();
    }

    @PostResource(name = "删除橱窗图片", path = "/removeShowcaseImg")
    @ApiOperation("删除橱窗图片")
    public ResponseData removeShowcaseImg(@RequestBody Long[] ids){
        if (ids==null){
            throw new ServiceException(500,"id不能为空");
        }
        showcaseService.removeShowcaseImg(ids);
        return ResponseData.success();
    }

    @GetResource(name = "查看所有橱窗图片", path = "/selectShowcaseImg")
    @ApiOperation("查看所有橱窗图片")
    public ResponseData selectShowcaseImg(){
        return ResponseData.success(showcaseService.selectShowcaseImg());
    }

    @PostResource(name = "修改橱窗图片", path = "/updateShowcaseImg")
    @ApiOperation("修改橱窗图片")
    public ResponseData updateShowcaseImg(@RequestBody ShowcaseParam param){

        showcaseService.updateShowcaseImg(param);
        return ResponseData.success();
    }

    @GetResource(name = "查看橱窗图片详情", path = "/selectShowcaseImgDetail")
    @ApiOperation("查看橱窗图片详情")
    public ResponseData selectShowcaseImgDetail(Long id){
        return ResponseData.success(showcaseService.selectAdvertisingImgDetail(id));
    }

}
