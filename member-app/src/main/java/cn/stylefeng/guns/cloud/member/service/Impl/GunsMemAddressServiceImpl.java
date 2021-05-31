package cn.stylefeng.guns.cloud.member.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import cn.stylefeng.guns.cloud.api.member.entity.GunsMemAddress;


import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemAddressResult;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemAddressMapper;
import cn.stylefeng.guns.cloud.member.service.GunsMemAddressService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GunsMemAddressServiceImpl extends ServiceImpl<GunsMemAddressMapper, GunsMemAddress> implements GunsMemAddressService {
    @Override
    public List<GunsMemAddressResult> select(Long memberId) {

        List<GunsMemAddressResult> list = new ArrayList<>();
        QueryWrapper<GunsMemAddress> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("member_id",memberId);
        List<GunsMemAddress> gunsMemAddresses = baseMapper.selectList(queryWrapper);
        for (GunsMemAddress gunsMemAddress:gunsMemAddresses) {
            GunsMemAddressResult gunsMemAddressResult = new GunsMemAddressResult();
            BeanUtil.copyProperties(gunsMemAddress, gunsMemAddressResult, CopyOptions.create().setIgnoreNullValue(true).ignoreError());
            list.add(gunsMemAddressResult);
        }
        return list;
    }

    @Override
    public Boolean saveAddress(GunsMemAddress gunsMemAddress) {

        if (gunsMemAddress.getIsDefault()==0){
           this.update (new UpdateWrapper<GunsMemAddress>().set("is_default",1).eq("is_default",0).eq("member_id",gunsMemAddress.getMemberId()));
        }
         baseMapper.insert(gunsMemAddress);


        return true;
    }

    @Override
    public GunsMemAddressResult selectDefaultAddress(Long memberId) {
        QueryWrapper<GunsMemAddress>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("member_id",memberId).eq("is_default",0);
        GunsMemAddress gunsMemAddress = baseMapper.selectOne(queryWrapper);
        GunsMemAddressResult gunsMemAddressResult=new GunsMemAddressResult();
        ToolUtil.copyProperties(gunsMemAddress,gunsMemAddressResult);
        return gunsMemAddressResult;
    }

    @Override
    public Boolean UpdateDefaultAddress(Long memberId,Long id) {
        if (id==null || memberId==null){
            throw new ServiceException(500,"地址id不能为空");
        }
        QueryWrapper<GunsMemAddress>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("member_id", memberId).eq("id",id);
        GunsMemAddress gunsMemAddress = baseMapper.selectOne(queryWrapper);
        if (gunsMemAddress.getIsDefault()==0){
            throw new ServiceException(500,"已经是默认地址");
        }

        if (gunsMemAddress.getIsDefault()!=0){
            QueryWrapper<GunsMemAddress> wrapper = new QueryWrapper<>();
            wrapper.eq("member_id",memberId);
            List<GunsMemAddress> gunsMemAddresses = baseMapper.selectList(wrapper);
            for (GunsMemAddress memAddress:gunsMemAddresses) {
                if(memAddress.getIsDefault()==0){
                    UpdateWrapper<GunsMemAddress>updateWrapper=new UpdateWrapper<>();
                    updateWrapper.set("is_default",1)
                            .eq("member_id",memberId)
                            .eq("id",memAddress.getId());
                    baseMapper.update(null, updateWrapper);
                }
            }
        }
        gunsMemAddress.setIsDefault(0);
        baseMapper.update(gunsMemAddress,queryWrapper);
        return true;
    }


}
