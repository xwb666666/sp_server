package cn.stylefeng.guns.cloud.api.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 账户
 */
@TableName("guns_mem_account")
@Data
public class GunsMemAccount implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;                //id
    private Long memberId;          //用户id
    private Double integral;        //金豆
    private Double lockMoney;      //冻结金额
    private Double memberMoney;     //账户余额

}
