package cn.stylefeng.guns.cloud.api.product.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author
 * @since 2021-03-22
 */
@TableName("guns_pro_product")
@Data
public class GunsProProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 店铺id
     */
    @TableField("shop_id")
    private Long shopId;

    /**
     * 分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 品牌id
     */
    @TableField("brand_id")
    private Long brandId;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;

    /**
     * 成本
     */
    @TableField("cost")
    private BigDecimal cost;

    /**
     * 原价
     */
    @TableField("original_price")
    private Double originalPrice;

    /**
     * 现价
     */
    @TableField("present_price")
    private Double presentPrice;

    /**
     * 简要描述
     */
    @TableField("des")
    private String des;

    /**
     * 描述详情
     */
    @TableField("des_details")
    private String desDetails;

    /**
     * 商品主图片
     */
    @TableField("picture")
    private String picture;

    /**
     * 商品图片 按,分割
     */
    @TableField("pictures")
    private String pictures;

    /**
     * 销量
     */
    @TableField("sales_volume")
    private Double salesVolume;

    /**
     * 总库存
     */
    @TableField("total_stock")
    private Double totalStock;

    /**
     * 是否启用规格 0 否 1是
     */
    @TableField("is_sku")
    private Integer isSku;

    /**
     * 规格信息
     */
    @TableField("sku_info")
    private String skuInfo;

    /**
     * 状态 0删除 1正常 2已上架 3已下架
     */
    @TableField("status")
    private Integer status;

    /**
     * 添加时间
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @TableField("update_date")
    private Date updateDate;

    /**
     * 上架时间
     */
    @TableField("put_on_date")
    private Date putOnDate;

    /**
     * 下架时间
     */
    @TableField("low_off_date")
    private Date lowOffDate;

}
