package cn.stylefeng.guns.cloud.system.modular.smsSender;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;

/**
 * @program: guns-cloud-parent
 * @description 发送短信接口
 * @author: wzx
 * @create: 2021-04-26 17:37
 **/

public abstract class SmsSender {
    protected String smsSign="环创融数";
    public  abstract boolean sendMsg(String[] mobiles,String templateId, String[] templatesParams) throws TencentCloudSDKException;
}
