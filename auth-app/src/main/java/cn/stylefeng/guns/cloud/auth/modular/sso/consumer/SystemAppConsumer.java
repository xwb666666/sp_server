package cn.stylefeng.guns.cloud.auth.modular.sso.consumer;

import cn.stylefeng.guns.cloud.system.api.SystemAppApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 系统应用app消费者
 *
 * @author fengshuonan
 * @Date 2019/12/4 20:30
 */
@FeignClient(name = "guns-cloud-system")
public interface SystemAppConsumer extends SystemAppApi {
}
