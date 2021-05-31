package cn.stylefeng.guns.cloud.system.modular.sys.service.impl;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.api.order.GunsOrderLog;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysFeedback;
import cn.stylefeng.guns.cloud.system.modular.sys.mapper.SysFeedbackMapper;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysFeedbackResult;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysFeedbackService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysFeedbackServiceImpl extends ServiceImpl<SysFeedbackMapper, SysFeedback> implements SysFeedbackService {

    @Override
    public Page<SysFeedbackResult> selectFeedback() {

        Page<SysFeedback> page = new Page<>();
        page.setSize(20);
        page.setCurrent(1);

        //获取所有数据
        page=baseMapper.selectPage(page,null);
        List<SysFeedbackResult> list = new ArrayList<>();

        for (SysFeedback sysFeedback : page.getRecords()) {
            SysFeedbackResult sysFeedbackResult = new SysFeedbackResult();
            ToolUtil.copyProperties(sysFeedback, sysFeedbackResult);
            list.add(sysFeedbackResult);
        }

        Page<SysFeedbackResult> pageResult = new Page<>();
        pageResult.setSize(page.getSize());
        pageResult.setCurrent(page.getCurrent());
        pageResult.setPages(page.getPages());
        pageResult.setRecords(list);
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

    @Override
    public Boolean updateDisposeStatus(Integer id) {

        SysFeedback sysFeedback = baseMapper.selectById(id);
        if (sysFeedback==null){
            throw new ServiceException(500,"信息不存在");
        }
        if (sysFeedback.getDisposeStatus()==0){
            throw new ServiceException(500,"已处理");
        }
        sysFeedback.setDisposeStatus(0);
        baseMapper.updateById(sysFeedback);
        return true;
    }
}
