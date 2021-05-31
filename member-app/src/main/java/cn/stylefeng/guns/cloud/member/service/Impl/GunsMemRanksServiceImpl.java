package cn.stylefeng.guns.cloud.member.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMemRanks;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMember;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemRanksMapper;
import cn.stylefeng.guns.cloud.member.model.result.GunsMemRanksResult;
import cn.stylefeng.guns.cloud.member.service.GunsMemRanksService;
import cn.stylefeng.guns.cloud.member.service.GunsMemService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class GunsMemRanksServiceImpl extends ServiceImpl<GunsMemRanksMapper, GunsMemRanks> implements GunsMemRanksService {

    @Autowired
    private GunsMemService gunsMemService;

    @Override
    public List<GunsMemRanksResult> selectList(Long sort) {
        QueryWrapper<GunsMemRanks>queryWrapper = null;
        if(sort!=null){
            queryWrapper=new QueryWrapper<>();
            queryWrapper.orderBy(true,sort.equals(0l),"start_score");//按照金豆升降排序
        }
        List<GunsMemRanks> gunsMemRanks= baseMapper.selectList(queryWrapper);
        List<GunsMemRanksResult>gunsMemRanksResults=new ArrayList<>();

        for (GunsMemRanks ranks:gunsMemRanks){
            GunsMemRanksResult result=new GunsMemRanksResult();
            BeanUtil.copyProperties(ranks, result, CopyOptions.create().setIgnoreNullValue(true).ignoreError());
            gunsMemRanksResults.add(result);
        }
        return gunsMemRanksResults;
    }




    @Override
    @Transactional
    public boolean removeId(Long id) {
        QueryWrapper<GunsMember> queryWrapper = new QueryWrapper<>();
        GunsMemRanks gunsMemRanks = baseMapper.selectById(id);
        if (gunsMemRanks==null){
            throw new ServiceException(500,"等级不存在");
        }
        queryWrapper.eq("rank_id", id);
        Integer count = gunsMemService.count(queryWrapper);
        if (count==0){
             baseMapper.deleteById(id);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public List<GunsMemRanksResult> nameSelect(String name) {

        QueryWrapper<GunsMemRanks> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",name);
        List<GunsMemRanksResult> list = new ArrayList<>();
        List<GunsMemRanks> gunsMemRanks = baseMapper.selectList(queryWrapper);
        for (GunsMemRanks memRanks:gunsMemRanks) {
            GunsMemRanksResult gunsMemRanksResult = new GunsMemRanksResult();
            BeanUtil.copyProperties(memRanks, gunsMemRanksResult, CopyOptions.create().setIgnoreNullValue(true).ignoreError());
            list.add(gunsMemRanksResult);
        }
        return list;
    }
}
