package cn.stylefeng.guns.cloud.libs.config;
import cn.stylefeng.guns.cloud.auth.api.AuthService;
import cn.stylefeng.guns.cloud.libs.context.auth.LoginContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 获取当前登录用户
 * <p>
 * 注意：如果开启本类的配置，需要在微服务中添加一个Feign的客户端 AuthServiceConsumer
 * <p>
 * 参考各个项目中的 AuthServiceConsumer 类
 *
 * @author fengshuonan
 * @Date 2020/2/11 22:33
 */
@Configuration
public class LoginContextConfig {

    @Bean
    public LoginContext loginContext(AuthService authService) {
        return new LoginContext(authService);
    }

}
