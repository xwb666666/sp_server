package cn.stylefeng.guns.cloud.auth.core.secauth;

import cn.stylefeng.guns.cloud.model.exp.SpsAuthException;

/**
 * 基础认证接口
 * <p>
 * 自定义拓展认证，例如，手机验证码二次认证
 *
 * @author fengshuonan
 * @Date 2019/4/19 22:14
 */
public interface BaseAuth {

    /**
     * 执行认证
     *
     * @throws SpsAuthException 认证失败异常
     * @author fengshuonan
     * @Date 2019/4/19 22:15
     */
    void doAuth();

}
