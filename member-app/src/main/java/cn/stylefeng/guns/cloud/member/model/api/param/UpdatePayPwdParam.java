package cn.stylefeng.guns.cloud.member.model.api.param;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel
public class UpdatePayPwdParam implements Serializable, BaseValidatingParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("会员id")
    @NotNull(message = "会员id不能为空")
    private Long memberId;

    @ApiModelProperty("支付密码")
    @NotNull(message = "支付密码不能为空")
    private String oldPayPwd;

    @ApiModelProperty("新密码")
    @NotNull(message = "新密码不能为空")
    private String newPayPwd;
}
