package cn.stylefeng.guns.cloud.system.modular.ent.enums;

/**
 * 树节点类型的枚举
 *
 * @author stylefeng
 * @Date 2018年1月23日 15:20:33
 */
public enum EntNodeTypeEnum {
    /**
     * 公司，部门，人员节点的类型
     */
    COMPANY(1),
    DEPT(2),
    USER(3);

    /**
     * 状态码
     */
    private final Integer code;

    EntNodeTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
