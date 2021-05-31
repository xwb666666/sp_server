package cn.stylefeng.guns.cloud.system.modular.sys.model.params;

import lombok.Data;

import java.util.Date;

@Data
public class AdvertisingParam {

    private Long id;

    private String picture;

    private String skipUrl;

    private Integer skipType;   //跳转类型 0商品 1分类 2网页 3其它

    private Integer status;     //0启用 1禁用

}
