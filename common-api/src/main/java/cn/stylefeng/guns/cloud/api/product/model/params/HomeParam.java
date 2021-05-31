package cn.stylefeng.guns.cloud.api.product.model.params;

import lombok.Data;

@Data
public class HomeParam {
    private Long memberId;          //会员id

    private Long categoryId=1l;     //分类id

    private Long groupId=1l;        //分组id

    private Long annId;             //公告id
}
