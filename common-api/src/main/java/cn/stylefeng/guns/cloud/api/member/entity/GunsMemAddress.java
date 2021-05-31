package cn.stylefeng.guns.cloud.api.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("guns_mem_address")
@Data
public class GunsMemAddress implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;                    //id
    private Long memberId;              //会员id
    private String name;                //名字
    private String phone;               //手机号码
    private Long areaId;                //区域id
    private String area;                //区域名称
    private String address;              //详细地址
    private Integer isDefault;     //是否设为默认地址  0是  1否
    private Integer sort;               //排序
    private Date createTime;            //创建时间
    private Date updateTime;            //修改时间
}
