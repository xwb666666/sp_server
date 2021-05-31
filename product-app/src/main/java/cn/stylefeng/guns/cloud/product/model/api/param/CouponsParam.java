package cn.stylefeng.guns.cloud.product.model.api.param;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class CouponsParam {

    private String name;

    //过滤字段
    private Integer type; //类型 1:满减 2折扣

    private Integer expireType=1;  //过期类型 1:固定日期 2领取后有效天数

    private Data startDate; //固定日期，互动结束时间
    private Data endDate; //固定日期，互动结束时间

    private Integer startDays; //过期天数，领取后几天有效
    private Integer endDays; //过期天数，领取后几天有效

    private Integer status; //状态（ 1:正常 2:关闭）

    private List<Long> productCoupons;  //优惠券关联商品


    private Integer page=1;
    private Integer pageSize=20;
}
