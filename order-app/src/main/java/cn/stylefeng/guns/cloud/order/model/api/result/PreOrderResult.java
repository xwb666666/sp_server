package cn.stylefeng.guns.cloud.order.model.api.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 下单，确认订单时返回结构
 */
@Data
public class PreOrderResult {
    private Long memberId;
    //订单基本信息
    private String orderNo;
    private Integer orderType;
    private Integer status;

    private Integer payment;        //支付方式 0微信 1支付宝
    private Double realPayPrice;    //实付金额
    private Double totalPrice;      //总金额
    private Long addressId;;
    //收货人地址信息Delivery
    private String contacts;
    private String mobile;
    private String address;
    //快递信息
    private Integer expId;          //快递单号
    private String expNo;           //快递公司

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
            //颜色，尺寸两规格
            private Long skuId;
            private String skuInfo;
        }
    }

    //给前台数据结构打印
/*    void main(String[] args) {
        PreOrderResult o = new PreOrderResult();
        o.setExpNo("");
        o.setExpId(123);
        o.setMobile("1234567890");
        o.setTotalPrice(new Double(100));
        List<ShopCart> shopCarts = new ArrayList<>();
        ShopCart shopCart = new ShopCart();
        shopCart.setShopId(1l);
        shopCart.setShopName("apple");
        shopCart.setTotalPresentPrice(new Double(1000));
        List<ShopCart.ProductItem> productItems = new ArrayList<>();
        ShopCart.ProductItem productItem = new ShopCart.ProductItem();
        productItem.setProductId(1L);
        productItem.setPresentPrice(new Double(100));
        productItem.setNumber(new Double(5));
        productItems.add(productItem);
        shopCart.setProducts(productItems);
        shopCarts.add(shopCart);
        o.setShopCarts(shopCarts);
        System.out.println( JSON.toJSONString(o) );
    }*/
}

