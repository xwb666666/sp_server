package cn.stylefeng.guns.cloud.system.modular.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("guns_advertising")
public class Advertising {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String picture;

    private String skipUrl;

    private Integer skipType;

    private Integer status;

    private Date createTime;

}
