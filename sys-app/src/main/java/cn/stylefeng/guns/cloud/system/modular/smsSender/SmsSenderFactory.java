package cn.stylefeng.guns.cloud.system.modular.smsSender;

import org.springframework.stereotype.Component;

/**
 * @program: guns-cloud-parent
 * @description 发送短信工厂
 * @author: wzx
 * @create: 2021-04-26 17:59
 **/

public class SmsSenderFactory {

    public static SmsSender builder()
    {
        return new TencentSmsSender(); //默认返回腾讯短信发送器
    }
}
