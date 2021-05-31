package cn.stylefeng.guns.cloud.api.member.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
@TableName("guns_mem_member")
@Data
public class GunsMember implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;                       // 会员id
    private Integer rankId;                //等级id
    private String loginName;              //会员账号
    private String password;               //会员密码
    private String payPwd;                 //支付密码
    private Integer type;                   //默认是会员
    private Integer gender;                 //性别
    private String nickName;               //用户昵称
    private String photo;                  //会员头像
    private String mobile;                 //用户手机号
    private Date lastLoginTime;            //最后登录时间
    private Integer status;                //账号状态0:停用 1:启用',
    private Date createTime;               //创建时间
    private String referralCode;           //推荐码
    private String referrer;               //绑定推荐人

}

