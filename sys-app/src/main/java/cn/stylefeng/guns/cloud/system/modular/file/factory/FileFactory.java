package cn.stylefeng.guns.cloud.system.modular.file.factory;

import cn.stylefeng.guns.cloud.libs.context.auth.LoginContext;
import cn.stylefeng.guns.cloud.system.modular.file.entity.Fileinfo;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.util.Date;

/**
 * 文件信息创建工厂
 *
 * @author fengshuonan
 * @Date 2019/11/27 18:11
 */
public class FileFactory {

    public static Fileinfo createFileInfo(String fileName, long size) {

        //文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));

        //文件唯一id
        long fileId = IdWorker.getId();

        //当前登录人
        Long accountId = null;
        try {
            accountId = LoginContext.me().getAccountId();
        } catch (Exception e) {
            accountId = -1L;
        }

        //创建文件信息
        Fileinfo fileinfo = new Fileinfo();
        fileinfo.setFileId(IdWorker.getId());
        fileinfo.setFileName(fileName);
        fileinfo.setFileSize(size);
        fileinfo.setFileFinalName(fileId + fileSuffix);
        fileinfo.setFileSuffix(fileSuffix);
        fileinfo.setAppCode("none");
        fileinfo.setCreateUser(accountId);
        fileinfo.setUpdateUser(accountId);
        fileinfo.setCreateTime(new Date());
        fileinfo.setUpdateTime(new Date());
        fileinfo.setDelFlag("N");

        return fileinfo;
    }

}
