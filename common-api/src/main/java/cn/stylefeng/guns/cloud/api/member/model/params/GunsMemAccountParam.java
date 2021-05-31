package cn.stylefeng.guns.cloud.api.member.model.params;


import cn.stylefeng.guns.cloud.api.member.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.member.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class GunsMemAccountParam implements Serializable, BaseValidatingParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @NotNull(message = "id不能为空",groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String memberId;

    @ApiModelProperty("金豆")
    @NotNull(message = "金豆不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private Integer integral;

    @ApiModelProperty("支付密码")
    private String payPwd;

    @ApiModelProperty("冻结金额")
    @NotNull(message = "冻结金额不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String lockMoney;

    @ApiModelProperty("账户余额")
    @NotNull(message = "账户余额不能为空",groups = {AddGroup.class})
    private Date memberMoney;
}
