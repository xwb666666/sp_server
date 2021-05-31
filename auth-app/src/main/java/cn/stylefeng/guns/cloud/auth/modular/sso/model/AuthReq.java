package cn.stylefeng.guns.cloud.auth.modular.sso.model;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import lombok.Data;

/**
 * 登录认证的请求
 *
 * @author fengshuonan
 * @date 2019-04-19-14:21
 */
@Data
public class AuthReq extends SsoLoginReq implements BaseValidatingParam {

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 租户编码
     */
    private String tenantCode;

    @Override
    public String checkParam() {

        if (StrUtil.isBlank(account) || StrUtil.isBlank(password)) {
            return "账号密码为空！";
        }

        if (ObjectUtil.isEmpty(super.getClientId())) {
            return "参数不合法，应用id或url为空！";
        }

        return null;
    }
}
