package cn.stylefeng.guns.cloud.system.modular.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@TableName("guns_sys_area")
@Data
public class SysArea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域id
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    private String name;

    private Long parentId;

}
