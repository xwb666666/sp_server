package cn.stylefeng.guns.cloud.api.member.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GunsMemIntegralDetailResult implements Serializable {

    private String loginName;          //会员账号

    private Double changeFer;       //金豆变化

    private Double currFer;         //当前金豆

    private String remark;          //备注

    private String ferUser;         //操作用户

    private Date createTime;        //操作时间
}
