package cn.stylefeng.guns.cloud.product.model.api.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SearchParam {

    private String keywords; //关键字(搜索字段)

    //过滤字段
    private Long cateId; //分类id
    private Long brandId; //品牌id

    private Integer sort=0; //排序字段 0默认 1:新品
    private Integer sortType=1;  //0降序 1升序
    private Integer saleSortType=0; //0降序 1升序


    private Integer page=1;
    private Integer pageSize=20;
}
