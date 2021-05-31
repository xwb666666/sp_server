package cn.stylefeng.guns.cloud.system.modular.sys.enums;

import cn.stylefeng.guns.cloud.model.enums.exp.AbstractBaseException;

/**
 * 菜单信息异常枚举类
 *
 * @author fengshuonan
 * @Date 2018/2/9 15:46
 */
public enum MenuExceptionEnum implements AbstractBaseException {

    // 菜单异常信息
    MENU_NOT_FOUND(1001, "找不到菜单"),
    DICT_TYPE_EMPTY(1000, "菜单编码或名称重复");

    MenuExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
