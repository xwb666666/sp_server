package cn.stylefeng.guns.cloud.system.modular.sys.provider;


import cn.stylefeng.guns.cloud.api.member.model.result.AnnouncementDetailResult;
import cn.stylefeng.guns.cloud.api.member.model.result.AnnouncementResult;
import cn.stylefeng.guns.cloud.api.member.model.result.ApiAnnouncementResult;
import cn.stylefeng.guns.cloud.api.product.model.result.ApiAdvertisingResult;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.AdvertisingResult;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.ShowcaseResult;
import cn.stylefeng.guns.cloud.system.modular.sys.service.AdvertisingService;
import cn.stylefeng.guns.cloud.system.modular.sys.service.AnnouncementService;
import cn.stylefeng.guns.cloud.system.modular.sys.service.ShowcaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/remote")
public class ProviderSystemService {

    @Autowired
    AdvertisingService advertisingService;

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    ShowcaseService showcaseService;

    @GetMapping(name = "查看所有广告图片", path = "/pic/getAdvertisingImg")
    public List<AdvertisingResult> selectAdvertisingImg(){
        return advertisingService.ApiSelectAdvertisingImg();
    }

    @GetMapping(name = "查看所有橱窗图片", path = "/pic/getCaseImg")
    public List<ShowcaseResult> selectShowcaseImg(){
        return showcaseService.selectShowcaseImg();
    }

    @GetMapping(name = "查询公告列表", path = "/announcement/selectAnnouncement")
    public PageResult<AnnouncementResult> selectAnnouncement(){
        return announcementService.selectAnnouncement();
    }

    @GetMapping(name = "查询公告详情", path = "/announcement/selectAnnouncementDetail")
    public AnnouncementDetailResult selectAnnouncementDetail(@RequestParam Long id){
        return announcementService.selectAnnouncementDetail(id);
    }

    @GetMapping("/announcement/selectAnn")
    public ApiAnnouncementResult selectAnn(){

        return announcementService.selectOneAnnouncement();
    }
}
