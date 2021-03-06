package cn.stylefeng.guns.cloud.system.modular.sys.model.result;

import cn.stylefeng.guns.cloud.system.modular.sys.validated.AliyunGroup;
import cn.stylefeng.guns.cloud.system.modular.sys.validated.LocalGroup;
import cn.stylefeng.guns.cloud.system.modular.sys.validated.QiniuGroup;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
public class CloudStorageResult implements Serializable {

    private static final long serialVersionUID = 1L;


    //类型 1：七牛  2：阿里云  3：本地上传
    @Range(min=1, max=3, message = "类型错误")
    private Integer type;

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

    //本地上传绑定的域名
    @NotBlank(message="本地上传绑定的域名不能为空",groups = LocalGroup.class)
    @URL(message = "本地上传绑定的域名格式不正确",groups = LocalGroup.class)
    private String bendiDomain;
    //阿里云路径前缀
    private String bendiPrefix;
    //阿里云EndPoint
    @NotBlank(message="本地上传EndPoint不能为空",groups = LocalGroup.class)
    private String bendiEndPoint;
    //阿里云AccessKeyId
    @NotBlank(message="本地上传AccessKeyId不能为空",groups = LocalGroup.class)
    private String bendiAccessKeyId;
    //阿里云AccessKeySecret
    @NotBlank(message="本地上传AccessKeySecret不能为空",groups = LocalGroup.class)
    private String bendiAccessKeySecret;
    //阿里云BucketName
    @NotBlank(message="本地上传BucketName不能为空",groups = LocalGroup.class)
    private String bendiBucketName;

}
