package cn.stylefeng.guns.cloud.product.consumer;

import cn.stylefeng.guns.cloud.api.member.model.result.AnnouncementResult;
import cn.stylefeng.guns.cloud.api.member.model.result.ApiAnnouncementResult;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import cn.stylefeng.guns.cloud.api.product.model.result.ApiAdvertisingResult;
import cn.stylefeng.guns.cloud.api.product.model.result.ApiShowcaseResult;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("guns-cloud-system")
@RequestMapping("/api/remote")
public interface RemoteSystemService {

    @GetMapping("/pic/getAdvertisingImg")
    public List<ApiAdvertisingResult> selectAdvertisingImg();

    @GetMapping( "/pic/getCaseImg")
    public List<ApiShowcaseResult> selectShowcaseImg();

    @GetMapping("/announcement/selectAnnouncement")
    PageResult<ApiAnnouncementResult> selectAnnouncement();

    @GetMapping("/announcement/selectAnnouncementDetail")
    AnnouncementResult selectAnnouncementDetail(@RequestParam Long id);

    @GetMapping("/announcement/selectAnn")
     ApiAnnouncementResult selectAnn();
}
