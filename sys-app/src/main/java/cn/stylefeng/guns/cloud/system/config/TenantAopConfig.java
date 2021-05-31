package cn.stylefeng.guns.cloud.system.config;

import cn.stylefeng.guns.cloud.libs.config.properties.TenantProperties;
import cn.stylefeng.guns.cloud.system.core.tenant.aop.TenantSourceExAop;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 多租户的配置
 *
 * @author fengshuonan
 * @date 2020/9/3 22:02
 */
@Configuration
public class TenantAopConfig {

    @Bean
    @ConfigurationProperties(prefix = "roses.tenant")
    public TenantProperties tenantProperties() {
        return new TenantProperties();
    }

    /**
     * 多租户切换aop的配置
     *
     * @author fengshuonan
     * @date 2020/9/3 22:03
     */
    @Bean
    public TenantSourceExAop tenantSourceExAop() {
        return new TenantSourceExAop();
    }

}
