package cn.stylefeng.guns.cloud.system.core.menus;

import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 日志菜单
 *
 * @author fengshuonan
 * @Date 2019/9/17 17:47
 */
@RestController
@ApiResource(name = "日志管理", path = "/logMenu")
public class LogMenuFlagController {

    @ApiResource(name = "日志管理菜单（一级）", path = "/logsMenu", menuFlag = true)
    public ResponseData logsMenu() {
        return ResponseData.success();
    }

    @ApiResource(name = "业务日志", path = "/bizMenu", menuFlag = true)
    public ResponseData bizMenu() {
        return ResponseData.success();
    }

    @ApiResource(name = "登录日志", path = "/loginMenu", menuFlag = true)
    public ResponseData loginMenu() {
        return ResponseData.success();
    }

}
