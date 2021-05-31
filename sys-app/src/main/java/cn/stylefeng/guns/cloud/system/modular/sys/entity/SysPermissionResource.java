package cn.stylefeng.guns.cloud.system.modular.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 权限资源表
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@TableName("guns_sys_permission_resource")
public class SysPermissionResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限资源id
     */
    @TableId(value = "permission_resource_id", type = IdType.ID_WORKER)
    private Long permissionResourceId;

    /**
     * 权限ID
     */
    @TableField("permission_id")
    private Long permissionId;

    /**
     * 资源ID
     */
    @TableField("resource_id")
    private String resourceId;


    public Long getPermissionResourceId() {
        return permissionResourceId;
    }

    public void setPermissionResourceId(Long permissionResourceId) {
        this.permissionResourceId = permissionResourceId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "SysPermissionResource{" +
                "permissionResourceId=" + permissionResourceId +
                ", permissionId=" + permissionId +
                ", resourceId=" + resourceId +
                "}";
    }
}
