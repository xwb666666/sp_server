package cn.stylefeng.guns.cloud.system.modular.sys.model.params;
import lombok.Data;

@Data
public class WXPayConfig {

    private String WXPayName; //微信支付名称

    private Integer payType;    //微信支付类型  0微信支付  1微信支付子商户
    private String payTypeDes="微信支付,微信支付子商户";

    private String merchantName;    //商户名称

    private String AppID;

    private String MchId;   //支付商户号

    private String apiKey;  //支付密钥

    private String CERTCertificate; //CERT文件证书

    private String secretKey;   //KEY密钥文件

    private String root;        //ROOT文件


}
