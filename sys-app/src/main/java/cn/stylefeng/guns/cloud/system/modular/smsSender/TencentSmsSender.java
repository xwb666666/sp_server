package cn.stylefeng.guns.cloud.system.modular.smsSender;

import cn.stylefeng.guns.cloud.libs.context.SpringContext;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.system.modular.oss.OSSFactory;
import cn.stylefeng.guns.cloud.system.modular.sys.model.SmsModel;
import cn.stylefeng.guns.cloud.system.modular.sys.service.CloudStorageService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-26 17:38
 **/

public class TencentSmsSender extends SmsSender {

    private static CloudStorageService cloudStorageService;
    static {
        TencentSmsSender.cloudStorageService= (CloudStorageService) SpringContext.getBean("CloudStorageService");
    }

    @Override
    public boolean sendMsg(String[] mobiles,String templateId, String[] templatesParams) throws TencentCloudSDKException {

        SmsModel model = cloudStorageService.getObject("TX_SMS_CONFIG_KEY", SmsModel.class);
        if (model == null)
            throw new ServiceException(500, "没有配置腾讯云短信信息");
        Credential cred = new Credential(model.getSecretId(), model.getSecretKey());
        SmsClient client = new SmsClient(cred, "");
        SendSmsRequest req = new SendSmsRequest();
        req.setSign(this.smsSign);
        req.setSmsSdkAppid(model.getSDKAppID()); //签名
        req.setTemplateID(templateId);  // 短信模板
        req.setPhoneNumberSet(mobiles);
        req.setTemplateParamSet(templatesParams);
        SendSmsResponse res = client.SendSms(req);
        System.out.println(SendSmsResponse.toJsonString(res));
        return false;
    }
}
