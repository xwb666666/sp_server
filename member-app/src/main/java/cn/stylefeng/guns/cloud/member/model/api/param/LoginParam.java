package cn.stylefeng.guns.cloud.member.model.api.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class LoginParam {

    @ApiModelProperty("用户账号")
    @NotNull(message = "用户账号不能为空")
    private String loginName;

    @ApiModelProperty("用户密码")
    @NotNull(message = "用户密码不能为空")
    private String password;
}
