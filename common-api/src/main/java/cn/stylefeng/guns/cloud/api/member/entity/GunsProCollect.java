package cn.stylefeng.guns.cloud.api.member.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("guns_pro_collect")
@Data
public class GunsProCollect implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long memberId;
    private Long productId;
    private Date createTime;
}
