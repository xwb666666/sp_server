package cn.stylefeng.guns.cloud.product.model.api.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApiGunsProCategoryResult implements Serializable {
    private Long id;
    private String name;

}
