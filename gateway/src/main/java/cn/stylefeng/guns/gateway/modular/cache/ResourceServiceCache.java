package cn.stylefeng.guns.gateway.modular.cache;

import cn.stylefeng.guns.cloud.model.resource.ResourceDefinition;
import cn.stylefeng.guns.cloud.system.api.keys.SysCacheKey;
import cn.stylefeng.guns.cloud.system.api.model.req.ResourceUrlReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 资源服务 缓存的实现
 *
 * @author fengshuonan
 * @Date 2019-08-12 18:52
 */
@Service
public class ResourceServiceCache {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过当前请求的
     *
     * @author fengshuonan
     * @Date 2019-09-11 14:37
     */
    public ResourceDefinition getResourceByUrl(ResourceUrlReq resourceUrlReq) {
        ResourceDefinition resourceDefinition = (ResourceDefinition) redisTemplate.boundHashOps(SysCacheKey.RES_CACHE_MAP).get(resourceUrlReq.getUrl());
        if (resourceDefinition != null) {
            return resourceDefinition;
        } else {
            return null;
        }
    }
}
