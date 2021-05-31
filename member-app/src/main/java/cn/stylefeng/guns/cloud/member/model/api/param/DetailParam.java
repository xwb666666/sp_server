package cn.stylefeng.guns.cloud.member.model.api.param;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel
public class DetailParam implements Serializable, BaseValidatingParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("会员id")
    @NotNull(message = "会员id不能为空")
    private Long memberId;//会员id

    private Long page=1L;
    private Long pageSize=20L;
}
