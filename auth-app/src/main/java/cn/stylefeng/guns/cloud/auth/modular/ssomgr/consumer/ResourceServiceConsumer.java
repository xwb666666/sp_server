package cn.stylefeng.guns.cloud.auth.modular.ssomgr.consumer;

import cn.stylefeng.guns.cloud.system.api.ResourceService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 资源服务
 *
 * @author fengshuonan
 * @Date 2019/12/3 22:30
 */
@FeignClient(name = "guns-cloud-system")
public interface ResourceServiceConsumer extends ResourceService {
}
