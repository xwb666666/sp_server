package cn.stylefeng.guns.cloud.auth.config;

import cn.stylefeng.guns.cloud.libs.config.properties.TenantProperties;
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
public class TenantConfig {

    @Bean
    @ConfigurationProperties(prefix = "roses.tenant")
    public TenantProperties tenantProperties() {
        return new TenantProperties();
    }

}
