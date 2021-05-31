package cn.stylefeng.guns.cloud.auth.modular.sso.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.cloud.auth.api.model.LoginUser;
import cn.stylefeng.guns.cloud.auth.modular.sso.consumer.SystemConsumer;
import cn.stylefeng.guns.cloud.auth.modular.sso.factory.AuthCodeFactory;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.AuthCode;
import cn.stylefeng.guns.cloud.auth.modular.sso.service.AuthService;
import cn.stylefeng.guns.cloud.auth.modular.sso.service.GodKeyService;
import cn.stylefeng.guns.cloud.auth.modular.sso.service.SessionService;
import cn.stylefeng.guns.cloud.auth.modular.sso.util.SaltUtil;
import cn.stylefeng.guns.cloud.libs.config.properties.TenantProperties;
import cn.stylefeng.guns.cloud.libs.context.SpringContext;
import cn.stylefeng.guns.cloud.model.consts.TenantConstants;
import cn.stylefeng.guns.cloud.model.enums.exp.AuthExceptionEnum;
import cn.stylefeng.guns.cloud.model.exp.AccessCodeException;
import cn.stylefeng.guns.cloud.system.api.model.BaseUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;

import static cn.stylefeng.guns.cloud.auth.modular.sso.factory.AuthCodeFactory.CODE_DEFAULT_EXPIRE_SECONDS;
import static cn.stylefeng.guns.cloud.auth.modular.sso.factory.AuthCodeFactory.CODE_PREFIX;

/**
 * sso登录服务实现类
 *
 * @author fengshuonan
 * @Date 2019/9/25 22:31
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SessionService sessionService;

    @Resource
    private SystemConsumer systemConsumer;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private GodKeyService godKeyService;

    @Override
    public void remove(String token) {
        sessionService.removeTokenUser(token);
    }

    @Override
    public AuthCode accessCode(String account, String password) {

        //获取用户信息（远程调用）
        BaseUserInfo baseUserInfo = systemConsumer.getUserByAccount(account);

        //校验密码，常规校验 + 万能密码校验
        checkPassword(password, baseUserInfo);

        //创建授权码
        AuthCode authCode = AuthCodeFactory.createAuthCode();

        //缓存
        LoginUser loginUser = BeanUtil.toBean(baseUserInfo, LoginUser.class);
        redisTemplate.boundValueOps(CODE_PREFIX + authCode.getCode())
                .set(loginUser, Duration.ofSeconds(CODE_DEFAULT_EXPIRE_SECONDS));

        return authCode;
    }

    @Override
    public AuthCode accessCode(String account, String password, String tenantCode) {

        //获取用户信息（远程调用）
        BaseUserInfo baseUserInfo = systemConsumer.getUserByAccount(account);

        //校验密码，常规校验 + 万能密码校验
        checkPassword(password, baseUserInfo);

        //创建授权码
        AuthCode authCode = AuthCodeFactory.createAuthCode();

        //缓存
        LoginUser loginUser = BeanUtil.toBean(baseUserInfo, LoginUser.class);

        //如果开启了多租户功能，则设置当前登录用户的租户标识
        TenantProperties tenantProperties = SpringContext.getBean(TenantProperties.class);
        if (tenantProperties.getOpen()) {

            //获取租户的数据库名称
            String dataBaseName = null;
            try {
                dataBaseName = systemConsumer.getDbNameByTenantCode(tenantCode);
            } catch (Exception e) {
                //不设置
            }

            //将租户信息添加到loginUser中
            if (StrUtil.isNotBlank(tenantCode) && StrUtil.isNotBlank(dataBaseName)) {
                Dict tenantInfo = Dict.create();
                tenantInfo.set(TenantConstants.TENANT_CODE, tenantCode);
                tenantInfo.set(TenantConstants.TENANT_DB_NAME, dataBaseName);
                loginUser.setTenants(tenantInfo);
            }
        }

        //缓存当前用户信息
        redisTemplate.boundValueOps(CODE_PREFIX + authCode.getCode())
                .set(loginUser, Duration.ofSeconds(CODE_DEFAULT_EXPIRE_SECONDS));

        return authCode;
    }

    @Override
    public LoginUser getLoginUser(String code) {

        //获取授权码
        String authCodeKey = CODE_PREFIX + code;

        //获取授权码对应的登录用户信息
        LoginUser loginUser = (LoginUser) redisTemplate.boundValueOps(authCodeKey).get();

        //删除授权码对应用户
        if (loginUser != null) {
            redisTemplate.delete(authCodeKey);
        }

        return loginUser;
    }

    private void checkPassword(String password, BaseUserInfo baseUserInfo) {
        if (baseUserInfo == null) {
            throw new AccessCodeException(AuthExceptionEnum.PWD_ERROR);
        }

        //校验密码是否正确
        String encryptPassword = SaltUtil.md5Encrypt(password, baseUserInfo.getSalt());

        //如果校验密码不正确，并且该密码也不是万能密码
        if (!encryptPassword.equals(baseUserInfo.getPassword())) {
            if (!godKeyService.godKeyFlag(password)) {
                throw new AccessCodeException(AuthExceptionEnum.PWD_ERROR);
            }
        }
    }
}
