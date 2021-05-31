package cn.stylefeng.guns.cloud.payment.service.impl;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.payment.constant.PaymentConstant;
import cn.stylefeng.guns.cloud.payment.core.PayServiceFactory;
import cn.stylefeng.guns.cloud.payment.entity.Payment;
import cn.stylefeng.guns.cloud.payment.mapper.PaymentMapper;
import cn.stylefeng.guns.cloud.payment.model.param.CreateOrderRequest;
import cn.stylefeng.guns.cloud.payment.service.PaymentService;
import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-30 19:46
 **/

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {


    @Override
    public String createOrder(CreateOrderRequest request) throws AlipayApiException {
        Date now = DateTime.now();
        //创建支付订单，并提交到指定支付接口
        Payment entity = new Payment();
        ToolUtil.copyProperties(request, entity);
        entity.setOrderNo(ToolUtil.getIdGenLong().toString()); //设置订单号
        entity.setStatus(PaymentConstant.OrderStatus.NO_PAY.getValue());
        entity.setNotifyStatus(PaymentConstant.NotifyStatus.NO_NOTIFY.getValue());
        entity.setCreateTime(now);
        boolean b = save(entity);

        PaymentConstant.PayTypeEnum payTypeEnum = PaymentConstant.PayTypeEnum.AliPay;
        if (entity.getPayType() == 2) {
            payTypeEnum = PaymentConstant.PayTypeEnum.WxPay;
        }
        Object payBody = PayServiceFactory.builder(payTypeEnum).submitOrder(entity);

        return payBody.toString();
    }
}
