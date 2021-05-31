package cn.stylefeng.guns.cloud.system.modular.sys.model.params;


import cn.stylefeng.guns.cloud.system.core.constant.Constant;
import cn.stylefeng.guns.cloud.system.modular.sys.validated.LocalGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LocalConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    //类型 0：七牛  1：阿里云  2：本地上传
    @Range(min=0, max=2, message = "类型错误")
    private  Integer type= Constant.CloudService.LOCAL.getValue();

    //本地上传绑定的域名
    @NotBlank(message="本地上传绑定的域名不能为空",groups = LocalGroup.class)
    @URL(message = "本地上传绑定的域名格式不正确",groups = LocalGroup.class)
    private String localDomain;
    //本地路径前缀
    private String localPrefix;
    //本地上传目录
    @NotBlank(message="本地上传不能为空",groups = LocalGroup.class)
    private String localDirectory;

}
