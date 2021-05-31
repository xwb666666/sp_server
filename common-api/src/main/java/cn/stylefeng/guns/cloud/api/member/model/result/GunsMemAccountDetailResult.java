package cn.stylefeng.guns.cloud.api.member.model.result;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GunsMemAccountDetailResult implements Serializable {
    private static final long serialVersionUID = 1L;


    private String loginName;           //会员账号

    private Double changeAmount;        //余额变化

    private Double currAmount;         //当前余额

    private String remark;              //备注

    private String operationUser;       //操作用户

    private Date createTime;            //操作时间


}
