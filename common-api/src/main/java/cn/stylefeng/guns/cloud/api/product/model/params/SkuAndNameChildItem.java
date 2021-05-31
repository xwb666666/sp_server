package cn.stylefeng.guns.cloud.api.product.model.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SkuAndNameChildItem {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("skuName")
    private String name;
}
