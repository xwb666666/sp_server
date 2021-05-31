package cn.stylefeng.guns.cloud.api.product.model.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author stylefeng
 * @since 2021-03-23
 */
@Data
public class GunsProSkuResult implements Serializable {

    private Long id;    //sku_id

    @ApiModelProperty(value = "sku名称")
    private List<String> skuNames;

    private List<Long> skuNameIds;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "sku名称")
    private String name;

    @ApiModelProperty(value = "sku值")
    private String centont;

    @ApiModelProperty(value = "sku图片")
    private String picture;

    @ApiModelProperty(value = "成本价")
    private Double cost;

    @ApiModelProperty(value = "原价")
    private Double originalPrice;

    @ApiModelProperty(value = "现价")
    private Double presentPrice;

    @ApiModelProperty(value = "库存")
    private Double stock;

    @ApiModelProperty(value = "添加时间")
    private Date createDate;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

}
