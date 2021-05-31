package cn.stylefeng.guns.cloud.order.model.api.result;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class CreateOrderResult {

    private String orderNo;         //订单单号
    private Integer status;         //订单状态
    private String statusDes="待支付,已发货,待收货,已收货,已完成,已关闭,退款中";
    private Integer orderType;      //订单类型
    private String orderTypeDes="普通订单,拼团订单,兑换订单";
    private Date orderTime;         //下单时间
    private Double realPayPrice;    //实付金额
    private Integer payment;        //支付方式 0微信 1支付宝
    private String contacts;        //收货人
    private String mobile;          //联系电话
    private String address;         //收货地址
    private String remark;          //备注
    //界面显示运费
    private String remark1;
    //界面显示需扣除金豆
    private String remark3;

    private List<ShopCart> shops;

    @Data
    public static class ShopCart {
        private Long shopId;
        private String shopName;
        private String picture;
        private Double totalOriginalPrice;
        private Double totalPresentPrice;
        private Double totalCost;
        //订单类型
        private String type;
        private List<ProductItem> list;
        @Data
        public static class ProductItem{
            //是否选中
            private boolean check;
            private Long productId;
            private String productName;
            private Double quantity;
            //原价
            private Double originalPrice;
            //现价
            private Double presentPrice;
            //成本
            private Double costPrice;
            private String pictures;
            //颜色，尺寸两规格
            private Long skuId;
            private String skuInfo;
        }
    }
}
