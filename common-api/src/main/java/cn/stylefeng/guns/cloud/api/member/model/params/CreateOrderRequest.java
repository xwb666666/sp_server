package cn.stylefeng.guns.cloud.api.member.model.params;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-05-08 13:27
 **/
@Data
public class CreateOrderRequest {

    private String appId;               //应用程序app_id 为后期扩展使用

    private String outOrderNo;         //外部订单号

    private String paySubject;          //支付主题

    private String payBody;             //支付主体

    private Integer payType;            //支付方式 1：支付宝 2：微信

    private Date orderTime;             //订单时间,客户平台提交

    private BigDecimal payAmount;       //支付金额

    private Integer serviceType;        //服务类型 1:订单支付 2充值支付


}
