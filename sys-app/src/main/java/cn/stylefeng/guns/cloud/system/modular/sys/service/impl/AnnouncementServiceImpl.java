package cn.stylefeng.guns.cloud.system.modular.sys.service.impl;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.api.member.model.result.AnnouncementDetailResult;
import cn.stylefeng.guns.cloud.api.member.model.result.ApiAnnouncementResult;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProListResult;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.Announcement;
import cn.stylefeng.guns.cloud.system.modular.sys.mapper.AnnouncementMapper;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AnnouncementParam;
import cn.stylefeng.guns.cloud.api.member.model.result.AnnouncementResult;
import cn.stylefeng.guns.cloud.system.modular.sys.service.AnnouncementService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {
    @Override
    public Boolean addAnnouncement(AnnouncementParam param) {
        Announcement announcement=new Announcement();
        ToolUtil.copyProperties(param,announcement);
        announcement.setCreateTime(new DateTime());
        baseMapper.insert(announcement);
        return true;
    }


    @Override
    public Boolean removeAnnouncement(Long[] ids) {
        List<Long> idList= Arrays.asList(ids);
        baseMapper.deleteBatchIds(idList);
        return true;
    }

    @Override
    public Boolean updateAnnouncement(AnnouncementParam param) {
        if (param.getId()==null){
            throw new ServiceException(500,"公告id不能为空");
        }

        QueryWrapper<Announcement>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",param.getId());
        Announcement announcement = baseMapper.selectOne(queryWrapper);
        if (announcement==null){
            throw new ServiceException(500,"此公告不存在");
        }

        Announcement ann=new Announcement();
        ToolUtil.copyProperties(param,ann);
        baseMapper.updateById(ann);
        return true;
    }

    @Override
    public PageResult<AnnouncementResult> selectAnnouncement() {

        IPage<Announcement> page = new Page<>();
        page.setSize(20);
        page.setCurrent(1);
        page = baseMapper.selectPage(page, null);
        PageResult<AnnouncementResult> result = new PageResult<>();

        result.setPage(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotalPage(page.getPages());
        result.setTotalRows(page.getTotal());
        List<Announcement> rows = page.getRecords();
        List<AnnouncementResult> announcementResults = new ArrayList<>();
        rows.forEach(item -> {
            AnnouncementResult r = new AnnouncementResult();
            ToolUtil.copyProperties(item, r);
            announcementResults.add(r);
        });
        result.setRows(announcementResults);
        return result;
    }

    @Override
    public AnnouncementDetailResult selectAnnouncementDetail(Long id) {
        if (id==null){
            throw new ServiceException(500,"公告id不能为空");
        }
        Announcement announcement = baseMapper.selectById(id);
        if (announcement==null){
            throw new ServiceException(500,"公告id不存在");
        }
        AnnouncementDetailResult announcementResult=new AnnouncementDetailResult();
        ToolUtil.copyProperties(announcement,announcementResult);
        return announcementResult;
    }

    @Override
    public ApiAnnouncementResult selectOneAnnouncement() {

        QueryWrapper<Announcement>queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time").last("limit 1");
        Announcement announcement = baseMapper.selectOne(queryWrapper);
        ApiAnnouncementResult announcementResult=new ApiAnnouncementResult();
        ToolUtil.copyProperties(announcement,announcementResult);
        return announcementResult;

    }


}
