package cn.stylefeng.guns.cloud.auth.modular.ssomgr.controller;

import cn.stylefeng.guns.cloud.auth.modular.ssomgr.service.SessionManageService;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


/**
 * 会话管理
 *
 * @author fengshuonan
 * @Date 2019/12/4 21:13
 */
@RestController
@Api(tags = "会话管理")
@ApiResource(name = "会话管理", path = "/session")
public class SessionManagerController {

    @Autowired
    private SessionManageService sessionManageService;

    /**
     * 获取在线用户信息
     *
     * @author fengshuonan
     * @Date 2019/12/4 21:13
     */
    @ApiOperation(value = "获取在线用户信息")
    @GetResource(name = "获取在线用户信息", path = "/pageList")
    public ResponseData pageList(@RequestParam(value = "userName", required = false) String userName) {
        Page sessionPageList = sessionManageService.pageList(userName);
        return ResponseData.success(sessionPageList);
    }

    /**
     * 强制用户下线
     *
     * @author fengshuonan
     * @Date 2019/12/4 21:13
     */
    @ApiOperation(value = "强制用户下线")
    @GetResource(name = "强制用户下线", path = "/forcedOffline")
    public ResponseData forcedOffline(@RequestParam("ticket") String ticket) {
        sessionManageService.forcedOffline(ticket);
        return ResponseData.success("强制下线成功");
    }
}
