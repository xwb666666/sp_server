package cn.stylefeng.guns.cloud.api.product.model.result;

import lombok.Data;

import java.util.Date;

@Data
public class ApiShowcaseResult {

    private String picture;

    private String skipUrl;

    private Integer skipType;

    private Integer status;     //启用 禁用

    private Date createTime;

}
