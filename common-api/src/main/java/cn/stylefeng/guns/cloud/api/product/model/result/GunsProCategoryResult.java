package cn.stylefeng.guns.cloud.api.product.model.result;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class GunsProCategoryResult implements Serializable {
    private Long id;
    private String name;
    private String shortName;
    private String icon;
    private Integer sort;
    private Long parentId;
    /**
     * 子分类
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<GunsProCategoryResult> children;

}
