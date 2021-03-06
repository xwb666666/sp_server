package cn.stylefeng.guns.cloud.system.modular.dbs.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 数据库信息表
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-15
 */
@TableName("guns_sys_database_info")
@Data
public class DatabaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 数据库名称（英文名称）
     */
    @TableField("db_name")
    private String dbName;

    /**
     * jdbc的驱动类型
     */
    @TableField("jdbc_driver")
    private String jdbcDriver;

    /**
     * 数据库连接的账号
     */
    @TableField("user_name")
    private String userName;

    /**
     * 数据库连接密码
     */
    @TableField("password")
    private String password;

    /**
     * jdbc的url
     */
    @TableField("jdbc_url")
    private String jdbcUrl;

    /**
     * 备注，摘要
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

}
