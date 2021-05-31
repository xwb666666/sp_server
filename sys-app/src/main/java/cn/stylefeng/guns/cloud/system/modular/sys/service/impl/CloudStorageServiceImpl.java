package cn.stylefeng.guns.cloud.system.modular.sys.service.impl;

import cn.stylefeng.guns.cloud.system.modular.oss.OSSFactory;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.CloudStorage;
import cn.stylefeng.guns.cloud.system.modular.sys.mapper.CloudStorageMapper;
import cn.stylefeng.guns.cloud.system.modular.sys.service.CloudStorageService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("CloudStorageService")
public class CloudStorageServiceImpl extends ServiceImpl<CloudStorageMapper, CloudStorage> implements CloudStorageService {
    @Override
    public CloudStorage findStorage() {
        QueryWrapper<CloudStorage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("param_key", "CLOUD_STORAGE_CONFIG_KEY");
        CloudStorage cloudStorage = baseMapper.selectOne(queryWrapper);
        return cloudStorage;
    }

    @Override
    public String storeFile(MultipartFile file) throws IOException {
        //存储文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = OSSFactory.build().upload(file.getBytes(), suffix);
        return url;
    }

    @Override
    public <T> T getObject(String key,Class<T> cls) {
        QueryWrapper<CloudStorage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("param_key", key);
        CloudStorage cloudStorage = baseMapper.selectOne(queryWrapper);
        T t= JSON.parseObject(cloudStorage.getParamValue(),cls);
        return t;
    }

    @Override
    public void addOrUpdate(String key, Object obj) {
        QueryWrapper<CloudStorage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("param_key", key);
        CloudStorage cloudStorage = baseMapper.selectOne(queryWrapper);
        if(cloudStorage!=null)
        {
            cloudStorage.setParamValue(JSON.toJSONString(obj));
            UpdateWrapper<CloudStorage> updateWrapper=new UpdateWrapper<>();
            updateWrapper.eq("param_key",key);
            baseMapper.update(cloudStorage,updateWrapper);
        }
        else
        {
            //添加
            CloudStorage storage=new CloudStorage();
            storage.setParamValue(JSON.toJSONString(obj));
            storage.setParamKey(key);
            baseMapper.insert(storage);
        }
    }


}
