package cn.stylefeng.guns.cloud.member.service;

import cn.stylefeng.guns.cloud.api.member.entity.GunsMemAddress;

import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemAddressResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface GunsMemAddressService extends IService<GunsMemAddress> {
    List<GunsMemAddressResult> select(Long memberId);


    Boolean saveAddress(GunsMemAddress gunsMemAddress);

    GunsMemAddressResult selectDefaultAddress(Long memberId);

    Boolean UpdateDefaultAddress(Long memberId,Long id);
}
