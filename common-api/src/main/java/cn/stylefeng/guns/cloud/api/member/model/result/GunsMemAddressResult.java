package cn.stylefeng.guns.cloud.api.member.model.result;


import lombok.Data;

import java.io.Serializable;

@Data
public class GunsMemAddressResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;    //名称
    private String phone;   //手机号码
    private Long areaId;    //区域id
    private String area;    //区域名称
    private String address; //详细地址
    private Integer isDefault;//是否设为默认地址 0是  1否
}
