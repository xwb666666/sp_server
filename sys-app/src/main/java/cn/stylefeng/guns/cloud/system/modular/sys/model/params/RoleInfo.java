package cn.stylefeng.guns.cloud.system.modular.sys.model.params;

import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色信息
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
@Data
public class RoleInfo implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    @Override
    public String checkParam() {
        return null;
    }

}
