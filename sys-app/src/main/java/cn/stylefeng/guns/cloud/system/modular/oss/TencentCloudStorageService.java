package cn.stylefeng.guns.cloud.system.modular.oss;

import cn.stylefeng.guns.cloud.system.modular.sys.entity.CloudStorage;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.QCloundConfig;
import com.alibaba.fastjson.JSON;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.ByteArrayInputStream;

public class TencentCloudStorageService extends AbsCloudStorageService {

    /**
     * SecretId: AKIDq48j35KCpLXVCatGL4VkiV1FWfY9ikfN
     * SecretKey: g2rPkFUfQ6hYAVaT5PXcbNFaMrKhgTrw
     */
    /**
     * 构造函数
     * @param config
     */
    public TencentCloudStorageService(CloudStorage config){
        this.config=config;
        init();
    }

    QCloundConfig qCloundConfig=new QCloundConfig();

    private COSClient client;

    private void init()
    {
        qCloundConfig= JSON.parseObject(config.getParamValue(),QCloundConfig.class); //转换为对象

        COSCredentials cred = new BasicCOSCredentials(qCloundConfig.getQcloudSecretId(), qCloundConfig.getQcloudSecretKey());
        ClientConfig clientConfig=new ClientConfig();
        clientConfig.setRegion(new Region(qCloundConfig.getQcloudRegion()));
        client=new COSClient(cred,clientConfig);
    }

    @Override
    public String upload(byte[] data, String path) {
        path=getPath(qCloundConfig.getQcloudPrefix(),path);
        if(!path.startsWith("/"))
            path="/"+path;
        ObjectMetadata mate=new ObjectMetadata();
        PutObjectRequest request=new PutObjectRequest(qCloundConfig.getQcloudBucketName(),path,new ByteArrayInputStream(data),mate);
        PutObjectResult resutl=client.putObject(request);
        return qCloundConfig.getQcloudDomain()+path;
    }
}
