package cn.stylefeng.guns.cloud.system.modular.sys.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("guns_announcement")
@Data
public class Announcement {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;                //id

    private String title;           //标题

    private String content;         //内容

    private Date createTime;        //创建时间
}
