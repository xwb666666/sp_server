package cn.stylefeng.guns.cloud.product.model.api.result;

import lombok.Data;

@Data
public class CollectResult {

    private Long id;      //收藏id

    private String picture;     //商品图片

    private String name;        //商品名称

    private String des;         //商品简要描述

    private Double originalPrice;   //原价

    private Double presentPrice;    //现价

}
