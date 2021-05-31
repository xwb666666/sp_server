package cn.stylefeng.guns.cloud.monitor;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 监控中心启动类
 *
 * @author stylefeng
 * @Date 2018/6/24 22:28
 */
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class GunsCloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunsCloudAdminApplication.class, args);
    }
}

