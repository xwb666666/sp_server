package cn.stylefeng.guns.cloud.api.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("guns_order_log")
public class GunsOrderLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String orderNo;

    private Integer userType;

    private Long userId;

    private String context;

    private Date createTime;
}
