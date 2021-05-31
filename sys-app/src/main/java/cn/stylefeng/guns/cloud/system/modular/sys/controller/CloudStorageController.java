package cn.stylefeng.guns.cloud.system.modular.sys.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.system.core.constant.Constant;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.LocalConfig;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.QCloundConfig;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.QiNiuConfig;
import cn.stylefeng.guns.cloud.system.modular.sys.validated.AliyunGroup;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;

import cn.stylefeng.guns.cloud.system.modular.sys.validated.LocalGroup;
import cn.stylefeng.guns.cloud.system.modular.sys.validated.QiniuGroup;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.CloudStorage;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AliYunConfig;
import cn.stylefeng.guns.cloud.system.modular.sys.service.CloudStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ApiResource(name = "存储配置接口", path = "/config/storage")
@Api(tags = "存储配置接口")
public class CloudStorageController {

    @Autowired
    private CloudStorageService cloudStorageService;
    private String key="CLOUD_STORAGE_CONFIG_KEY";

    @ApiOperation("获取存储配置")
    @GetResource(name = "获取存储配置", path = "/selectsso")
    public ResponseData storage() {
        return ResponseData.success(cloudStorageService.findStorage());
    }


    @PostResource(name = "阿里存储配置", path = "/alisso")
    @ApiOperation("阿里存储配置")
    public ResponseData saveAliSso(@Validated({AliyunGroup.class}) @RequestBody AliYunConfig aliYunConfig) {

        CloudStorage cloudStorage = cloudStorageService.findStorage();
        aliYunConfig.setType(Constant.CloudService.ALIYUN.getValue());
        cloudStorageService.addOrUpdate(key, aliYunConfig);
        return ResponseData.success();

    }

    @PostResource(name = "腾讯云存储配置", path = "/qcloudsso")
    @ApiOperation("腾讯云存储配置")
    public ResponseData saveQcloudSso(@RequestBody QCloundConfig qcloudConfig) {

        CloudStorage cloudStorage = cloudStorageService.findStorage();
        qcloudConfig.setType(Constant.CloudService.QCLOUD.getValue());
        cloudStorageService.addOrUpdate(key, qcloudConfig);
        return ResponseData.success();

    }

    @PostResource(name = "本地存储配置", path = "/localsso")
    @ApiOperation("本地存储配置")
    public ResponseData SaveLocalSso(@Validated({LocalGroup.class}) @RequestBody LocalConfig localConfig) {
        localConfig.setType(Constant.CloudService.LOCAL.getValue());
        cloudStorageService.addOrUpdate(key, localConfig);
        return ResponseData.success();
    }


    @PostResource(name = "七牛存储配置", path = "/qnsso")
    @ApiOperation("七牛存储配置")
    public ResponseData SaveQnSso(@Validated({QiniuGroup.class}) @RequestBody QiNiuConfig qiNiuConfig) {
        qiNiuConfig.setType(Constant.CloudService.QINIU.getValue());
        cloudStorageService.addOrUpdate(key, qiNiuConfig);
        return ResponseData.success();
    }

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public ResponseData upLoad(MultipartFile file) throws IOException {
        String url = cloudStorageService.storeFile(file);
        Map<String, String> map = new HashMap<>();
        map.put("url", url);
        return ResponseData.success(map);
    }


    @PostMapping("/uploadImgs")
    @ApiOperation("上传文件")
    public ResponseData uploadImgs(MultipartFile[] files) throws IOException {
        List<String> imgList = new ArrayList<>();
        for(MultipartFile file : files){
            String url = cloudStorageService.storeFile(file);
            imgList.add(url);
        }
        return ResponseData.success(imgList);

    }
}

