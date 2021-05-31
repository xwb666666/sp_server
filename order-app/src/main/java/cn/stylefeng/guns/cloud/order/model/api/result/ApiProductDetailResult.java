package cn.stylefeng.guns.cloud.order.model.api.result;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-26 15:15
 **/
@Data
public class ApiProductDetailResult {


    /**
     * 商品id
     */
    private Long id;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 编码
     */
    private String code;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 成本
     */
    private BigDecimal cost;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 现价
     */
    private BigDecimal presentPrice;

    /**
     * 简要描述
     */
    private String des;

    /**
     * 描述详情
     */
    private String desDetails;

    /**
     * 商品主图片
     */
    private String picture;

    /**
     * 商品图片 按,分割
     */
    private String pictures;

    /**
     * 销量
     */
    private Long salesVolume;

    /**
     * 总库存
     */
    private Long totalStock;

    /**
     * 是否启用规格 0 否 1是
     */
    private Integer isSku;

    /**
     * 状态 0删除 1正常  2下架
     */
    private Integer status;

    private String statusText;

    /**
     * sku name 信息
     */
    private List<SkuNameItem> skuNames;

    /**
     * sku 信息
     */
    private List<SkuItem> skuList;

    @Data
    public static class SkuNameItem {
        private Long id;
        private String name;
        private List<SkuNameChildItem> items;

        @Data
        public static class SkuNameChildItem {
            private Long id;
            private String name;
        }
    }

    @Data
    public static class SkuItem {

        private Long id;    //sku_id
        private List<String> skuNames;
        private List<Long> skuNameIds;
        private Long productId;
        private String code;
        private String name;
        private String centont;
        private String picture;
        private Double cost;
        private Double originalPrice;
        private Double presentPrice;
        private Double stock;
    }
}
