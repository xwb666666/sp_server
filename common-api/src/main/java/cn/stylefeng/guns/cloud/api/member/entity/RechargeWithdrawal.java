package cn.stylefeng.guns.cloud.api.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("guns_recharge_withdrawal")
public class RechargeWithdrawal {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;                //id

    private String orderNo;         //订单号

    private Long memberId;          //会员id

    private Double amount;          //金额

    private Integer method;         //支付方式：0微信 1支付宝

    private  Integer type;          //类型：0充值 1提现

    private Integer status;         //状态：0未完成 1完成

    private Date createTime;        //创建时间
}
