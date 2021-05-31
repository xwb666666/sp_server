package cn.stylefeng.guns.cloud.system.core.menus;

import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 权限管理菜单控制器
 *
 * @author fengshuonan
 * @Date 2019/9/17 17:47
 */
@RestController
@ApiResource(name = "资源权限菜单", path = "/authorityMenu")
public class AuthorityMenuFlagController {

    @ApiResource(name = "资源权限菜单", path = "/authorityMenu", menuFlag = true)
    public ResponseData authorityMenu() {
        return ResponseData.success();
    }

    @ApiResource(name = "角色管理", path = "/roleManager", menuFlag = true)
    public ResponseData roleManager() {
        return ResponseData.success();
    }

    @ApiResource(name = "权限管理", path = "/permissionManager", menuFlag = true)
    public ResponseData permissionManager() {
        return ResponseData.success();
    }

    @ApiResource(name = "菜单管理", path = "/menuManager", menuFlag = true)
    public ResponseData menuManager() {
        return ResponseData.success();
    }

    @ApiResource(name = "资源管理", path = "/resourceManager", menuFlag = true)
    public ResponseData resourceManager() {
        return ResponseData.success();
    }

}
