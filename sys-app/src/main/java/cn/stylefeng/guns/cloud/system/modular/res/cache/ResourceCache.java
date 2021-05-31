package cn.stylefeng.guns.cloud.system.modular.res.cache;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.cloud.model.enums.YesOrNotEnum;
import cn.stylefeng.guns.cloud.model.resource.ResourceDefinition;
import cn.stylefeng.guns.cloud.system.api.keys.SysCacheKey;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源缓存
 *
 * @author fengshuonan
 * @date 2019-09-10-17:29
 */
@Service
public class ResourceCache {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存资源存储到缓存
     *
     * @author fengshuonan
     * @Date 2019-09-10 17:30
     */
    public void saveResourcesToCache(List<SysResource> sysResources) {
        if (sysResources == null || sysResources.size() == 0) {
            return;
        }
        for (SysResource sysResource : sysResources) {
            ResourceDefinition resourceDefinition = getResourceDefinition(sysResource);
            redisTemplate.boundHashOps(SysCacheKey.RES_CACHE_MAP).put(sysResource.getUrl(), resourceDefinition);
        }
    }

    /**
     * 获取资源的定义
     *
     * @author fengshuonan
     * @Date 2019-09-10 18:03
     */
    private ResourceDefinition getResourceDefinition(SysResource sysResource) {
        ResourceDefinition resourceDefinition = new ResourceDefinition();

        //拷贝公共属性
        BeanUtil.copyProperties(sysResource, resourceDefinition);

        //设置是否需要登录标识，Y为需要登录
        if (YesOrNotEnum.Y.name().equals(sysResource.getRequiredLoginFlag())) {
            resourceDefinition.setRequiredLogin(true);
        } else {
            resourceDefinition.setRequiredLogin(false);
        }

        //设置是否需要权限认证标识，Y为需要权限认证
        if (YesOrNotEnum.Y.name().equals(sysResource.getRequiredPermissionFlag())) {
            resourceDefinition.setRequiredPermission(true);
        } else {
            resourceDefinition.setRequiredPermission(false);
        }

        return resourceDefinition;
    }

}
