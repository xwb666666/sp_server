package cn.stylefeng.guns.cloud.member.service;


import cn.stylefeng.guns.cloud.api.member.entity.RechargeWithdrawal;
import cn.stylefeng.guns.cloud.member.model.api.param.RechargeWithdrawalParam;
import cn.stylefeng.guns.cloud.member.model.result.RechargeWithdrawalResult;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RechargeWithdrawalService extends IService<RechargeWithdrawal> {

    RechargeWithdrawalResult recharge(RechargeWithdrawalParam param);

   /* RechargeWithdrawalResult rechargeInfo();*/
}
