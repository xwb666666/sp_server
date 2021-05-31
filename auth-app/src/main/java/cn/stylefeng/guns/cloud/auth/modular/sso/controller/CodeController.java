package cn.stylefeng.guns.cloud.auth.modular.sso.controller;

import cn.stylefeng.guns.cloud.auth.modular.sso.model.AccessCodeParam;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.AuthCode;
import cn.stylefeng.guns.cloud.auth.modular.sso.service.AuthService;
import cn.stylefeng.guns.cloud.auth.modular.sso.util.RSAUtil;
import cn.stylefeng.guns.cloud.libs.config.properties.TenantProperties;
import cn.stylefeng.guns.cloud.libs.context.SpringContext;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


/**
 * 登录认证控制器，颁发accessCode
 *
 * @author fengshuonan
 * @Date 2019/9/25 21:52
 */
@RestController
@RequestMapping("/auth")
public class CodeController {

    @Autowired
    private AuthService authService;

    /**
     * 获取accessCode，校验账号密码是否正确，密码经过rsa对称加密
     *
     * @author fengshuonan
     * @Date 2019/9/25 22:03
     */
    @PostMapping("/accessCode")
    public ResponseData accessCode(@RequestBody AccessCodeParam accessCodeParam) {
        String decryptPassword = RSAUtil.decrypt(accessCodeParam.getPassword());

        //如果系统开启了多租户开关，则添加租户的临时缓存
        TenantProperties tenantProperties = SpringContext.getBean(TenantProperties.class);
        if (tenantProperties.getOpen()) {
            AuthCode authCode = authService.accessCode(accessCodeParam.getAccount(), decryptPassword, accessCodeParam.getTenantCode());
            return ResponseData.success(authCode);
        } else {
            AuthCode authCode = authService.accessCode(accessCodeParam.getAccount(), decryptPassword);
            return ResponseData.success(authCode);
        }
    }

}
