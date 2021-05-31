package cn.stylefeng.guns.cloud.product.service.Impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProGroup;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProGroupResult;
import cn.stylefeng.guns.cloud.product.mapper.GunsProGroupMapper;
import cn.stylefeng.guns.cloud.product.model.api.result.GroupResult;
import cn.stylefeng.guns.cloud.product.service.GunsProGroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GunsProGroupServiceImpl extends ServiceImpl<GunsProGroupMapper, GunsProGroup> implements GunsProGroupService {


    @Override
    public List<GunsProGroupResult> getSelect() {
        //获取所有数据
        List<GunsProGroup>result=baseMapper.selectList(null);
        List<GunsProGroupResult> list = new ArrayList<>();

        for (GunsProGroup gunsProGroup : result) {
            GunsProGroupResult gunsProGroupResult =new GunsProGroupResult();
            BeanUtil.copyProperties(gunsProGroup, gunsProGroupResult, CopyOptions.create().setIgnoreNullValue(true).ignoreError());
            list.add(gunsProGroupResult);
        }
        return list;
    }


    @Override
    public List<GroupResult> select() {
        //获取所有数据
        List<GunsProGroup>result=baseMapper.selectList(null);
        List<GroupResult> list = new ArrayList<>();
        for (GunsProGroup gunsProGroup : result) {
            GroupResult groupResult =new GroupResult();
            BeanUtil.copyProperties(gunsProGroup, groupResult, CopyOptions.create().setIgnoreNullValue(true).ignoreError());
            list.add(groupResult);
        }
        return list;
    }
}
