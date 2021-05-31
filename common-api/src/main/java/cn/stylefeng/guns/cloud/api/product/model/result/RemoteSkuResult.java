package cn.stylefeng.guns.cloud.api.product.model.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 远程调用规格信息
 */
@Data
public class RemoteSkuResult implements Serializable {

    private Long id;
    private List<String> skuNames;
    private List<Long> skuNameIds;
    private Long productId;
    private String code;
    private String name;
    private String centont;
    private String picture;

    private Double cost;
    private Double originalPrice;
    private Double presentPrice;

    private Double stock;

    private Integer status; //状态 0 删除 1正常 2上架 3下架

}
