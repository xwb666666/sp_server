package cn.stylefeng.guns.cloud.member.model.result;



import lombok.Data;

import java.io.Serializable;

@Data
public class GunsMemRanksResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Double startScore;

    private String userRankImg;

}
