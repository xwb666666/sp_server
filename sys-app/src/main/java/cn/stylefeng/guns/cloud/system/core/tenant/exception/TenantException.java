package cn.stylefeng.guns.cloud.system.core.tenant.exception;


import cn.stylefeng.guns.cloud.model.enums.exp.AbstractBaseException;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;

/**
 * 多租户的异常
 *
 * @author fengshuonan
 * @date 2020/9/3
 */
public class TenantException extends ServiceException {

    public TenantException(AbstractBaseException exception) {
        super(exception);
    }

}
