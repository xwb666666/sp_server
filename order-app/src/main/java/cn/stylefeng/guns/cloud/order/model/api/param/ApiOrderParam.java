package cn.stylefeng.guns.cloud.order.model.api.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class ApiOrderParam implements Serializable {

    @ApiModelProperty("会员id")
    private Long memberId;      //会员id

    @ApiModelProperty("订单类型")
    private Integer orderType;  //订单类型

    @ApiModelProperty("订单状态")
    private Integer status;     //订单状态

    private Integer page = 1;

    private Integer pageSize = 20;

}
