package cn.stylefeng.guns.cloud.api.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 分组
 */
@Data
@TableName("guns_pro_group")
public class GunsProGroup {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer status;
    private Date creatDate;
}
