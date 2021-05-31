package cn.stylefeng.guns.cloud.order.model.result;

import lombok.Data;

import java.util.Date;

@Data
public class OrderLogResult {
    
    private String orderNo;

    private Integer userType;

    private Long userId;

    private String context;

    private Date createTime;
}
