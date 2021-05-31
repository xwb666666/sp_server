package cn.stylefeng.guns.cloud.system.modular.sys.controller;

import cn.stylefeng.guns.cloud.auth.api.model.LoginUser;
import cn.stylefeng.guns.cloud.libs.context.auth.LoginContext;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.model.web.response.SuccessResponseData;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 鉴权相关的控制器
 *
 * @author fengshuonan
 * @Date 2019/5/18 21:04
 */
@ApiResource(name = "系统信息", path = "/system")
@RestController
public class SystemController {

    /**
     * 获取用户基本信息
     *
     * @author fengshuonan
     * @date 2020/8/1 23:25
     */
    @GetResource(name = "获取用户基本信息", path = "/getUserInfo")
    @ResponseBody
    public ResponseData getUserInfo() {
        LoginUser user = LoginContext.me().getLoginUser();
        return new SuccessResponseData(user);
    }

}
