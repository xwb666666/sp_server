package cn.stylefeng.guns.cloud.api.member.model.result;


import lombok.Data;

import java.io.Serializable;

@Data
public class GunsMemAccountResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer memberId;

    private Double integral;

    private Double lockMoney;

    private Double memberMoney;
}
