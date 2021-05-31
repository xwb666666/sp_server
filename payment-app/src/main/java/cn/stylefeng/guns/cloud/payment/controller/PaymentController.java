package cn.stylefeng.guns.cloud.payment.controller;

import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.payment.model.param.CreateOrderRequest;
import cn.stylefeng.guns.cloud.payment.service.PaymentService;
import com.alipay.api.AlipayApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-30 17:29
 **/
@RestController
@RequestMapping("/api")
public class PaymentController {

    @Resource
    PaymentService service;

    @PostMapping("/createOrder")
    public ResponseData createOrder(@RequestBody CreateOrderRequest request) {
        try {
            String body = service.createOrder(request);
            return ResponseData.success(body);
        } catch (AlipayApiException ex) {
            return ResponseData.error(ex.getMessage());
        }
    }
}
