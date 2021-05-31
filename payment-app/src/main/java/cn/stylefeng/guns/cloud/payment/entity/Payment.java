package cn.stylefeng.guns.cloud.payment.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("guns_payment")
public class Payment {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;             //id

    private String appId;           //应用程序app_id 为后期扩展使用

    private String orderNo;         //订单号,用于提交到支付平台

    private String paySubject;      //支付主题

    private String payBody;         //支付主体

    private String outOrderNo;      //外部订单号

    private Integer payType;        //支付方式 1：支付宝 2：微信

    private Date orderTime;         //订单时间,客户平台提交

    private BigDecimal payAmount;       //支付金额

    private String tradeNo;         //交易号,支付平台返回

    private Date payTime;           //支付时间,支付平台返回时间

    private Integer serviceType;    //服务类型 1:订单支付 2充值支付

    private BigDecimal realPayAmount;   //实付金额,支付平台返回

    private Integer status;         //0:未支付 1:已支付

    private Integer notifyStatus;  //给客户端通知状态 0 未发送 1已发送通知

    private Date createTime;        //创建时间


}





