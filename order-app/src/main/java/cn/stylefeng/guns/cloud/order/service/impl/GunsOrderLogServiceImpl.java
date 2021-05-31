package cn.stylefeng.guns.cloud.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.stylefeng.guns.cloud.api.order.GunsOrderLog;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProGroup;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProGroupResult;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.order.mapper.GunsOrderLogMapper;
import cn.stylefeng.guns.cloud.order.model.result.OrderLogResult;
import cn.stylefeng.guns.cloud.order.model.result.OrderResult;
import cn.stylefeng.guns.cloud.order.service.GunsOrderLogService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GunsOrderLogServiceImpl extends ServiceImpl<GunsOrderLogMapper, GunsOrderLog> implements GunsOrderLogService {


    @Override
    public Page<OrderLogResult> selectOrderLog() {
        Page<GunsOrderLog> page = new Page<>();
        page.setSize(20);
        page.setCurrent(1);

        //获取所有数据
        page=baseMapper.selectPage(page,null);
        List<OrderLogResult> list = new ArrayList<>();

        for (GunsOrderLog gunsOrderLog : page.getRecords()) {
            OrderLogResult orderLogResult = new OrderLogResult();
            ToolUtil.copyProperties(gunsOrderLog, orderLogResult);
            list.add(orderLogResult);
        }

        Page<OrderLogResult> pageResult = new Page<>();
        pageResult.setSize(page.getSize());
        pageResult.setCurrent(page.getCurrent());
        pageResult.setPages(page.getPages());
        pageResult.setRecords(list);
        pageResult.setTotal(page.getTotal());
        return pageResult;

    }


}