package cn.stylefeng.guns.cloud.system.modular.res.enums;

import cn.stylefeng.guns.cloud.model.enums.exp.AbstractBaseException;

/**
 * 应用信息异常枚举
 *
 * @author fengshuonan
 * @Date 2019-05-29 13:49
 */
public enum AppExceptionEnum implements AbstractBaseException {

    /**
     * 应用不存在
     */
    APP_NOT_FOUND(3103, "应用不存在!");

    AppExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;

    private final String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
