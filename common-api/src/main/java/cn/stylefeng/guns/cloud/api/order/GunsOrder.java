package cn.stylefeng.guns.cloud.api.order;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("guns_order")
@Data
public class GunsOrder implements Serializable {

    private String orderNo;                //订单号

    private Long memberId;          //会员id

    private Double totalPrice;      //总金额

    private Double realPayPrice;    //实收金额

    private String contacts;         //收货人姓名

    private String mobile;       //收货人手机号

    private String address;      //收货地址

    private String remark;      //订单备注

    private Integer payment;    //支付方式 0微信  1支付宝

    private Date orderTime;         //下单时间

    private Date payTime;           //支付时间

    private Date completeTime;        //完成时间

    private  Integer orderType;     //订单类型 0普通订单 1秒杀订单 2团购订单

    private Integer status;         //状态: 0待支付，1已发货，2待收货，3已收货，4已完成，5已关闭，6退款中 7待发货
    /**
     * 快递单号
     */
    private Integer expId;
    /**
     * 快递单号
     */
    private String expNo;

    private Double freightAmount;   //运费

    private Double couponAmount;    //优惠卷金额

    private Double discountAmount;  //折扣金额

    private Double promotionAmount; //促销金额

}
