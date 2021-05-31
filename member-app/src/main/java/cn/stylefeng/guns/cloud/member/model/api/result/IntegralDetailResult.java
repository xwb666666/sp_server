package cn.stylefeng.guns.cloud.member.model.api.result;
import lombok.Data;

import java.util.Date;
@Data
public class IntegralDetailResult {
    private Double changeFer;       //金豆变化
    private Integer operationType;  //操作类型(枚举)
    private String operationTypeDes="签到,邀请好友,看视频,绑定推荐人"; //0签到 1邀请好友 2看视频 3绑定推荐人
    private Integer ferChange;      //金豆变化类型 0 金豆增加 1金豆减少
    private Date createTime;        //操作时间
}
