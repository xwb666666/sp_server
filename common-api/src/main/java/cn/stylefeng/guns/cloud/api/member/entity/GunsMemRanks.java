package cn.stylefeng.guns.cloud.api.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 等级
 */
@TableName("guns_mem_ranks")
@Data
public class GunsMemRanks implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;                //等级id
    private String name;            //等级名称
    private Double startScore;     //初始金豆
    private String userRankImg;     //等级图标
    private String isDefault;       //是否为默认等级 0不是默认  1默认
    private Date createTime;        //创建时间
}
