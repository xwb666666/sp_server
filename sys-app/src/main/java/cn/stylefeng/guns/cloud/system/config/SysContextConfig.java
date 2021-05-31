/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.cloud.system.config;

import cn.stylefeng.guns.cloud.libs.config.*;
import cn.stylefeng.guns.cloud.libs.context.SpringContext;
import cn.stylefeng.guns.cloud.libs.web.VirtualLoginUserAop;
import cn.stylefeng.guns.cloud.libs.web.error.GlobalErrorView;
import cn.stylefeng.guns.cloud.system.core.aop.BusinessLogAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * 默认激活的配置，基本上项目所有的配置都写在这里了
 * <p>
 * 此类通过Import开启了很多的配置，这些配置都是项目基本需要的
 * <p>
 * 如果某个微服务不需要某个配置，可以自己手动引入配置，不用这个类
 *
 * @author fengshuonan
 * @Date 2018/7/24 下午3:27
 */
@Configuration
@Import(value = {
        CustomWebConfig.class,
        CustomFastJsonConfig.class,
        LoginContextConfig.class,
        MybatisPluginConfig.class,
        RedisConfig.class,
        RibbonConfig.class,
        ScannerConfig.class,
        SecurityConfiguration.class,
        SentinelConfig.class,
        ValidatorConfig.class
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SysContextConfig {

    /**
     * Spring Bean 快速调用
     *
     * @author fengshuonan
     * @Date 2020/5/20 6:08 下午
     */
    @Bean
    public SpringContext springContext() {
        return new SpringContext();
    }

    /**
     * 虚拟的当前登陆用户AOP
     *
     * @author fengshuonan
     * @Date 2020/5/20 6:37 下午
     */
    @Bean
    public VirtualLoginUserAop virtualLoginUserAop() {
        return new VirtualLoginUserAop();
    }

    /**
     * 默认错误页面，返回json
     */
    @Bean("error")
    public GlobalErrorView error() {
        return new GlobalErrorView();
    }

    /**
     * 日志切面
     *
     * @author xuyuxiang
     * @date 2020/3/20 14:10
     */
    @Bean
    public BusinessLogAop businessLogAop() {
        return new BusinessLogAop();
    }

}