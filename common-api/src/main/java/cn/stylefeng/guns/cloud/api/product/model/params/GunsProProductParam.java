package cn.stylefeng.guns.cloud.api.product.model.params;

import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
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
@ApiModel
public class GunsProProductParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 商品id
     */
    private Long id;

    /**
     * 店铺id
     */
    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "品牌id")
    private Long brandId;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;

    /**
     * 成本
     */
    @ApiModelProperty(value = "成本价")
    private BigDecimal cost;

    /**
     * 原价
     */
    @ApiModelProperty(value = "原价")
    private BigDecimal originalPrice;

    /**
     * 现价
     */
    @ApiModelProperty(value = "现价")
    private BigDecimal presentPrice;

    /**
     * 简要描述
     */
    @ApiModelProperty(value = "简要描述")
    private String des;

    /**
     * 描述详情
     */
    @ApiModelProperty(value = "描述详情")
    private String desDetails;

    /**
     * 商品主图片
     */
    @ApiModelProperty(value = "商品主图片")
    private String picture;

    /**
     * 商品图片 按,分割
     */
    @ApiModelProperty(value = "商品图片 按,分割")
    private String pictures;

    /**
     * 销量
     */
    @ApiModelProperty(value = "销量")
    private Long salesVolume;

    /**
     * 总库存
     */
    @ApiModelProperty(value = "总库存")
    private Long totalStock;

    /**
     * 是否启用规格 0 否 1是
     */
    @ApiModelProperty(value = "是否启用规格 0 否 1是")
    private Integer isSku;

    /**
     * 状态 0删除 1正常  2下架
     */
    @ApiModelProperty(value = "状态 0删除 1正常  2下架")
    private Integer status;



    /**
     * sku name 项
     */
    private List<GunsProSkuNameItem> skuNameItems;

    /**
     * sku 信息
     */
    private List<GunsProSkuParam> skuItems;



}
