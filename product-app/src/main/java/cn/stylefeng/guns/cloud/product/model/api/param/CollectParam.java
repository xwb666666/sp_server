package cn.stylefeng.guns.cloud.product.model.api.param;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class CollectParam {

    private Long memberId;

    private Long page=1L;
    private Long pageSize=20L;
}
