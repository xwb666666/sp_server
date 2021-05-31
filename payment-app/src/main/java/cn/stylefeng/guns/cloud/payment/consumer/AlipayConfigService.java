package cn.stylefeng.guns.cloud.payment.consumer;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @program: guns-cloud-parent
 * @description 支付宝支付配置
 * @author: wzx
 * @create: 2021-05-09 11:02
 **/


@FeignClient("guns-cloud-system")
public class AlipayConfigService {
}
