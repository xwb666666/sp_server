package cn.stylefeng.guns.cloud.system.modular.sys.model.params;


import cn.stylefeng.guns.cloud.system.core.constant.Constant;
import cn.stylefeng.guns.cloud.system.modular.sys.validated.AliyunGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

import java.io.Serializable;

@Data
public class AliYunConfig implements Serializable {



    private static final long serialVersionUID = 1L;

    //类型 0：七牛  1：阿里云  2：本地上传 3:腾讯云
    @Range(min=0, max=3, message = "类型错误")
    private  Integer type= Constant.CloudService.ALIYUN.getValue();

    //阿里云绑定的域名
    @NotBlank(message="阿里云绑定的域名不能为空",groups = AliyunGroup.class)
    @URL(message = "阿里云绑定的域名格式不正确",groups = AliyunGroup.class)
    private String aliyunDomain;
    //阿里云路径前缀
    private String aliyunPrefix;
    //阿里云EndPoint
    @NotBlank(message="阿里云EndPoint不能为空",groups = AliyunGroup.class)
    private String aliyunEndPoint;
    //阿里云AccessKeyId
    @NotBlank(message="阿里云AccessKeyId不能为空",groups = AliyunGroup.class)
    private String aliyunAccessKeyId;
    //阿里云AccessKeySecret
    @NotBlank(message="阿里云AccessKeySecret不能为空",groups = AliyunGroup.class)
    private String aliyunAccessKeySecret;
    //阿里云BucketName
    @NotBlank(message="阿里云BucketName不能为空",groups = AliyunGroup.class)
    private String aliyunBucketName;

}
