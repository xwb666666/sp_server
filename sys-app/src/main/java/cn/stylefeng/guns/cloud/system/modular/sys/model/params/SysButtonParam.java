package cn.stylefeng.guns.cloud.system.modular.sys.model.params;

import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 按钮表
 * </p>
 *
 * @author stylefeng
 * @since 2019-11-02
 */
@Data
@ApiModel
public class SysButtonParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 按钮id
     */
    @ApiModelProperty("按钮id")
    private Long buttonId;

    /**
     * 菜单id
     */
    @ApiModelProperty("菜单id")
    private Long menuId;

    /**
     * 资源id
     */
    @ApiModelProperty("资源编码")
    private String resCode;

    /**
     * 按钮名称
     */
    @ApiModelProperty("按钮名称")
    private String buttonName;

    /**
     * 按钮编码
     */
    @ApiModelProperty("按钮编码")
    private String buttonCode;

    /**
     * 按钮编码
     */
    @ApiModelProperty("保存资源编码的字符串")
    private String resCodeStr;

    /**
     * 按钮描述
     */
    @ApiModelProperty("按钮描述")
    private String description;

    /**
     * 按钮状态(1:启用 2:禁用)
     */
    @ApiModelProperty("按钮状态(1:启用 2:禁用)")
    private Integer status;

    /**
     * 按钮排序
     */
    @ApiModelProperty("按钮排序")
    private BigDecimal orderNo;

    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    private Long createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private Date createTime;

    /**
     * 修改人
     */
    @ApiModelProperty(hidden = true)
    private Long updateUser;

    /**
     * 修改时间
     */
    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @Override
    public String checkParam() {
        if (ObjectUtil.isEmpty(buttonName)) {
            return "按钮名称为空";
        }
        if (ObjectUtil.isEmpty(buttonCode)) {
            return "按钮编码为空";
        }
        if (ObjectUtil.isEmpty(resCode)) {
            return "关联的资源为空";
        }
        if (ObjectUtil.isEmpty(menuId)) {
            return "菜单id为空";
        }

        return null;
    }

}
