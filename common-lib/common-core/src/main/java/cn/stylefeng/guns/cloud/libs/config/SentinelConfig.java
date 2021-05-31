package cn.stylefeng.guns.cloud.libs.config;

import cn.stylefeng.guns.cloud.libs.cloud.sentinel.RestfulUrlCleaner;
import cn.stylefeng.guns.cloud.libs.cloud.sentinel.SentinelBlockHandler;
import cn.stylefeng.guns.cloud.libs.cloud.sentinel.SentinelOriginParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sentinel配置
 *
 * @author fengshuonan
 * @Date 2020/2/11 21:20
 */
@Configuration
public class SentinelConfig {

    @Bean
    public RestfulUrlCleaner restfulUrlCleaner() {
        return new RestfulUrlCleaner();
    }

    @Bean
    public SentinelBlockHandler sentinelBlockHandler() {
        return new SentinelBlockHandler();
    }

    @Bean
    public SentinelOriginParser sentinelOriginParser() {
        return new SentinelOriginParser();
    }

}
