package cn.stylefeng.guns.gateway.core.exception;

import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.gateway.core.exception.enums.GatewayExceptionEnum;

/**
 * 网关的业务异常封装
 *
 * @author fengshuonan
 * @Date 2019/5/12 21:14
 */
public class GunsGatewayException extends ServiceException {

    public GunsGatewayException(GatewayExceptionEnum exception) {
        super(exception);
    }

}
