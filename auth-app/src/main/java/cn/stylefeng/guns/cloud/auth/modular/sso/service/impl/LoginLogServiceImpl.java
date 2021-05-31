package cn.stylefeng.guns.cloud.auth.modular.sso.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.auth.api.model.LoginUser;
import cn.stylefeng.guns.cloud.auth.modular.sso.entity.AuthLoginLog;
import cn.stylefeng.guns.cloud.auth.modular.sso.enums.OperationEnum;
import cn.stylefeng.guns.cloud.auth.modular.sso.mapper.AuthLoginLogMapper;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.TokenInfo;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.result.AuthLoginLogResult;
import cn.stylefeng.guns.cloud.auth.modular.sso.service.LoginLogService;
import cn.stylefeng.guns.cloud.auth.modular.sso.util.IpAddressService;
import cn.stylefeng.guns.cloud.auth.modular.sso.util.IpInfoUtils;
import cn.stylefeng.guns.cloud.libs.mp.page.PageFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<AuthLoginLogMapper, AuthLoginLog> implements LoginLogService {

    @Autowired
    private IpAddressService ipAddressService;

    @Override
    public void loginLog(HttpServletRequest request, TokenInfo tokenInfo) {
        AuthLoginLog authLoginLog = createAuthLoginLog(request, tokenInfo, OperationEnum.LOGIN);
        baseMapper.insert(authLoginLog);
    }

    @Override
    public void logoutLog(HttpServletRequest request, TokenInfo tokenInfo) {
        AuthLoginLog authLoginLog = createAuthLoginLog(request, tokenInfo, OperationEnum.LOGIN_OUT);
        baseMapper.insert(authLoginLog);
    }

    private AuthLoginLog createAuthLoginLog(HttpServletRequest request, TokenInfo tokenInfo, OperationEnum operationEnum) {
        LoginUser loginUser = tokenInfo.getLoginUser();
        String ipAddr = IpInfoUtils.getIpAddr(request);
        AuthLoginLog authLoginLog = new AuthLoginLog();
        authLoginLog.setIpAddress(ipAddr);
        String address = ipAddressService.getCityInfo(ipAddr);
        authLoginLog.setLocalAddress(address != null ? address : "未找到");
        authLoginLog.setAccount(ObjectUtil.isNotEmpty(loginUser.getAccount()) ? loginUser.getAccount() : "");
        authLoginLog.setName(loginUser.getName());
        authLoginLog.setCompanyId(loginUser.getCompanyId());
        authLoginLog.setClientId(tokenInfo.getAuthClient().getClientId());
        authLoginLog.setOperation(operationEnum.getMessage());
        authLoginLog.setLoginTime(new Date());
        return authLoginLog;
    }

    @Override
    public Page pageList(String account) {
        Page page = PageFactory.defaultPage();
        List<Map<String, Object>> pageList = this.baseMapper.pageList(page, account);
        return page.setRecords(pageList);
    }

    @Override
    public AuthLoginLogResult detail(Long loginLogId) {
        return this.baseMapper.detail(loginLogId);
    }

}
