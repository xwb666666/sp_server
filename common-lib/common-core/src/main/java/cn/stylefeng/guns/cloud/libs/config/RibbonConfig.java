package cn.stylefeng.guns.cloud.libs.config;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbon.RibbonConfiguration;

/**
 * ribbon配置（defaultConfiguration对应类必须加@Configuration注解，并且需要放在父级上下文无法扫描到的地方）
 * <p>
 * 配置生效顺序说明：全局代码 < 全局属性 < 细粒度代码 < 细粒度属性
 *
 * @author fengshuonan
 * @Date 2019/8/13 22:02
 */
@Configuration
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class RibbonConfig {
}
