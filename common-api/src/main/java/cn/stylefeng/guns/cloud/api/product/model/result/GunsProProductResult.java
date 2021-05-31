package cn.stylefeng.guns.cloud.api.product.model.result;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.api.product.model.params.SkuAndSkuNameItem;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author
 * @since 2021-03-22
 */
@Data
public class GunsProProductResult implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private List<ProductSkuNameItem> skuInfo;

    /**
     * 状态 0删除 1正常  2下架
     */
    private Integer status;

    private String statusText;

    /**
     * 添加时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 上架时间
     */
    private Date putOnDate;

    /**
     * 下架时间
     */
    private Date lowOffDate;

    /**
     * sku name 信息
     */
    private List<SkuAndSkuNameItem> skuNames;

    /**
     * sku 信息
     */
    private List<GunsProSkuResult> skuList;

}
