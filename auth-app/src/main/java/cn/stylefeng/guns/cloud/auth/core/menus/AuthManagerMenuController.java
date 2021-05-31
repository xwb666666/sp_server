package cn.stylefeng.guns.cloud.auth.core.menus;

import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 权限管理标识控制器
 *
 * @author fengshuonan
 * @Date 2019/12/5 20:57
 */
@RestController
@ApiResource(name = "权限管理所有菜单标识", path = "/authManagerMenuFlag")
public class AuthManagerMenuController {

    @ApiResource(name = "单点应用", path = "/ssoApp", menuFlag = true)
    public ResponseData ssoApp() {
        return ResponseData.success();
    }

    @ApiResource(name = "客户端管理", path = "/clientManager", menuFlag = true)
    public ResponseData clientManager() {
        return ResponseData.success();
    }

    @ApiResource(name = "安全审计", path = "/safetyAudit", menuFlag = true)
    public ResponseData safetyAudit() {
        return ResponseData.success();
    }

    @ApiResource(name = "登录日志", path = "/loginLog", menuFlag = true)
    public ResponseData loginLog() {
        return ResponseData.success();
    }

    @ApiResource(name = "会话管理", path = "/sessionManage", menuFlag = true)
    public ResponseData sessionManage() {
        return ResponseData.success();
    }

    @ApiResource(name = "资源维护", path = "/resMaintain", menuFlag = true)
    public ResponseData resMaintain() {
        return ResponseData.success();
    }

    @ApiResource(name = "万能密码", path = "/godKey", menuFlag = true)
    public ResponseData godKey() {
        return ResponseData.success();
    }
}
