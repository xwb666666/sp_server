package cn.stylefeng.guns.cloud.product.model.api.result;

import lombok.Data;


/**
 *
 * @author
 * @since 2021-03-22
 */
@Data
public class ProductResult  {

    private Long id;            //商品id
    private String picture;     //商品图片

    private String name;        //商品名称

    private Double originalPrice;   //商品原价

    private Double presentPrice;    //销售价格

    private Double salesVolume;     //销量

    private String evaluation;      //评价（null数据，数据库不存在该字段）




}
