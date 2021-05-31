package cn.stylefeng.guns.cloud.api.product.model.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class SkuAndSkuNameItem {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("skuName")
    private String name;
    @ApiModelProperty("item")
    private SkuAndNameChildItem item;
}


