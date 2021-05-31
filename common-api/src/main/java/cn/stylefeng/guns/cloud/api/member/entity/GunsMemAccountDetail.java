package cn.stylefeng.guns.cloud.api.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("guns_mem_account_detail")
@Data
public class GunsMemAccountDetail {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;                    //余额明细id
    private Integer accountType;        //1账户金额 2冻结金额
    private  Long memberId;             //会员ID
    private Integer operationType;      //操作类型 1充值 2消费 3提现 4冻结
    private Integer changeType;         //余额变化类型 0余额增加 1余额减少
    private Double changeAmount;        //余额变化金额
    private Double currAmount;          //当前余额
    private String remark;              //备注
    private String operationUser;       //操作用户
    private Date createTime;            //操作时间

}
