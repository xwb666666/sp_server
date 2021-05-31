package cn.stylefeng.guns.cloud.api.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@TableName("guns_pro_sku")
public class GunsProSku implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("sku_names")
    private String skuNames;

    @TableField("sku_name_ids")
    private String  skuNameIds;

    @TableField("product_id")
    private Long productId;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("centont")
    private String centont;

    @TableField("picture")
    private String picture;

    @TableField("cost")
    private Double cost;

    @TableField("original_price")
    private Double originalPrice;

    @TableField("present_price")
    private Double presentPrice;

    @TableField("stock")
    private Double stock;

    @TableField("create_date")
    private Date createDate;

    @TableField("update_date")
    private  Date updateDate;
}
