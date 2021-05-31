package cn.stylefeng.guns.cloud.system.core.menus;

import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 系统监控菜单控制器
 *
 * @author fengshuonan
 * @Date 2019/9/17 17:47
 */
@RestController
@ApiResource(name = "系统监控", path = "/systemMonitorMenu")
public class MonitorMenuFlagController {

    @ApiResource(name = "系统监控菜单（一级）", path = "/sysMonitor", menuFlag = true)
    public ResponseData sysMonitor() {
        return ResponseData.success();
    }

    @ApiResource(name = "druid监控", path = "/druidMenu", menuFlag = true)
    public ResponseData druidMenu() {
        return ResponseData.success();
    }

    @ApiResource(name = "硬件监控", path = "/hardInfoMenu", menuFlag = true)
    public ResponseData hardInfoMenu() {
        return ResponseData.success();
    }

}
