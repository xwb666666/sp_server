package cn.stylefeng.guns.cloud.system.modular.file.controller;

import cn.hutool.core.io.IoUtil;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.file.entity.Fileinfo;
import cn.stylefeng.guns.cloud.system.modular.file.enums.FileExceptionEnum;
import cn.stylefeng.guns.cloud.system.modular.file.service.FileInfoService;
import cn.stylefeng.guns.cloud.system.modular.file.storage.FileStorage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * 文件系统类
 *
 * @author fengshuonan
 * @Date 2019/10/1 7:27
 */
@RestController
@Api(tags = "文件系统")
@ApiResource(name = "文件系统")
public class FileController {

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private FileStorage fileStorage;

    /**
     * 文件上传
     *
     * @author fengshuonan
     * @Date 2019/11/27 17:47
     */
    @ApiOperation("文件上传")
    @PostResource(name = "文件上传", path = "/upload")
    public ResponseData upload(@RequestPart("file") MultipartFile file) {
        fileInfoService.upload(file);
        return ResponseData.success("上传成功");
    }

    /**
     * 预览文件
     * <p>
     * TODO 等前端改过来后requiredLogin改为true
     *
     * @author fengshuonan
     * @Date 2019/11/27 17:48
     */
    @ApiOperation("文件预览")
    @GetResource(name = "文件预览", path = "/preview", requiredPermission = false, requiredLogin = false)
    public void preview(@RequestParam Long fileId, HttpServletResponse response) {

        //获取文件信息
        Fileinfo fileinfo = fileInfoService.getFileinfo(fileId);

        byte[] fileBytes = fileStorage.getFileBytes(fileinfo.getFileFinalName());

        //设置头信息Content-Type
        String fileContentType = fileStorage.getFileContentType(fileinfo.getFileSuffix());
        response.setContentType(fileContentType);

        //返回文件流
        returnFileStream(response, fileBytes);
    }

    /**
     * 下载文件
     *
     * @author fengshuonan
     * @Date 2019/11/27 17:48
     */
    @ApiOperation("文件下载")
    @GetResource(name = "文件下载", path = "/download", requiredPermission = false)
    public void fileAccess(@RequestParam Long fileId, HttpServletResponse response) {

        //获取文件信息
        Fileinfo fileinfo = fileInfoService.getFileinfo(fileId);

        //获取文件内容
        byte[] fileBytes = fileStorage.getFileBytes(fileinfo.getFileFinalName());

        //设置头信息
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileinfo.getFileName());

        //返回文件流
        returnFileStream(response, fileBytes);

    }

    /**
     * 返回前端文件流
     *
     * @author fengshuonan
     * @Date 2019/11/27 18:10
     */
    private void returnFileStream(HttpServletResponse response, byte[] fileBytes) {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            stream.write(fileBytes);
            stream.flush();
        } catch (IOException e) {
            throw new ServiceException(FileExceptionEnum.FILE_IO_ERROR);
        } finally {
            IoUtil.close(stream);
        }
    }

}
