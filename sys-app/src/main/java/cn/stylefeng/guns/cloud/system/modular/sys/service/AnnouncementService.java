package cn.stylefeng.guns.cloud.system.modular.sys.service;

import cn.stylefeng.guns.cloud.api.member.model.result.AnnouncementDetailResult;
import cn.stylefeng.guns.cloud.api.member.model.result.ApiAnnouncementResult;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.Announcement;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AnnouncementParam;
import cn.stylefeng.guns.cloud.api.member.model.result.AnnouncementResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AnnouncementService extends IService<Announcement> {
    Boolean addAnnouncement(AnnouncementParam param);


    Boolean removeAnnouncement(Long[] ids);

    Boolean updateAnnouncement(AnnouncementParam param);

    PageResult<AnnouncementResult> selectAnnouncement();

    AnnouncementDetailResult selectAnnouncementDetail(Long id);

    ApiAnnouncementResult selectOneAnnouncement();
}