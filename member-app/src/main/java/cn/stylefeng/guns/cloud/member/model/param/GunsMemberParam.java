package cn.stylefeng.guns.cloud.member.model.param;

import cn.stylefeng.guns.cloud.api.member.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.member.model.validated.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-14 18:35
 **/
@Data
public class GunsMemberParam {
    @ApiModelProperty("会员id")
    @NotNull(message = "会员id不能为空",groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty("等级id")
    @NotNull(message = "等级id不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private Integer rankId;

    @ApiModelProperty("会员账号")
    @NotNull(message = "会员账号不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String loginName;

    @ApiModelProperty("会员密码")
    private String password;

    @ApiModelProperty("会员")
    private String type;

    @ApiModelProperty("性别")
    @NotNull(message = "会员性别不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String gender;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("会员头像")
    private String photo;

    @ApiModelProperty("会员手机号")
    @NotNull(message = "会员手机号不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private String mobile;


    @ApiModelProperty("会员状态")
    @NotNull(message = "会员状态不能为空",groups = {AddGroup.class,UpdateGroup.class})
    private Integer status;

    private String payPwd;
}
