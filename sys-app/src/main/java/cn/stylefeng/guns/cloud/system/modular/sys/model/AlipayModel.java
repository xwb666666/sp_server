package cn.stylefeng.guns.cloud.system.modular.sys.model;


import lombok.Data;

@Data
public class AlipayModel {

    private String alipayName; //支付宝支付名称

    private Integer payType;    //支付宝支付

    private String AppID;

    private Integer signType;   //验签方式 0RSA  1RSA2
    private String singTypeDes="RSA,RSA2";

    private String privateKey;  //应用密钥

    private String appCertPublicKey;//商户证书

    private String alipayCertPublicKey; //支付宝公钥证书

    private String alipayRootCert;  //支付宝根证书
}
