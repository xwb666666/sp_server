package cn.stylefeng.guns.cloud.system.modular.oss;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.CloudStorage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 云存储服务，支持七牛，腾讯，本地
 */
public abstract class AbsCloudStorageService {

    CloudStorage config;

    /**
     * 文件路径
     * @param prefix 前缀
     * @param suffix 后缀
     * @return
     */
    public String getPath(String prefix,String suffix){
        String uuid= UUID.randomUUID().toString().replace("-","");
        String path= DateUtil.format(new Date(),"yyyyMMdd")+"/"+uuid;
        if(StringUtils.isNotBlank(prefix))
            path=prefix+"/"+path;
        return path+suffix;
    }

    /**
     * 文件上传
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return     返回http地址
     */
    public abstract String upload(byte[] data,String path);
}
