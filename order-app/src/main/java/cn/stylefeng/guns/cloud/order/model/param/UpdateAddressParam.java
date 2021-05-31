package cn.stylefeng.guns.cloud.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateAddressParam {

    @ApiModelProperty("订单号")
    private String orderNo;         //订单号

    @ApiModelProperty("收货人姓名")
    private String contacts;         //收货人姓名

    @ApiModelProperty("收货人手机号")
    private String mobile;       //收货人手机号

    @ApiModelProperty("收货地址")
    private String address;      //收货地址
}
