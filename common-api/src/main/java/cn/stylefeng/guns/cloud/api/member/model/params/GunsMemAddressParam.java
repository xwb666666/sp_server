package cn.stylefeng.guns.cloud.api.member.model.params;


import cn.stylefeng.guns.cloud.api.member.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.member.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@ApiModel
public class GunsMemAddressParam implements Serializable, BaseValidatingParam {
    private static final long serialVersionUID = 1L;

    private Long id;        //详细地址id

    @ApiModelProperty("会员id")
    @NotNull(message = "会员id不能为空",groups = UpdateGroup.class)
    private Long memberId;  //会员id

    @ApiModelProperty("区域id")
    @NotNull(message = "区域id不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private Long areaId;    //区域id

    @ApiModelProperty("区域名称")
    @NotNull(message = "区域名称不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String area;

    @ApiModelProperty("名字")
    @NotNull(message = "名字不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String name;    //名字

    @ApiModelProperty("手机号码")
    @Pattern(message = "手机号码格式不正确",regexp = "1[3|4|5|7|8][0-9]\\d{8}")
    @NotNull(message = "手机号码不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String phone;

    @ApiModelProperty("详细地址")
    @NotNull(message = "详细地址不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String address;

    private Integer isDefault;

    private Integer sort;

}
