package cn.stylefeng.guns.cloud.member.model.api.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class RechargeWithdrawalParam {

    private Long memberId;      //会员id

    private Integer method;     //支付方式:0微信 1支付宝

    private Double amount;      //金额
}
