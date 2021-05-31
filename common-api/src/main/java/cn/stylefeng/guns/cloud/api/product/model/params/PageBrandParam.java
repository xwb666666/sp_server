package cn.stylefeng.guns.cloud.api.product.model.params;

import cn.stylefeng.guns.cloud.api.product.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.product.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;



@Data
@ApiModel
public class PageBrandParam {

    private String name;

    private Long page=1L;
    private Long pageSize=20L;

}
