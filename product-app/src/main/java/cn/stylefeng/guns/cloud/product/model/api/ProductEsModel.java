package cn.stylefeng.guns.cloud.product.model.api;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: guns-cloud-parent
 * @description 供es提交的model
 * @author: wzx
 * @create: 2021-04-22 15:23
 **/
@Data
public class ProductEsModel {
    private Long id;
    private Long category_id;
    private Long brand_id;
    private String code;
    private String name;
    private Double cost;
    private Double original_price;
    private Double present_price;
    private String desc;
    private Double sales_volume;
    private String picture;
    private Double total_stock;
    private List<EsSkuModel> skus;
    private String create_time;

    @Data
    public static class EsSkuModel{
        private Long skuId;
        private String name;
        private String code;
        private Double cost;
        private String picture;
        private Double original_price;
        private Double present_price;
        private Double stock;
    }
}
