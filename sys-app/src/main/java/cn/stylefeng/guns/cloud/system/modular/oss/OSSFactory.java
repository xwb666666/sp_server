package cn.stylefeng.guns.cloud.system.modular.oss;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.stylefeng.guns.cloud.libs.context.SpringContext;
import cn.stylefeng.guns.cloud.system.core.constant.Constant;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.CloudStorage;
import cn.stylefeng.guns.cloud.system.modular.sys.service.CloudStorageService;

/**
 * oss 工厂 add by wzx
 */
public class OSSFactory {
    private static CloudStorageService cloudStorageService;
    static {
        OSSFactory.cloudStorageService= (CloudStorageService)SpringContext.getBean("CloudStorageService");
    }

    public static AbsCloudStorageService build(){

        CloudStorage storage = cloudStorageService.findStorage();
        String value=storage.getParamValue();
        JSONObject jsonObject = JSONUtil.parseObj(value);
        int type=jsonObject.get("type",Integer.class);
        if(type== Constant.CloudService.QCLOUD.getValue())
            return new TencentCloudStorageService(storage);
        return null;
    }
}
