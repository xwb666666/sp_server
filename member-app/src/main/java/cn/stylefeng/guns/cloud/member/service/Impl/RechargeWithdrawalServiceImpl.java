package cn.stylefeng.guns.cloud.member.service.Impl;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.api.member.entity.RechargeWithdrawal;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.constants.RechargeWithdrawalConstant;
import cn.stylefeng.guns.cloud.member.mapper.RechargeWithdrawalMapper;
import cn.stylefeng.guns.cloud.member.model.api.param.RechargeWithdrawalParam;
import cn.stylefeng.guns.cloud.member.model.result.RechargeWithdrawalResult;
import cn.stylefeng.guns.cloud.member.service.GunsMemService;
import cn.stylefeng.guns.cloud.member.service.RechargeWithdrawalService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechargeWithdrawalServiceImpl extends ServiceImpl<RechargeWithdrawalMapper, RechargeWithdrawal> implements RechargeWithdrawalService {

@Autowired
private GunsMemService gunsMemService;

    @Override
    public RechargeWithdrawalResult recharge(RechargeWithdrawalParam param) {
        GunsMemParamResult gunsMemParamResult = gunsMemService.selectMember(param.getMemberId());
        if(gunsMemParamResult==null)
            throw new ServiceException(500,"用户id不存在");

        //生成充值订单
        RechargeWithdrawal rechargeWithdrawal=new RechargeWithdrawal();
        rechargeWithdrawal.setOrderNo(ToolUtil.getIdGen());
        rechargeWithdrawal.setMemberId(param.getMemberId());
        rechargeWithdrawal.setAmount(param.getAmount());
        rechargeWithdrawal.setMethod(param.getMethod());
        rechargeWithdrawal.setCreateTime(new DateTime());
        rechargeWithdrawal.setStatus(RechargeWithdrawalConstant.RechargeWithdrawalStatus.NO_STATUS.getValue());
        rechargeWithdrawal.setType(RechargeWithdrawalConstant.RechargeWithdrawalType.RECHARGE.getValue());
        baseMapper.insert(rechargeWithdrawal);

        //获取充值订单信息
        RechargeWithdrawalResult rechargeWithdrawalResult=new RechargeWithdrawalResult();
        rechargeWithdrawalResult.setOrderNo(rechargeWithdrawal.getOrderNo());
        rechargeWithdrawalResult.setAmount(rechargeWithdrawal.getAmount());
        rechargeWithdrawalResult.setCreateTime(rechargeWithdrawal.getCreateTime());

        return rechargeWithdrawalResult;

    }
}
