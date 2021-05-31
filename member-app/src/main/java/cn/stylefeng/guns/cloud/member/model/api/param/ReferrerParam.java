package cn.stylefeng.guns.cloud.member.model.api.param;

import lombok.Data;

@Data
public class ReferrerParam {
    private Long memberId;          //会员id

    private String referrer;        //上级推荐码
}
