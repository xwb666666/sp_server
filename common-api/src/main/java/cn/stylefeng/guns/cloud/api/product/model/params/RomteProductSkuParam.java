package cn.stylefeng.guns.cloud.api.product.model.params;

import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 远程调用商品规格传参
 */
@Data
public class RomteProductSkuParam implements Serializable, BaseValidatingParam {

    private Integer productId;
    private Long skuId;    //sku_id
    private String code;
    private String name;

}
