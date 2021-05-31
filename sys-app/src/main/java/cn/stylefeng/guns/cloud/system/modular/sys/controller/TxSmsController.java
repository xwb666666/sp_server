package cn.stylefeng.guns.cloud.system.modular.sys.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.smsSender.SmsSender;
import cn.stylefeng.guns.cloud.system.modular.smsSender.SmsSenderFactory;
import cn.stylefeng.guns.cloud.system.modular.sys.model.SmsModel;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.TxSmsConfig;
import cn.stylefeng.guns.cloud.system.modular.sys.service.CloudStorageService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiResource(name = "短信配置接口", path = "/config/sms")
@Api(tags = "短信配置接口")
public class TxSmsController {

    @Autowired
    private CloudStorageService cloudStorageService;

    private String param_key = "TX_SMS_CONFIG_KEY";

    @ApiOperation("获取短信配置")
    @GetResource(name = "获取短信配置", path = "/selectsms")
    public ResponseData storage() {
        SmsModel selectsms = cloudStorageService.getObject(param_key, SmsModel.class);
        return ResponseData.success(selectsms);
    }


    @PostResource(name = "短信配置", path = "/TxSms")
    @ApiOperation("短信配置")
    public ResponseData TxSms(@Validated @RequestBody TxSmsConfig txSmsConfig) {

        cloudStorageService.addOrUpdate(param_key, txSmsConfig);
        return ResponseData.success();

    }

    @PostResource(name = "测试短信发送", path = "/testSend")
    @ApiOperation("测试发送短信")
    public ResponseData testSms()
    {
        SmsSender sms=SmsSenderFactory.builder();
        try {
            boolean b= sms.sendMsg(new String[]{"+8618616721366"},
                    "937950",
                    new String[]{"238798"}
            );
        }
        catch (TencentCloudSDKException ex)
        {
            return ResponseData.error(ex.toString());
        }
        return ResponseData.success();

    }


}

