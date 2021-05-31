package cn.stylefeng.guns.cloud.api.product.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 分组表
 * </p>
 *
 * @author stylefeng
 * @since 2021-03-23
 */
@Data
@ApiModel
public class GunsProGroupResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 分组id
     */
    @ApiModelProperty("分组id")
    private Long id;

    /**
     * 分组名称
     */
    @ApiModelProperty("名称")
    private String name;



    /**
     * 状态 0启用   1禁用
     */
    @ApiModelProperty("状态 0启用 1禁用")
    private Integer status;
    private String statusDes="启用,禁用";


    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date creatDate;

}
