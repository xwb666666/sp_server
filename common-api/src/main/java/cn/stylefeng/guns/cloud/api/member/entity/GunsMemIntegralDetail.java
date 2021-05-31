package cn.stylefeng.guns.cloud.api.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

/**
 * 金豆
 * */
@TableName("guns_mem_integral_detail")
@Data
public class GunsMemIntegralDetail {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;                //金豆明细id
    private Long memberId;          //会员id
    private Double changeFer;       //金豆变化
    private Integer operationType;  //操作类型(枚举)
    private Double currFer;         //当前金豆
    private Integer ferChange;      //金豆变化类型 0 金豆增加 1金豆减少
    private String remark;          //备注
    private String ferUser;         //操作用户
    private Date createTime;        //操作时间
}
