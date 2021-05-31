package cn.stylefeng.guns.cloud.system.modular.sys.controller;


import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.sys.model.AlipayModel;
import cn.stylefeng.guns.cloud.system.modular.sys.model.WXPayModel;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AlipayConfig;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.WXPayConfig;
import cn.stylefeng.guns.cloud.system.modular.sys.service.CloudStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiResource(name = "支付配置", path = "/config/pay")
@Api(tags = "支付配置")
public class PayTypeController {
    @Autowired
    private CloudStorageService cloudStorageService;
    //微信支付配置key
    private String WXkey="WXPAY_TYPE_CONFIG_KEY";

    //支付宝支付配置key
    private String AliKey="AliPAY_TYPE_CONFIG_KEY";

    @ApiOperation("获取微信支付配置")
    @GetResource(name = "获取微信支付配置", path = "/selectWXPay")
    public ResponseData WXstorage() {
        return ResponseData.success(cloudStorageService.getObject(WXkey, WXPayModel.class));
    }


    @PostResource(name = "微信支付配置", path = "/WXPay")
    @ApiOperation("微信支付配置")
    public ResponseData WXPay(@RequestBody WXPayConfig wxPayConfig) {

        cloudStorageService.addOrUpdate(WXkey, wxPayConfig);
        return ResponseData.success();

    }


    @ApiOperation("获取支付宝支付配置")
    @GetResource(name = "获取支付宝支付配置", path = "/selectAliPay")
    public ResponseData Alistorage() {
        return ResponseData.success(cloudStorageService.getObject(AliKey, AlipayModel.class));
    }


    @PostResource(name = "支付宝支付配置", path = "/AliPay")
    @ApiOperation("支付宝支付配置")
    public ResponseData AliPay(@RequestBody AlipayConfig alipayConfig) {

        cloudStorageService.addOrUpdate(AliKey, alipayConfig);
        return ResponseData.success();

    }




}
