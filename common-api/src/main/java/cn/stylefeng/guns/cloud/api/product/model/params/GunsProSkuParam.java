package cn.stylefeng.guns.cloud.api.product.model.params;

import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * <p>
 * 商品sku 信息
 * </p>
 *
 * @author stylefeng
 * @since 2021-03-23
 */
@Data
@ApiModel
public class GunsProSkuParam implements Serializable, BaseValidatingParam {


    private Long id;    //sku_id

    @ApiModelProperty("skuNames")
    private List<SkuAndSkuNameItem> skuNames;

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "sku名称")
    private String name;

    @ApiModelProperty(value = "sku值")
    private String centont;

    @ApiModelProperty(value = "sku图片")
    private String picture;

    @ApiModelProperty(value = "成本价")
    private BigDecimal cost;

    @ApiModelProperty(value = "原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "现价")
    private BigDecimal presentPrice;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "状态")//状态 0 删除 1正常 2下架
    private Integer status;

}
