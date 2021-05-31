package cn.stylefeng.guns.cloud.system.modular.ent.model.params;

import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 当前登录人信息
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@Data
public class EntCurrentParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    /**
     * 用户信息id
     */
    private Long userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 姓
     */
    private String lastName;

    /**
     * 名
     */
    private String firstName;

    /**
     * 性别(M:男   F:女)
     */
    private String sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户头像
     */
    private String avatarUrl;

    @Override
    public String checkParam() {
        if (ObjectUtil.isEmpty(email)) {
            return "邮箱不能为空";
        }
        if (ObjectUtil.isEmpty(lastName)) {
            return "姓不能为空";
        }
        if (ObjectUtil.isEmpty(firstName)) {
            return "名不能为空";
        }
        if (ObjectUtil.isEmpty(name)) {
            return "姓名为空";
        }
        return null;
    }
}
