package cn.stylefeng.guns.cloud.payment.service;

import cn.stylefeng.guns.cloud.payment.entity.Payment;
import cn.stylefeng.guns.cloud.payment.model.param.CreateOrderRequest;
import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-30 19:45
 **/

public interface PaymentService extends IService<Payment> {
    String  createOrder(CreateOrderRequest request) throws AlipayApiException;
}
