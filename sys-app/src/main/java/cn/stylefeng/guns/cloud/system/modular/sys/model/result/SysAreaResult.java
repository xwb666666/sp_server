package cn.stylefeng.guns.cloud.system.modular.sys.model.result;

import cn.stylefeng.guns.cloud.api.product.model.result.GunsProCategoryResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 区域
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-15
 */
@Data
@ApiModel
public class SysAreaResult implements Serializable {

    private static final long serialVersionUID = 1L;



    @ApiModelProperty("区域id")
    private Long id;

    @ApiModelProperty("区域名称")
    private String name;

    @ApiModelProperty("上级区域id")
    private Long parentId;

    /**
     * 子分类
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SysAreaResult> children;

}
