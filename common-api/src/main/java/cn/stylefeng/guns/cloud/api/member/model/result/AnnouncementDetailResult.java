package cn.stylefeng.guns.cloud.api.member.model.result;
import lombok.Data;

import java.util.Date;

@Data
public class AnnouncementDetailResult {

    private Long id;                //id

    private String title;           //标题

    private String content;         //内容

    private Date createTime;        //创建时间

}
