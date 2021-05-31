package cn.stylefeng.guns.cloud.api.member.model.result;


import lombok.Data;
import java.io.Serializable;


@Data
public class GunsMemberResult implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer rankId;

    private String loginName;

    private String type;

    private String gender;

    private String nickName;

    private String photo;

    private String mobile;

    private Integer status;

    private Long memberId;




}
