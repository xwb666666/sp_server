package cn.stylefeng.guns.cloud.order.model.api.result;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApiOrderDetailResult {

    private String orderNo;         //订单编号
    private String contacts;        //收货人
    private String mobile;          //联系电话
    private String address;         //收货地址
    private Double totalPrice;      //总金额
    private Double realPayPrice;    //实付金额
    private Integer payment;        //支付方式 0微信 1支付宝
    private Date orderTime;         //下单时间
    private Date payTime;           //支付时间
    private Date completeTime;      //完成时间
    private Integer expId;          //快递单号
    private String expNo;           //快递公司
    private Integer status;         //订单状态 0待支付，1已发货，2待收货，3已收货，4已完成，5已取消，6退款中,7待发货
    private String statusDes = "待支付,已发货,待收货,已收货,已完成,已取消,退款中,待发货";
    private String remark;          //订单备注
    private Integer orderType;      //订单类型
    private String orderTypeDes = "普通单,拼团单,兑换单";
    private Integer refundStatus;   //退款状态 0已退款，1未退款
    private String refundStatusDes = "已退款,未退款";
    private Double freightAmount;   //运费
    private Double couponAmount;    //优惠卷金额


    private List<OrderProduct> products;

    @Data
    public static class OrderProduct {
        private String name;        //商品名称
        private Long productId;     //商品编号
        private String picture;     //商品图片
        private Double salePrice;   //销售价格
        private Double quantity;    //商品数量
        private String specs;       //商品规格 如：黑色,xxl
    }


}
