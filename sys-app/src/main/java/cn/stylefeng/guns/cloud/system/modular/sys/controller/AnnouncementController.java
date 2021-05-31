package cn.stylefeng.guns.cloud.system.modular.sys.controller;


import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AnnouncementParam;
import cn.stylefeng.guns.cloud.system.modular.sys.service.AnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "公告")
@ApiResource(name = "公告", path = "/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostResource(name = "添加公告", path = "/addAnnouncement")
    @ApiOperation("添加公告")
    public ResponseData addAnnouncement(@RequestBody AnnouncementParam param){
        announcementService.addAnnouncement(param);

        return ResponseData.success();
    }


    @PostResource(name = "删除公告", path = "/removeAnnouncement")
    @ApiOperation("删除公告")
    public ResponseData removeAnnouncement(@RequestBody Long[] ids){
        if (ids==null){
            throw new ServiceException(500,"id不能为空");
        }
        announcementService.removeAnnouncement(ids);

        return ResponseData.success();
    }


    @PostResource(name = "修改公告", path = "/updateAnnouncement")
    @ApiOperation("修改公告")
    public ResponseData updateAnnouncement(@RequestBody AnnouncementParam param){
        announcementService.updateAnnouncement(param);
        return ResponseData.success();
    }


    @GetResource(name = "查询公告列表", path = "/selectAnnouncement")
    @ApiOperation("查询公告")
    public ResponseData selectAnnouncement(){
        return ResponseData.success(announcementService.selectAnnouncement());
    }


    @GetResource(name = "查询公告详情", path = "/selectAnnouncementDetail")
    @ApiOperation("查询公告详情")
    public ResponseData selectAnnouncementDetail(Long id){
        return ResponseData.success(announcementService.selectAnnouncementDetail(id));
    }




}
