package cn.stylefeng.guns.cloud.product.model.api.param;

import lombok.Data;

@Data
public class AddEditCartParam {

    private Long id;
    private Long skuId; //颜色，尺寸两规格。不启用规格ID为0

    private Long shopId;
    private Long memberId;

    private Double number;

    private boolean check;

    private String type;    //订单类型
}
