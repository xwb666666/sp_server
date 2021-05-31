package cn.stylefeng.guns.cloud.auth.modular.ssomgr.controller;

import cn.stylefeng.guns.cloud.auth.modular.sso.model.result.AuthLoginLogResult;
import cn.stylefeng.guns.cloud.auth.modular.sso.service.LoginLogService;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.libs.web.base.BaseController;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 登录日志表控制器
 *
 * @author fengshuonan
 * @Date 2019/12/4 21:11
 */
@RestController
@Api(tags = "登录日志管理")
@ApiResource(name = "登录日志管理", path = "/authLoginLog")
public class AuthLoginLogController extends BaseController {

    @Autowired
    private LoginLogService authLoginLogService;

    /**
     * 查看日志列表（分页）
     *
     * @author fengshuonan
     * @Date 2019/12/4 21:11
     */
    @ApiOperation("查看日志列表（分页）")
    @GetResource(name = "查看日志列表（分页）", path = "/pageList")
    public ResponseData pageList(@RequestParam(value = "account", required = false) String account) {
        Page pageList = authLoginLogService.pageList(account);
        return ResponseData.success(pageList);
    }

    /**
     * 查看日志详情
     *
     * @author fengshuonan
     * @Date 2019/12/4 21:12
     */
    @ApiOperation(value = "日志详情", response = AuthLoginLogResult.class)
    @GetResource(name = "日志详情", path = "/detail")
    public ResponseData detail(@RequestParam("loginLogId") Long loginLogId) {
        AuthLoginLogResult detail = this.authLoginLogService.detail(loginLogId);
        return ResponseData.success(detail);
    }

}


