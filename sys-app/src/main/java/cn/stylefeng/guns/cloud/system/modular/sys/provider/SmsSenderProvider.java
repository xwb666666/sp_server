package cn.stylefeng.guns.cloud.system.modular.sys.provider;

import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.smsSender.SmsSender;
import cn.stylefeng.guns.cloud.system.modular.smsSender.SmsSenderFactory;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.springframework.web.bind.annotation.*;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-26 18:36
 **/
@RequestMapping("/smsProvider")
@RestController
public class SmsSenderProvider {


    @GetMapping("/sendCode")
    public boolean sendCode(@RequestParam String mobile, @RequestParam String templateId, @RequestParam String code) {
        SmsSender sms = SmsSenderFactory.builder();
        try {
            mobile = "+86" + mobile;
            boolean b = sms.sendMsg(new String[]{mobile},
                    templateId,
                    new String[]{code}
            );
        } catch (TencentCloudSDKException ex) {
            return false;
        }
        return true;
    }
}
