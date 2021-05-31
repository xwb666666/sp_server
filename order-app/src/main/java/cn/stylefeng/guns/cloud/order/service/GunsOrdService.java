package cn.stylefeng.guns.cloud.order.service;

import cn.stylefeng.guns.cloud.api.order.GunsOrder;
import cn.stylefeng.guns.cloud.order.model.api.param.ApiOrderParam;
import cn.stylefeng.guns.cloud.order.model.api.param.CreateOrderParam;
import cn.stylefeng.guns.cloud.order.model.api.param.OrderParam;
import cn.stylefeng.guns.cloud.order.model.api.result.OrderListResult;
import cn.stylefeng.guns.cloud.order.model.param.DeliverParam;
import cn.stylefeng.guns.cloud.order.model.param.OrderListParam;
import cn.stylefeng.guns.cloud.order.model.param.UpdateAddressParam;
import cn.stylefeng.guns.cloud.order.model.result.OrderResult;
import cn.stylefeng.guns.cloud.order.model.api.result.PreOrderResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface GunsOrdService extends IService<GunsOrder> {

    Page<OrderResult> selectOrderList(OrderListParam param);

    Boolean deleteByOrder(String orderNo);

    Page<OrderListResult> orderList(ApiOrderParam param);


    Boolean cancel(OrderParam param);

    Boolean delete(OrderParam param);

    Boolean updateAddress(UpdateAddressParam param);

    PreOrderResult preOrder(Long memberId);

    PreOrderResult create(CreateOrderParam param);

    Boolean deliver(DeliverParam param);
}
