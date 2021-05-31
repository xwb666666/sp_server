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
import java.util.Date;

/**
 * 分组
 */
@Data
@ApiModel
public class GunsProGroupParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 分组id
     */
    @ApiModelProperty("分组id")
    @NotNull(message="分组id不能为空",groups = UpdateGroup.class)
    private Long id;

    /**
     * 分组名称
     */
    @ApiModelProperty("名称")
    @NotBlank(message="分组名字name不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private String name;



    /**
     * 状态 0启用   1禁用
     */
    @ApiModelProperty("状态 0启用 1禁用")
    @Range(message = "状态只能输入0或1",min = 0,max = 1,groups = {AddGroup.class, UpdateGroup.class})
    private Integer status;

}
