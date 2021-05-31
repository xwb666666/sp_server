package cn.stylefeng.guns.cloud.system.modular.sys.model.params;

import cn.stylefeng.guns.cloud.system.core.constant.Constant;
import cn.stylefeng.guns.cloud.system.modular.sys.validated.QiniuGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class QiNiuConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    //类型 0：七牛  1：阿里云  2：本地上传
    @Range(min=0, max=2, message = "类型错误")
    private  Integer type= Constant.CloudService.QINIU.getValue();

    //七牛绑定的域名
    @NotBlank(message="七牛绑定的域名不能为空",groups = QiniuGroup.class)
    @URL(message = "七牛绑定的域名格式不正确",groups = QiniuGroup.class)
    private String qiniuDomain;
    //七牛路径前缀
    private String qiniuPrefix;
    //七牛ACCESS_KEY
    @NotBlank(message="七牛AccessKey不能为空",groups = QiniuGroup.class)
    private String qiniuAccessKey;
    //七牛SECRET_KEY
    @NotBlank(message="七牛SecretKey不能为空",groups = QiniuGroup.class)
    private String qiniuSecretKey;
    //七牛存储空间名
    @NotBlank(message="七牛空间名不能为空",groups = QiniuGroup.class)
    private String qiniuBucketName;

}
