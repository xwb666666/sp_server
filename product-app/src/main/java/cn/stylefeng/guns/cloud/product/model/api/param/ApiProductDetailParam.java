package cn.stylefeng.guns.cloud.product.model.api.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ApiProductDetailParam {

    @ApiModelProperty("会员id")
    private Long memberId;

    @ApiModelProperty("商品id")
    private Long id;
}
