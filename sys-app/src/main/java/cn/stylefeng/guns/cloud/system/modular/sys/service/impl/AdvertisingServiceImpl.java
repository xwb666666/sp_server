package cn.stylefeng.guns.cloud.system.modular.sys.service.impl;
import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.Advertising;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.Showcase;
import cn.stylefeng.guns.cloud.system.modular.sys.mapper.AdvertisingMapper;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AdvertisingParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AdvertisingStatusParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.ShowcaseParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.ShowcaseStatusParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.AdvertisingResult;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.ShowcaseResult;
import cn.stylefeng.guns.cloud.system.modular.sys.service.AdvertisingService;
import cn.stylefeng.guns.cloud.system.modular.sys.service.ShowcaseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class AdvertisingServiceImpl extends ServiceImpl<AdvertisingMapper, Advertising> implements AdvertisingService {

    @Autowired
    private ShowcaseService showcaseService;

    @Override
    public Boolean saveHomePicture(AdvertisingParam param) {
        Advertising advertising=new Advertising();
        ToolUtil.copyProperties(param,advertising);
        advertising.setCreateTime(new DateTime());
        baseMapper.insert(advertising);

        return true;
    }

    @Override
    public Boolean removeAdvertisingImg(Long[] ids) {
        List<Long> idList= Arrays.asList(ids);
        baseMapper.deleteBatchIds(idList);
        return true;
    }


    @Override
    public List<AdvertisingResult>  selectListAdvertisingImg() {
        //获取所有数据
        List<Advertising>result=baseMapper.selectList(null);
        List<AdvertisingResult> list = new ArrayList<>();

        for (Advertising advertising : result) {
            AdvertisingResult advertisingResult =new AdvertisingResult();
           ToolUtil.copyProperties(advertising,advertisingResult);
            list.add(advertisingResult);
        }
        return list;
    }

    @Override
    public Boolean updateAdvertisingImg(AdvertisingParam param) {
        QueryWrapper<Advertising>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",param.getId());
        Advertising advertising=new Advertising();
        ToolUtil.copyProperties(param,advertising);
        baseMapper.update(advertising,queryWrapper);
        return true;
    }



    @Override
    public List<AdvertisingResult> ApiSelectAdvertisingImg() {
        //获取所有数据
        List<Advertising>result=baseMapper.selectList(null);
        List<AdvertisingResult> list = new ArrayList<>();

        for (Advertising advertising : result) {
            AdvertisingResult advertisingResult =new AdvertisingResult();
            ToolUtil.copyProperties(advertising,advertisingResult);
            list.add(advertisingResult);
        }
        return list;
    }

    @Override
    public AdvertisingResult selectAdvertisingImgDetail(Long id) {
        if (id==null){
            throw new ServiceException(500,"广告图片id不能为空");
        }

        Advertising advertising = baseMapper.selectById(id);
        AdvertisingResult advertisingResult=new AdvertisingResult();
        ToolUtil.copyProperties(advertising,advertisingResult);
        return advertisingResult;
    }


}
