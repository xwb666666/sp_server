package cn.stylefeng.guns.cloud.system.modular.sys.model.params;

import cn.stylefeng.guns.cloud.system.core.constant.Constant;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 腾讯云配置
 */
@Data
public class QCloundConfig {

    //类型 0：七牛  1：阿里云  2：本地上传 3:腾讯云
    private  Integer type= Constant.CloudService.QCLOUD.getValue();
    //腾讯云绑定的域名
    @NotBlank(message="腾讯云绑定的域名不能为空")
    @URL(message = "腾讯云绑定的域名格式不正确")
    private String qcloudDomain;
    //腾讯云路径前缀
    private String qcloudPrefix;
    //腾讯云AppId
    @NotNull(message="腾讯云AppId不能为空")
    private Integer qcloudAppId;
    //腾讯云SecretId
    @NotBlank(message="腾讯云SecretId不能为空")
    private String qcloudSecretId;
    //腾讯云SecretKey
    @NotBlank(message="腾讯云SecretKey不能为空")
    private String qcloudSecretKey;
    //腾讯云BucketName
    @NotBlank(message="腾讯云BucketName不能为空")
    private String qcloudBucketName;
    //腾讯云COS所属地区
    @NotBlank(message="所属地区不能为空")
    private String qcloudRegion;

}
