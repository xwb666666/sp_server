package cn.stylefeng.guns.cloud.api.member.model.params;

import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel
public class GunsMemAccountDetailParam implements Serializable, BaseValidatingParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("会员ID")
    @NotNull(message = "会员ID不能为空")
    private Long memberId;        //会员id

    private Long page=1L;
    private Long pageSize=20L;


}
