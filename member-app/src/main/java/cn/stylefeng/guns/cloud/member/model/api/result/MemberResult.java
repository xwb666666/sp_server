package cn.stylefeng.guns.cloud.member.model.api.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String photo;           //用户头像

    private String nickName;        //用户昵称

    private Long type;              //用户类型  //用户类型描述 0会员 1普通用户

    private String typeDes = "会员,普通用户";

    private Long rankId;            //用户等级

    private String rankName;        //用户等级名称

    private Integer coupons=0;        //优惠券

    private Integer attention=10;    //我的关注

    private Integer collect;        //我的收藏

    private  Integer browse=30;            //浏览记录

    private Double memberMoney;         //账户余额

    private Double integral;            //金豆

    private String mobile;              //用户手机号
}
