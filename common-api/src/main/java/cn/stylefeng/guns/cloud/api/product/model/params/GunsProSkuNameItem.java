package cn.stylefeng.guns.cloud.api.product.model.params;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel
public class GunsProSkuNameItem implements Serializable {

    private Long id;
    private String name;
    private Long parentId;
    private List<GunsProSkuNameItem> children;
}
