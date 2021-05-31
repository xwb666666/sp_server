package cn.stylefeng.guns.cloud.system.modular.sys.model.result;

import lombok.Data;

import java.util.Date;

@Data
public class AdvertisingResult {

    private Long id;            //id
    private String picture;     //图片

    private String skipUrl;     //跳转地址

    private Integer skipType;    //跳转类型 0商品 1分类 2网页 3其它

    private Integer status;     //0启用 1禁用

    private Date createTime;        //创建时间

}
