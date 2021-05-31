package cn.stylefeng.guns.cloud.system.modular.file.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 文件信息表
 * </p>
 *
 * @author fengshuonan
 * @since 2018-02-28
 */
@TableName("guns_fileInfo")
@Data
public class Fileinfo extends Model<Fileinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    @TableId("file_id")
    private Long fileId;

    /**
     * 文件名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件大小
     */
    @TableField("file_size")
    private Long fileSize;

    /**
     * 文件存储路径
     */
    @TableField("file_final_name")
    private String fileFinalName;

    /**
     * 文件后缀
     */
    @TableField("file_suffix")
    private String fileSuffix;

    /**
     * 应用编码
     */
    @TableField("app_code")
    private String appCode;

    /**
     * 应用名称
     */
    @TableField("app_name")
    private String appName;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField("update_user")
    private Long updateUser;

    /**
     * 删除标记(Y:已删除 N:未删除)
     */
    @TableField("del_flag")
    private String delFlag;

}
