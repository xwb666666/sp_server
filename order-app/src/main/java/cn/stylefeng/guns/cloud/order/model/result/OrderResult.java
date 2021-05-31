package cn.stylefeng.guns.cloud.order.model.result;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResult {

    private String orderNo;         //订单单号
    private Integer status;         //订单状态
    private String statusDes="待支付,已发货,待收货,已收货,已完成,已关闭,退款中";
    private Integer orderType;      //订单类型
    private String orderTypeDes=" 普通订单,拼团订单,兑换订单";
    private Date orderTime;         //下单时间
    private Double realPayPrice;    //实付金额
    private Integer payment;        //支付方式 0微信 1支付宝
    private String contacts;        //收货人
    private String mobile;          //联系电话
    private String address;         //收货地址
    private String remark;          //备注

    private List<OrderProduct> products;

    @Data
    public static class OrderProduct
    {
        private String name;      //商品名称
        private Long productId;    //商品编号
        private String picture;   //商品图片
        private Double salePrice;  //销售价格
        private Double quantity; //商品数量
        private String specs; //商品规格 如：黑色,xxl
    }

}
