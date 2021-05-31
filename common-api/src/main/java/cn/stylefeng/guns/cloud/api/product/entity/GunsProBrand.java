package cn.stylefeng.guns.cloud.api.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author stylefeng
 * @since 2021-03-23
 */
@TableName("guns_pro_brand")
@Data
public class GunsProBrand implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 品牌id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 品牌名称
     */
    @TableField("name")
    private String name;

    /**
     * 图片
     */
    @TableField("picture")
    private String picture;

    /**
     * 排序
     */
    @TableField("sort")
    private Long sort;

    /**
     * 状态 0删除 1正常 2下架
     */
    @TableField("status")
    private Integer status;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 是否是独有品牌  0否 1是
     */
    @TableField("is_private")
    private Integer isPrivate;

    /**
     * 添加时间
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @TableField("update_date")
    private Date updateDate;

    /**
     * 首字母
     */
    @TableField("first_char")
    private String firstChar;



}
