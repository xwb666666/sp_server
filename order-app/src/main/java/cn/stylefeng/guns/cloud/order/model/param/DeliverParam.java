package cn.stylefeng.guns.cloud.order.model.param;

import lombok.Data;

import java.util.List;

@Data
public class DeliverParam {

    private String orderNo;

    private List<Long> productIds;

    private String address;

    private Integer expId;

    private String expNo;
}
