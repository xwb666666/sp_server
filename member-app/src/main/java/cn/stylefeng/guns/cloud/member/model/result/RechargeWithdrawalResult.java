package cn.stylefeng.guns.cloud.member.model.result;

import lombok.Data;

import java.util.Date;

@Data
public class RechargeWithdrawalResult {

    private String orderNo;         //订单号

    private Double amount;          //金额

    private Date createTime;        //创建时间

}
