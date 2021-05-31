package cn.stylefeng.guns.cloud.product.model.api.result;

import lombok.Data;

@Data
public class BrandResult {
    private Long id;            //品牌id
    private String name;        //品牌名称
    private String picture;     //品牌图片
    private String firstChar;   //品牌首字母

}
