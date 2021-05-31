package cn.stylefeng.guns.cloud.order.model.api.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class OrderParam implements Serializable {

    @ApiModelProperty("会员id")
    private Long memberId;      //会员id

    @ApiModelProperty("订单号")
    private String orderNo;      //订单号

}
