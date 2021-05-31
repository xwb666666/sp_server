package cn.stylefeng.guns.cloud.auth.core.secauth.impl;

import cn.stylefeng.guns.cloud.auth.core.secauth.BaseAuth;
import org.springframework.stereotype.Component;

/**
 * 二次认证的一个例子
 *
 * @author fengshuonan
 * @Date 2019/4/19 22:16
 */
@Component
public class ExampleAuth implements BaseAuth {

    @Override
    public void doAuth() {
        System.out.println("认证示例！");
    }
}
