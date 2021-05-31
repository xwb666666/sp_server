package cn.stylefeng.guns.cloud.api.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品分类
 */
@Data
@TableName("guns_pro_category")
public class GunsProCategory {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    @TableField("short_name")
    private String shortName;
    private String icon;
    private Integer sort;
    private Long parentId;
}
