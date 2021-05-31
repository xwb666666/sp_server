package cn.stylefeng.guns.cloud.system.core.menus;

import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 系统管理菜单标识控制器
 *
 * @author fengshuonan
 * @Date 2019/9/17 17:47
 */
@RestController
@ApiResource(name = "系统菜单", path = "/systemMenu")
public class SystemMenuFlagController {

    @ApiResource(name = "系统管理菜单", path = "/system", menuFlag = true)
    public ResponseData system() {
        return ResponseData.success();
    }

    @ApiResource(name = "应用管理", path = "/appManager", menuFlag = true)
    public ResponseData appManager() {
        return ResponseData.success();
    }

    @ApiResource(name = "字典管理", path = "/dicManager", menuFlag = true)
    public ResponseData dicManager() {
        return ResponseData.success();
    }

}
