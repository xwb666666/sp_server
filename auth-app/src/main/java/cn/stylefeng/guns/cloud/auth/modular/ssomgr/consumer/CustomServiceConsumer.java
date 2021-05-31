package cn.stylefeng.guns.cloud.auth.modular.ssomgr.consumer;

import cn.stylefeng.guns.cloud.system.api.ResourceServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 资源服务
 *
 * @author fengshuonan
 * @Date 2019/12/4 21:10
 */
@FeignClient(name = "guns-cloud-system")
public interface CustomServiceConsumer extends ResourceServiceApi {
}
