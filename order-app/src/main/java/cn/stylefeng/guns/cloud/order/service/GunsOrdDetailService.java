package cn.stylefeng.guns.cloud.order.service;

import cn.stylefeng.guns.cloud.api.order.GunsOrderDetail;
import cn.stylefeng.guns.cloud.order.model.api.result.ApiOrderDetailResult;
import cn.stylefeng.guns.cloud.order.model.result.OrderDetailResult;
import com.baomidou.mybatisplus.extension.service.IService;

public interface GunsOrdDetailService extends IService<GunsOrderDetail> {


    OrderDetailResult selectOrderDetail(String orderNo);

    ApiOrderDetailResult selectByOrderDetail(String orderNo);
}
