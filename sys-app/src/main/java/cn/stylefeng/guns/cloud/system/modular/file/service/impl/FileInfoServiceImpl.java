package cn.stylefeng.guns.cloud.system.modular.file.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.system.modular.file.entity.Fileinfo;
import cn.stylefeng.guns.cloud.system.modular.file.enums.FileExceptionEnum;
import cn.stylefeng.guns.cloud.system.modular.file.factory.FileFactory;
import cn.stylefeng.guns.cloud.system.modular.file.mapper.FileinfoMapper;
import cn.stylefeng.guns.cloud.system.modular.file.service.FileInfoService;
import cn.stylefeng.guns.cloud.system.modular.file.storage.FileStorage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 文件系统 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
@Slf4j
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileinfoMapper, Fileinfo> implements FileInfoService {

    @Autowired
    private FileStorage fileStorage;

    @Override
    public Fileinfo getFileinfo(Long fileId) {

        if (ObjectUtil.isEmpty(fileId)) {
            throw new ServiceException(400, "fileId不能为空");
        }

        Fileinfo fileinfo = this.getById(fileId);

        if (ObjectUtil.isEmpty(fileinfo)) {
            throw new ServiceException(400, "文件不存在");
        }

        return fileinfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upload(MultipartFile file) {

        //获取文件名称
        String fileName = file.getOriginalFilename();

        if (ObjectUtil.isEmpty(fileName)) {
            fileName = "emptyFileName";
        }

        //文件大小
        long size = file.getSize();

        //保存文件
        Fileinfo fileInfo = FileFactory.createFileInfo(fileName, size);
        this.save(fileInfo);

        //上传文件
        try {
            fileStorage.upload(fileInfo.getFileFinalName(), file.getBytes());
        } catch (IOException e) {
            log.error("上传那文件错误！", e);
            throw new ServiceException(FileExceptionEnum.FILE_UPLOAD_ERROR);
        }
    }

}
