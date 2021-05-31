package cn.stylefeng.guns.cloud.api.member.model.params;

import cn.stylefeng.guns.cloud.api.product.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.product.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Data
@ApiModel
public class GunsMemberQueryParam implements Serializable, BaseValidatingParam {
    private String nickName;
    private String LoginName;
    private String mobile;
    private Integer memberId;
    @Range(message = "性别只能输入0或1或2",min = 0,max = 2)
    private Integer gender;

    @Range(message = "状态只能输入0或1",min = 0,max = 1)
    private Integer status;


    private Long page=1L;
    private Long pageSize=20L;
}
