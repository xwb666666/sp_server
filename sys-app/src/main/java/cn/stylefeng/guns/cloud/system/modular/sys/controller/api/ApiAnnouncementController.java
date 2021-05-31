package cn.stylefeng.guns.cloud.system.modular.sys.controller.api;


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
@Api(tags = "app-公告")
@ApiResource(name = "公告", path = "api/announcement")
public class ApiAnnouncementController {

    @Autowired
    private AnnouncementService announcementService;


    @GetResource(name = "查询公告列表", path = "/selectAnnouncement")
    @ApiOperation("查询公告列表")
    public ResponseData selectAnnouncement(){
        return ResponseData.success(announcementService.selectAnnouncement());
    }

    @GetResource(name = "查询公告详情", path = "/selectAnnouncementDetail")
    @ApiOperation("查询公告详情")
    public ResponseData selectAnnouncementDetail(Long id){
        return ResponseData.success(announcementService.selectAnnouncementDetail(id));
    }


}
