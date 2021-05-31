package cn.stylefeng.guns.cloud.api.product.model.params;
import cn.stylefeng.guns.cloud.api.product.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.product.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 商品分类表
 * </p>
 *
 * @author 
 * @since 2021-03-24
 */
@Data
@ApiModel
public class GunsProCategoryParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty("id")
    @NotNull(message="分类id不能为空",groups = UpdateGroup.class)
    private Long id;

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    @NotBlank(message="分类名字name不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     * 简称
     */
    @ApiModelProperty("简称")
    @NotBlank(message="分类简称不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private String shortName;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    @NotNull(message="分类排序sort不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private Long sort;

    /**
     * 父id
     */
    @ApiModelProperty("父id")
    @NotNull(message="分类父id不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private Long parentId;

    @Override
    public String checkParam() {
        return null;
    }

}
