package cn.stylefeng.guns.apigateway.config;


import cn.stylefeng.guns.apigateway.core.rbrule.NacosSameClusterWeightedRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-30 17:53
 **/
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule() {
        return new NacosSameClusterWeightedRule();
    }
}
