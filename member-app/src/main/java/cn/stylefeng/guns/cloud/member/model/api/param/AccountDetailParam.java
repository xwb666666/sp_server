package cn.stylefeng.guns.cloud.member.model.api.param;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel
public class AccountDetailParam implements Serializable, BaseValidatingParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("会员id")
    @NotNull(message = "会员id不能为空")
    private Long memberId;//会员id

    @Range(message = "非法值" ,min = 0,max = 2)
    @ApiModelProperty("操作类型 1充值 2提现 3消费 4冻结 ")
    private Integer operationType;

    @ApiModelProperty("当前页")
    private Long page=1L;
    @ApiModelProperty("当前条数")
    private Long pageSize=20L;
}
