package cn.stylefeng.guns.cloud.api.product.model.result;

import lombok.Data;

import java.util.List;

@Data
public class ProductSkuNameItem {
    private Long id;
    private String name;
    private List<ProductSkuNameItem> items;
}
