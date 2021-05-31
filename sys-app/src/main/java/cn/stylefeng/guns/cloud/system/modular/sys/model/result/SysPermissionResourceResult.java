package cn.stylefeng.guns.cloud.system.modular.sys.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 权限资源表
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@Data
@ApiModel
public class SysPermissionResourceResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 权限资源id
     */
    @ApiModelProperty("权限资源id")
    private Long permissionResourceId;

    /**
     * 权限ID
     */
    @ApiModelProperty("权限ID")
    private Long permissionId;

    /**
     * 资源ID
     */
    @ApiModelProperty("资源ID")
    private String resourceId;

}
