package cn.stylefeng.guns.cloud.api.member.model.params;
import cn.stylefeng.guns.cloud.api.member.model.validated.SelectGroup;
import cn.stylefeng.guns.cloud.api.member.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@ApiModel
public class GunsMemParam implements Serializable, BaseValidatingParam {

    @ApiModelProperty("id")
    @NotNull(message = "id不能为空",groups = {UpdateGroup.class, SelectGroup.class})
    private Long id;

    @ApiModelProperty("等级id")
    private Integer rankId;

    @ApiModelProperty("会员密码")
    private String password;               //会员密码

    @ApiModelProperty("会员")
    @NotNull(message = "不能为空",groups = UpdateGroup.class)
    @Range(message = "0是会员1是普通用户",min = 0,max = 1)
    private Integer type;                   //默认是会员

    @ApiModelProperty("性别")
    @NotNull(message = "性别不能为空",groups = UpdateGroup.class)
    @Range(message = "0保密1男2女",min = 0,max = 2)
    private Integer gender;                  //性别

    @ApiModelProperty("用户昵称")
    private String nickName;               //用户昵称

    @ApiModelProperty("会员头像")
    private String photo;                  //会员头像

    @ApiModelProperty("手机号")
   /* @Pattern(message = "手机号码格式不正确",regexp = "1[3|4|5|7|8][0-9]\\d{8}")*/
    @NotNull(message = "手机号不能为空",groups = UpdateGroup.class)
    private String mobile;                 //用户手机号

    @ApiModelProperty("账号状态")
    @NotNull(message = "账号状态不能为空",groups = UpdateGroup.class)
    @Range(message = "0停用 1:启用'",min = 0,max = 1)
    private Integer status;                //账号状态0:停用 1:启用',

    @ApiModelProperty("支付密码")
    private String payPwd;          //支付密码

}
