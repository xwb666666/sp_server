package cn.stylefeng.guns.cloud.system.modular.file.service;

import cn.stylefeng.guns.cloud.system.modular.file.entity.Fileinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 应用管理 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
public interface FileInfoService extends IService<Fileinfo> {

    /**
     * 上传文件
     *
     * @author fengshuonan
     * @since 2019-09-10
     */
    void upload(MultipartFile file);

    /**
     * 获取文件信息
     *
     * @author fengshuonan
     * @since 2019-09-10
     */
    Fileinfo getFileinfo(Long fileId);

}
