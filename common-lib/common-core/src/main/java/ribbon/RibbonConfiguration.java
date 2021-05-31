package ribbon;

import cn.stylefeng.guns.cloud.libs.cloud.rbrule.NacosSameClusterWeightedRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ribbon的配置
 *
 * @author fengshuonan
 * @Date 2019/8/13 21:19
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new NacosSameClusterWeightedRule();
    }

}
