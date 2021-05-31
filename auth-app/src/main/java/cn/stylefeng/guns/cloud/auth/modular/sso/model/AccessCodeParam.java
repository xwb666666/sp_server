package cn.stylefeng.guns.cloud.auth.modular.sso.model;

import lombok.Data;

/**
 * 获取授权码的请求参数
 *
 * @author fengshuonan
 * @Date 2019/9/25 21:47
 */
@Data
public class AccessCodeParam {

    /**
     * 账号
     */
    private String account;

    /**
     * 密码（RSA对称加密）
     */
    private String password;

    /**
     * 租户编码
     */
    private String tenantCode;
}
