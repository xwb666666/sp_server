package cn.stylefeng.guns.cloud.system.modular.sys.service.impl;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.Advertising;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.Showcase;
import cn.stylefeng.guns.cloud.system.modular.sys.mapper.ShowcaseMapper;

import cn.stylefeng.guns.cloud.system.modular.sys.model.params.ShowcaseParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.AdvertisingResult;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.ShowcaseResult;
import cn.stylefeng.guns.cloud.system.modular.sys.service.ShowcaseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ShowcaseServiceImpl extends ServiceImpl<ShowcaseMapper, Showcase> implements ShowcaseService {


    @Override
    public Boolean saveShowcaseImg(ShowcaseParam param) {
        Showcase showcase=new Showcase();
        ToolUtil.copyProperties(param,showcase);
        showcase.setCreateTime(new DateTime());
        baseMapper.insert(showcase);
        return true;
    }

    @Override
    public Boolean removeShowcaseImg(Long[] ids) {
        List<Long> idList= Arrays.asList(ids);
        baseMapper.deleteBatchIds(idList);
        return true;
    }


    @Override
    public List<ShowcaseResult> selectShowcaseImg() {

        //获取所有数据
        List<Showcase>result=baseMapper.selectList(null);
        List<ShowcaseResult> list = new ArrayList<>();

        for (Showcase showcase : result) {
            ShowcaseResult showcaseResult =new ShowcaseResult();
            ToolUtil.copyProperties(showcase,showcaseResult);
            list.add(showcaseResult);
        }
        return list;
    }

    @Override
    public Boolean updateShowcaseImg(ShowcaseParam param) {

        QueryWrapper<Showcase> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",param.getId());
        Showcase showcase=new Showcase();
        ToolUtil.copyProperties(param,showcase);
        baseMapper.update(showcase,queryWrapper);
        return true;
    }

    @Override
    public ShowcaseResult selectAdvertisingImgDetail(Long id) {
        if (id==null){
            throw new ServiceException(500,"橱窗图片id不能为空");
        }

        Showcase showcase = baseMapper.selectById(id);
        ShowcaseResult showcaseResult=new ShowcaseResult();
        ToolUtil.copyProperties(showcase,showcaseResult);
        return showcaseResult;
    }
}
