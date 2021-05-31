package cn.stylefeng.guns.cloud.order.model.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description 购物车下单参数
 * @params
 * @return
 */
@Data
public class CartProductParam {

    private Long id;
    private Long skuId; //颜色，尺寸两规格。不启用规格ID为0
    private Long shopId;
    private Long memberId;

    private Double presentPrice;    //现价
    private Double number;
    private String type;    //订单类型
}
