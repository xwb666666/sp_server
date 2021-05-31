package cn.stylefeng.guns.cloud.system.modular.ent.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 企业应用配置
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@Data
@ApiModel
public class EntCompAppResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 应用ID
     */
    @ApiModelProperty("应用ID")
    private Long appId;

}
