package cn.stylefeng.guns.cloud.api.product.model.result;

import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author stylefeng
 * @since 2021-03-23
 */
@Data
public class GunsProSkuNameResult implements Serializable {

    @ApiModelProperty(value = "规格名称id")
    private Long id;

    @ApiModelProperty(value = "规格名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer sort;



}
