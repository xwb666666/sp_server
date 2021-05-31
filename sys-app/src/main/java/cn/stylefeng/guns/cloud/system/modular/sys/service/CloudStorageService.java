package cn.stylefeng.guns.cloud.system.modular.sys.service;


import cn.stylefeng.guns.cloud.system.modular.sys.entity.CloudStorage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudStorageService extends IService<CloudStorage> {

    CloudStorage findStorage();

    String storeFile(MultipartFile file) throws IOException;

    <T> T getObject(String key,Class<T> cls);

    void addOrUpdate(String key,Object obj);


}
