package cn.stylefeng.guns.cloud.auth.modular.sso.security.exception;

import cn.stylefeng.guns.cloud.model.enums.exp.AbstractBaseException;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;

/**
 * 安全问题异常
 *
 * @author fengshuonan
 * @date 2019-02-19-5:48 PM
 */
public class SecurityException extends ServiceException {

    public SecurityException(AbstractBaseException exception) {
        super(exception);
    }

    public SecurityException(Integer code, String errorMessage) {
        super(code, errorMessage);
    }
}
