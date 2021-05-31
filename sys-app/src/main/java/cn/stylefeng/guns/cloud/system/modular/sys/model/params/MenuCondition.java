package cn.stylefeng.guns.cloud.system.modular.sys.model.params;

import cn.stylefeng.guns.cloud.model.web.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询菜单列表的条件
 *
 * @author fengshuonan
 * @date 2018-02-27 15:24
 */
@Data
@ApiModel
public class MenuCondition extends BaseRequest {

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String menuName;

    /**
     * 父级菜单名称
     */
    @ApiModelProperty("父级菜单名称")
    private String pName;

    /**
     * 应用id
     */
    @ApiModelProperty("应用id")
    private String appId;

}
