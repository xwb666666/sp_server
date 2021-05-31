package cn.stylefeng.guns.cloud.order.model.param;


import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class OrderListParam {

    @ApiModelProperty("订单状态")
    private Integer status;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("收货人姓名")
    private String contacts;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("商品名称")
    private String productName;

    private Long page=1L;
    private Long pageSize=20L;


}
