package cn.stylefeng.guns.cloud.api.product.model.result;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品列表返回内容
 */
@Data
public class GunsProListResult implements Serializable {
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
     * 商品主图片
     */
    private String picture;


    /**
     * 销量
     */
    private Long salesVolume;

    /**
     * 总库存
     */
    private Long totalStock;


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

}
