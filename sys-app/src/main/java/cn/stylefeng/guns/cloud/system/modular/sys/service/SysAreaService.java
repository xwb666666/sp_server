package cn.stylefeng.guns.cloud.system.modular.sys.service;

import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysArea;

import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysAreaResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 * 基础字典 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
public interface SysAreaService extends IService<SysArea> {


    List<SysAreaResult> getCateWithTree();
    /*List<SysAreaResult> getCateWithTree();*/
}
