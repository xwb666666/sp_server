package cn.stylefeng.guns.cloud.system.modular.sys.model.params;

import cn.stylefeng.guns.cloud.model.validator.BaseValidatingParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
@Data
public class ResourceQueryParam implements Serializable, BaseValidatingParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用id(非必填)")
    private Long appId;

    @ApiModelProperty(value = "应用名称(非必填)")
    private String resourceName;

    @ApiModelProperty(value = "是否是方法(Y:页面 N:api)(非必填)")
    private String menuFlag;

    @Override
    public String checkParam() {
        return null;
    }

}
