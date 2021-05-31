package cn.stylefeng.guns.cloud.member.model.api.result;
import lombok.Data;
import java.util.Date;

@Data
public class AccountDetailResult {

    private Integer operationType;

    private Integer changeType;

    private Double currAmount;

    private Date createTime;

}
