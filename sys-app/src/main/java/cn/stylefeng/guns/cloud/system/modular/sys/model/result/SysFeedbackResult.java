package cn.stylefeng.guns.cloud.system.modular.sys.model.result;
import lombok.Data;
import java.util.Date;


@Data
public class SysFeedbackResult {

    private Long memberId;                   //会员id

    private  String questionsComments;      //问题与意见

    private String picture;                 //图片

    private Integer disposeStatus;         //是否已处理 0是  1否
    private String disposeStatusDes="是,否";

    private String relationWay;             //联系方式

    private Date createTime;                //创建时间
}
