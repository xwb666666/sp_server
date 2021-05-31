package cn.stylefeng.guns.cloud.order.model.api.param;

import lombok.Data;
import java.util.List;

/**
 * 创建订单时传参
 */
@Data
public class CreateOrderParam {
    private Long memberId;
    //订单基本信息
    private String orderNo;
    private Integer orderType;
    private Integer status;

    private Integer payment;    //支付方式 0微信 1支付宝
    private Double realPayPrice;    //实付金额
    private Double totalPrice;  //总金额
    private Double couponAmount;    //优惠券
    private List<Long> couponIds;    //优惠券id

    private Long addressId;
    //收货人地址信息Delivery
    private String contacts;
    private String mobile;
    private String address;
    //快递信息
    private Integer expId;  //快递单号
    private String expNo;   //快递公司

    private String remark;  //备注
    private String remark1;
    private String remark2;
    private String remark3;
    //商品信息
    private List<ShopCart> shopCarts;

    @Data
    public static class ShopCart{
        private Long shopId;
        private String shopName;
        private Double totalOriginalPrice;
        private Double totalPresentPrice;
        private Double totalCost;
        private List<ProductItem> products;

        @Data
        public static class ProductItem {
            private Long productId;
            private String productName;
            private Double quantity;
            //原价
            private Double originalPrice;
            //现价
            private Double presentPrice;
            //成本
            private Double cost;
            private String picture;

            private Long skuId; //颜色，尺寸两规格
            private String skuInfo;
        }
    }
}

