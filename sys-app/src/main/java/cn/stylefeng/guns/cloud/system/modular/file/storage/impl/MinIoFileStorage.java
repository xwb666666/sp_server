package cn.stylefeng.guns.cloud.system.modular.file.storage.impl;

import cn.hutool.core.io.IoUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.system.config.properties.FileProperties;
import cn.stylefeng.guns.cloud.system.modular.file.enums.FileExceptionEnum;
import cn.stylefeng.guns.cloud.system.modular.file.storage.FileStorage;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * minio方式存储的
 *
 * @author fengshuonan
 * @Date 2019/11/26 15:33
 */
@Service
@Slf4j
public class MinIoFileStorage implements FileStorage {

    @Autowired
    private FileProperties fileProperties;

    @Override
    public void upload(String ObjectName, File object) {
        upload(fileProperties.getDefaultBucket(), ObjectName, object);
    }

    @Override
    public void upload(String bucktName, String objectName, File object) {
        try {
            MinioClient minioClient = createMinioClient(bucktName);

            //获取文件后缀
            String fileSuffix = objectName.substring(objectName.lastIndexOf("."));

            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(bucktName, objectName, new FileInputStream(object), getFileContentType(fileSuffix));
        } catch (Exception e) {
            log.error("上传文件出错！", e);
            throw new ServiceException(FileExceptionEnum.FILE_UPLOAD_ERROR);
        }
    }

    @Override
    public void upload(String objectName, byte[] objectBytes) {
        upload(fileProperties.getDefaultBucket(), objectName, objectBytes);
    }

    @Override
    public void upload(String bucktName, String objectName, byte[] objectBytes) {
        try {
            MinioClient minioClient = createMinioClient(bucktName);

            //获取文件后缀
            String fileSuffix = objectName.substring(objectName.lastIndexOf("."));

            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(bucktName, objectName, IoUtil.toStream(objectBytes), getFileContentType(fileSuffix));
        } catch (Exception e) {
            log.error("上传文件出错！", e);
            throw new ServiceException(FileExceptionEnum.FILE_UPLOAD_ERROR);
        }
    }

    @Override
    public byte[] getFileBytes(String objectName) {
        return getFileBytes(fileProperties.getDefaultBucket(), objectName);
    }

    @Override
    public byte[] getFileBytes(String bucktName, String objectName) {
        InputStream stream = null;
        try {
            MinioClient minioClient = createMinioClient(bucktName);
            stream = minioClient.getObject(bucktName, objectName);
            return IoUtil.readBytes(stream);
        } catch (Exception e) {
            log.error("获取文件流出错！", e);
            throw new ServiceException(FileExceptionEnum.FILE_IO_ERROR);
        } finally {
            IoUtil.close(stream);
        }
    }

    @Override
    public String getDownloadUrl(String objectName, String fileName) {
        return getDownloadUrl(fileProperties.getDefaultBucket(), objectName, fileName);
    }

    @Override
    public String getDownloadUrl(String bucktName, String objectName, String fileName) {
        return getDownloadUrl(fileProperties.getDefaultBucket(), objectName, fileName, fileProperties.getExpireSeconds());
    }

    @Override
    public String getDownloadUrl(String bucktName, String objectName, String fileName, Integer expireSeconds) {
        try {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            Map<String, String> headers = new HashMap<>();
            headers.put("response-content-type", "application/octet-stream");
            headers.put("response-content-disposition", "attachment; filename=" + fileName);

            MinioClient minioClient = createMinioClient(bucktName);
            String internalUrl = minioClient.presignedGetObject(bucktName, objectName, expireSeconds, headers);

            //转化外部地址
            return getExternalUrl(internalUrl);

        } catch (Exception e) {
            log.error("获取文件下载url出错！", e);
            throw new ServiceException(FileExceptionEnum.FILE_GET_URL_ERROR);
        }
    }

    @Override
    public String getPreviewUrl(String objectName) {
        return getPreviewUrl(fileProperties.getDefaultBucket(), objectName);
    }

    @Override
    public String getPreviewUrl(String bucktName, String objectName) {
        try {
            MinioClient minioClient = createMinioClient(bucktName);
            String internalUrl = minioClient.presignedGetObject(bucktName, objectName, fileProperties.getExpireSeconds());

            //转化外部地址
            return getExternalUrl(internalUrl);

        } catch (Exception e) {
            log.error("获取文件下载url出错！", e);
            throw new ServiceException(FileExceptionEnum.FILE_GET_URL_ERROR);
        }
    }

    /**
     * 创建minio客户端
     *
     * @author fengshuonan
     * @Date 2019/11/26 17:37
     */
    private MinioClient createMinioClient(String bucktName) {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient(fileProperties.getServerEndpoint(), fileProperties.getAccessKey(), fileProperties.getSecretKey());

            // 检查存储桶是否已经存在，不存在就创建一个令牌桶
            boolean isExist = minioClient.bucketExists(bucktName);
            if (!isExist) {
                minioClient.makeBucket(bucktName);
            }

            return minioClient;

        } catch (Exception e) {
            log.error("创建文件客户端错误！", e);
            throw new ServiceException(FileExceptionEnum.FILE_CLIENT_ERROR);
        }
    }

    /**
     * 获取外部地址
     *
     * @author fengshuonan
     * @Date 2019-11-28 10:50
     */
    private String getExternalUrl(String internal) {
        return internal.replaceAll(fileProperties.getServerEndpoint(), fileProperties.getExternalServerEndpoint());
    }

}
