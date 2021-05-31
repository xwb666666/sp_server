package cn.stylefeng.guns.cloud.system.modular.sys.model.params;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
public class ShowcaseParam {
    private Long id;

    private String picture;

    private String skipUrl;

    private Integer skipType;

    private Integer status;





}
