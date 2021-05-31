package cn.stylefeng.guns.cloud.order.mapper;

import cn.stylefeng.guns.cloud.api.order.GunsOrder;
import cn.stylefeng.guns.cloud.order.model.api.param.ApiOrderParam;
import cn.stylefeng.guns.cloud.order.model.api.result.OrderListResult;
import cn.stylefeng.guns.cloud.order.model.param.OrderListParam;
import cn.stylefeng.guns.cloud.order.model.result.OrderResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface GunsOrdMapper extends BaseMapper<GunsOrder> {

    List<OrderResult> selectOrderList(Page<OrderResult>page, OrderListParam param);

    List<OrderListResult> selectForOrderList(Page<OrderListResult>page,ApiOrderParam param);
}
