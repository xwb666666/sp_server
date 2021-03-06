package cn.stylefeng.guns.cloud.system.modular.mgr.listener;

import cn.stylefeng.guns.cloud.system.modular.mgr.flag.InitManagerFlag;
import cn.stylefeng.guns.cloud.system.modular.mgr.service.InitManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 监听项目启动,初始化系统默认管理员
 *
 * @author fengshuonan
 * @date 2018-02-06 13:05
 */
@Component
@Slf4j
public class InitManagerListener implements ApplicationListener<ApplicationReadyEvent>, Ordered {

    @Autowired
    private InitManagerService initManagerService;

    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        if (!InitManagerFlag.getFlag()) {
            log.info("开始初始化guns-cloud-system需要的数据！");

            //dev环境初始化系统管理员账号
            if (profile.equalsIgnoreCase("dev")) {
                initManagerService.initSystemManagerAccount();
            }

            log.info("初始化guns-cloud-system需要的数据完毕！");
            InitManagerFlag.setFlag();
        }

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
