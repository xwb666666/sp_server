package cn.stylefeng.guns.cloud.order.model.api.result;

import lombok.Data;

import java.util.List;

@Data
public class CartItemResult {
    private boolean check;
    private Long shopId;
    private String shopName;
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
    private List<String> skuInfo;
}
