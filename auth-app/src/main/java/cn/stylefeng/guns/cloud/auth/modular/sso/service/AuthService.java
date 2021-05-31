package cn.stylefeng.guns.cloud.auth.modular.sso.service;

import cn.stylefeng.guns.cloud.auth.api.model.LoginUser;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.AuthCode;

/**
 * 鉴权服务
 *
 * @author fengshuonan
 * @Date 2019/9/25 22:33
 */
public interface AuthService {

    /**
     * token退出
     *
     * @author fengshuonan
     * @Date 2019/9/25 22:33
     */
    void remove(String token);

    /**
     * 获取AuthCode
     *
     * @author fengshuonan
     * @Date 2019/9/25 22:33
     */
    AuthCode accessCode(String account, String password);

    /**
     * 获取AuthCode(带多租户属性)
     *
     * @author fengshuonan
     * @Date 2020/9/23 21:16
     */
    AuthCode accessCode(String account, String password, String tenantCode);

    /**
     * 根据授权码获取登录用户
     *
     * @author fengshuonan
     * @Date 2019/9/25 22:33
     */
    LoginUser getLoginUser(String code);

}
