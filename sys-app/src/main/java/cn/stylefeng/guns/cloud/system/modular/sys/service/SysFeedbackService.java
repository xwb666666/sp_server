package cn.stylefeng.guns.cloud.system.modular.sys.service;

import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysFeedback;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysFeedbackResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysFeedbackService extends IService<SysFeedback> {


    Page<SysFeedbackResult> selectFeedback();

    Boolean updateDisposeStatus(Integer id);
}
