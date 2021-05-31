package cn.stylefeng.guns.cloud.product.model.api.result;

import lombok.Data;
import java.io.Serializable;


@Data
public class CategoryResult implements Serializable {
    private Long id;        //分类id
    private String name;    //分类名称
    private String icon;    //分类图标
    private Integer sort;   //排序
    private Long parentId;  //父级id
}
