package cn.stylefeng.guns.cloud.product.model.api.param;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class AddCollectParam {

    private Long memberId;

    private Long productId;
}
