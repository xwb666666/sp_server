package cn.stylefeng.guns.cloud.payment.core;

import cn.stylefeng.guns.cloud.payment.constant.PaymentConstant;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-05-08 14:51
 **/
public class PayServiceFactory {
    public static PayService builder(PaymentConstant.PayTypeEnum payType)
    {
            switch (payType)
            {
                case AliPay:
                    return new AlipayService();
                case WxPay:
                    return null;
                default:
                    throw new RuntimeException("没有实现的支付接口");
            }
    }
}
