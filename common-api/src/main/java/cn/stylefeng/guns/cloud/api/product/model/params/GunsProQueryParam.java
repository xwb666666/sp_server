package cn.stylefeng.guns.cloud.api.product.model.params;

import lombok.Data;

/**
 * 查询商品列表请求参数
 */
@Data
public class GunsProQueryParam {
    private String name;
    private String code;
    private Integer status;
    private Long cateId;
    private Long groupId;

    private Long pageSize=20l;
    private Long page=1l;
}
