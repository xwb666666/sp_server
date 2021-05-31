package cn.stylefeng.guns.cloud.system.modular.sys.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@Data
@ApiModel
public class SysRolePermissionResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 角色权限id
     */
    @ApiModelProperty("角色权限id")
    private Long rolePermissionId;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Long roleId;

    /**
     * 权限ID
     */
    @ApiModelProperty("权限ID")
    private Long permissionId;

}
