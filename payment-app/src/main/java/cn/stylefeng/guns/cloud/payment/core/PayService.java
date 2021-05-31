package cn.stylefeng.guns.cloud.payment.core;

import cn.stylefeng.guns.cloud.payment.entity.Payment;
import com.alipay.api.AlipayApiException;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-05-08 14:28
 **/
public interface PayService {
    //创建支付
    Object submitOrder(Payment payment) throws AlipayApiException;
    //接收支付通知
    void orderNotify(); //通知
}
