package cn.stylefeng.guns.cloud.api.member.model.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class GunsMemParamResult implements Serializable {

    private Long id;

    private Integer rankId;

    private Integer type;

    private Integer gender;

    private String nickName;

    private String photo;

    private String mobile;

    private Integer status;

    private Double integral;

    private Double lockMoney;

    private Double memberMoney;


}
