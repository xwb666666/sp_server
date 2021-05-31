package cn.stylefeng.guns.cloud.system.modular.ent.model.params;

import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 职务管理
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@Data
@ApiModel
public class EntDutyParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    /**
     * 职务管理ID
     */
    @ApiModelProperty("职务管理ID")
    private Long dutyId;

    /**
     * 职务唯一业务编码
     */
    @ApiModelProperty("职务唯一业务编码")
    private String dutyCode;

    /**
     * 职务名称
     */
    @ApiModelProperty("职务名称")
    private String dutyName;

    /**
     * 排序码
     */
    @ApiModelProperty("排序码")
    private BigDecimal orderNo;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 状态(1:启用,2:禁用)
     */
    @ApiModelProperty("状态(1:启用,2:禁用)")
    private Integer status;

    @Override
    public String checkParam() {
        return null;
    }

}
