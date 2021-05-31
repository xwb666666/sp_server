package cn.stylefeng.guns.cloud.auth.modular.sso.enums;

/**
 * 操作枚举
 *
 * @author fengshuonan
 * @Date 2019/9/25 22:27
 */
public enum OperationEnum {

    LOGIN(1, "登录"),
    LOGIN_OUT(2, "退出");

    private final Integer code;
    private final String message;

    OperationEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
