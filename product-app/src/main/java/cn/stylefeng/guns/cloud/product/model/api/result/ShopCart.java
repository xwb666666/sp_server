package cn.stylefeng.guns.cloud.product.model.api.result;

import lombok.Data;
import java.util.List;

@Data
public class ShopCart {
    private Long shopId;
    private String shopName;
    private Double totalOriginalPrice;
    private Double totalPresentPrice;
    private Double totalCost;
    //订单类型
    private String type;

    private List<CartItem> list;
    @Data
    public static class CartItem{
        //是否选中
        private boolean check;
        private Long productId;
        private String productName;
        private Double number;
        //原价
        private Double originalPrice;
        //现价
        private Double presentPrice;
        //成本
        private Double cost;
        private String pic;

        //颜色，尺寸两规格
        private Long skuId;
        private String skuInfo;
    }
}
