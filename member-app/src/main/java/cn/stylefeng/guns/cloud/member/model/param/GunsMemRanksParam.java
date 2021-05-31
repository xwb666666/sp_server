package cn.stylefeng.guns.cloud.member.model.param;


import cn.stylefeng.guns.cloud.api.member.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.member.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel
public class GunsMemRanksParam implements Serializable, BaseValidatingParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("等级id")
    @NotNull(message = "等级id不能为空",groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty("等级名称")
    @NotNull(message = "等级名称不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String name;

    @ApiModelProperty("初始金豆")
    @NotNull(message = "初始金豆不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private Integer startScore;

    @ApiModelProperty("等级图标")
    private String userRankImg;

    @ApiModelProperty("默认等级 0不为默认等级 1为默认等级")
    @NotNull(message = "默认等级不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String isDefault;

}
