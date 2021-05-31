package cn.stylefeng.guns.cloud.product.model.api.result;

import cn.stylefeng.guns.cloud.product.model.api.ProductEsModel;
import lombok.Data;

import java.util.List;

@Data
public class SearchResult {
    private Long id;
    private Long categoryId;
    private Long brandId;
    private String code;
    private String name;
    private Double cost;
    private Double originalPrice;
    private Double presentPrice;
    private String desc;
    private Double salesVolume;
    private String picture;
    private Double totalStock;
    private List<SearchResult.SkuModel> skus;
    private String create_time;

    @Data
    public static class SkuModel{
        private Long skuId;
        private String name;
        private String code;
        private Double cost;
        private String picture;
        private Double originalPrice;
        private Double presentPrice;
        private Double stock;
    }

}
