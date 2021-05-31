package cn.stylefeng.guns.cloud.member.model.result;

import lombok.Data;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-04-14 18:39
 **/
@Data
public class GunsMemSelectResult {
    private Long id;
    private Integer rankId;
    private String rankName;
    private String loginName;
    private String type;
    private Integer gender;
    private String genderDes = "保密,男,女";
    private String nickName;
    private String photo;
    private String mobile;
    private Integer status;
    private String statusDes = "正常,禁用";
    private Double integral;
    private Double lockMoney;
    private Double memberMoney;
}
