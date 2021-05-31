package cn.stylefeng.guns.cloud.system.modular.sys.model.result;

import lombok.Data;

import java.util.Date;

@Data
public class ShowcaseResult {

    private Long id;

    private String picture;

    private String skipUrl;

    private Integer skipType;

    private Integer status;     //启用 禁用

    private Date createTime;

}
