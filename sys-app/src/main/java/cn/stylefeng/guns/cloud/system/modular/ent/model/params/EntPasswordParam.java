package cn.stylefeng.guns.cloud.system.modular.ent.model.params;

import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码时的参数接受类
 *
 * @author stylefeng
 * @date 2019/10/30
 */
@Data
@ApiModel
public class EntPasswordParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    /**
     * 旧密码
     */
    @ApiModelProperty("旧密码")
    private String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty("新密码")
    private String newPassword;

    /**
     * 重复新密码
     */
    @ApiModelProperty("重复新密码")
    private String repeatPassword;

    @Override
    public String checkParam() {
        if (ObjectUtil.isEmpty(oldPassword)) {
            return "旧密码为空";
        }
        if (ObjectUtil.isEmpty(newPassword)) {
            return "新密码为空";
        }
        if (ObjectUtil.isEmpty(repeatPassword)) {
            return "重复输入的新密码为空";
        }
        if (!newPassword.equals(repeatPassword)) {
            return "两次密码输入不一致";
        }
        return null;
    }
}
