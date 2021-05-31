package cn.stylefeng.guns.cloud.api.product.model.params;

import cn.stylefeng.guns.cloud.api.product.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.product.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author stylefeng
 * @since 2021-03-23
 */
@Data
@ApiModel
public class GunsProBrandParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 品牌id
     */
    @ApiModelProperty("品牌id")
    @NotNull(message="品牌id不能为空",groups = UpdateGroup.class)
    private Long id;


    /**
     * 品牌名称
     */
    @ApiModelProperty("品牌名称")
    @NotBlank(message="品牌name不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    /*@NotBlank(message="品牌图片不能为空",groups = {AddGroup.class, UpdateGroup.class})*/
    private String picture;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    @NotNull(message="品牌排序sort不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private Long sort;

    /**
     * 状态 0禁用 1正常
     */
    @ApiModelProperty("状态 0删除 1正常")
    @Range(message = "状态只能输入0或1",min = 0,max = 1,groups = {AddGroup.class, UpdateGroup.class})
    private Integer status;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 是否是独有品牌  0否 1是
     */
    @ApiModelProperty("是否是独有品牌  0否 1是")
    @Range(message = "状态只能输入0或1",min = 0,max = 1,groups = {AddGroup.class, UpdateGroup.class})
    private Integer isPrivate;


    /**
     * 首字母
     */
    @ApiModelProperty("首字母")
    @NotBlank(message="添加时间",groups = {UpdateGroup.class})
    private String firstChar;

    @Override
    public String checkParam() {
        return null;
    }

}
