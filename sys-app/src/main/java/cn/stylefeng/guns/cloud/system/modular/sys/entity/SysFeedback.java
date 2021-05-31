package cn.stylefeng.guns.cloud.system.modular.sys.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("guns_feedback")
public class SysFeedback {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;                        //id

    private Long memberId;                   //会员id

    private  String questionsComments;      //问题与意见

    private Integer disposeStatus;         //是否已处理 0是  1否

    private String picture;                 //图片

    private String relationWay;             //联系方式

    private Date createTime;                //创建时间
}
