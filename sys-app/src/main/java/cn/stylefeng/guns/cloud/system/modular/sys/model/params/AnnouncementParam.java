package cn.stylefeng.guns.cloud.system.modular.sys.model.params;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class AnnouncementParam {

    @ApiModelProperty("id")
    private Long id;                //id

    @ApiModelProperty("标题")
    private String title;           //标题

    @ApiModelProperty("内容")
    private String content;         //内容

}
