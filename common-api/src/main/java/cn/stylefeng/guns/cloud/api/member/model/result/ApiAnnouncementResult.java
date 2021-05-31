package cn.stylefeng.guns.cloud.api.member.model.result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ApiAnnouncementResult {

    private Long id;                //id

    private String title;           //标题

    private Date createTime;        //创建时间



}
