package cn.stylefeng.guns.cloud.member.config;

import cn.stylefeng.guns.cloud.libs.config.*;
import cn.stylefeng.guns.cloud.libs.web.error.DefaultExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {DataSourceConfig.class,
        CustomFastJsonConfig.class,
        MybatisPluginConfig.class,
        RedisConfig.class,
        RibbonConfig.class,
        ScannerConfig.class,
        SecurityConfiguration.class,
        SentinelConfig.class,})
public class AppConfig {
    /**
     * 默认的全局异常拦截器
     */
    @Bean
    public DefaultExceptionHandler defaultControllerExceptionHandler() {
        return new DefaultExceptionHandler();
    }
}
