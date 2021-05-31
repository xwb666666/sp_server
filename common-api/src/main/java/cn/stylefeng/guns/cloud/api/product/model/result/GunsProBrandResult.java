package cn.stylefeng.guns.cloud.api.product.model.result;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author stylefeng
 * @since 2021-03-23
 */
@Data
@ApiModel
public class GunsProBrandResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 品牌id
     */
    @ApiModelProperty("品牌id")
    private Long id;

    /**
     * 品牌名称
     */
    @ApiModelProperty("品牌名称")
    private String name;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    private String picture;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Long sort;

    /**
     * 状态 0删除 1正常 2下架
     */
    @ApiModelProperty("状态 0删除 1正常")
    private Integer status;
    private String statusDes="删除,正常";

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 是否是独有品牌  0否 1是
     */
    @ApiModelProperty("是否是独有品牌  0否 1是")
    private Integer isPrivate;
    private String isPrivateDes="否,是";

    /**
     * 添加时间
     */
    @ApiModelProperty("添加时间")
    private Date createDate;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateDate;

    /**
     * 首字母
     */
    @ApiModelProperty("首字母")
    private String firstChar;

}
