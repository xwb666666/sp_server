package cn.stylefeng.guns.cloud.model.exp;

import cn.stylefeng.guns.cloud.model.enums.exp.AuthExceptionEnum;
import lombok.Getter;

/**
 * 认证失败异常
 *
 * @author fengshuonan
 * @Date 2019/4/19 21:38
 */
@Getter
public class AccessCodeException extends ServiceException {

    private final AuthExceptionEnum authExceptionEnum;

    public AccessCodeException(AuthExceptionEnum authExceptionEnum) {
        super(authExceptionEnum);
        this.authExceptionEnum = authExceptionEnum;
    }

    public AuthExceptionEnum getAuthExceptionEnum() {
        return authExceptionEnum;
    }
}
