package cn.stylefeng.guns.cloud.system.modular.ent.model.params;

import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
@Data
@ApiModel
public class EntUserQueryParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 部门id
     */
    @ApiModelProperty("部门id")
    private Long deptId;

    /**
     * 人员姓名
     */
    @ApiModelProperty("人员姓名")
    private String name;

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String account;


}
