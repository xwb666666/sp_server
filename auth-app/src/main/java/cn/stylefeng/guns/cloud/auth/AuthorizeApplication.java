package cn.stylefeng.guns.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主启动类
 *
 * @author fengshuonan
 * @Date 2019/2/15 3:41 PM
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.stylefeng.guns.cloud.auth.modular.sso.consumer", "cn.stylefeng.guns.cloud.auth.modular.ssomgr.consumer"})
@EnableScheduling
public class AuthorizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizeApplication.class, args);
    }

}