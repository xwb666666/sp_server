package cn.stylefeng.guns.cloud.payment.config;

import cn.stylefeng.guns.cloud.libs.config.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-30 16:42
 **/
@Import(value = {DataSourceConfig.class,
        CustomFastJsonConfig.class,
        MybatisPluginConfig.class,
        RedisConfig.class,
        RibbonConfig.class,
        ScannerConfig.class,
        SecurityConfiguration.class,
        SentinelConfig.class})
@Configuration
public class AppConfig {
}
