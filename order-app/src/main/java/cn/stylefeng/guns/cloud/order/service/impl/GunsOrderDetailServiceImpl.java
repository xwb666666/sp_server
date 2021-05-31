package cn.stylefeng.guns.cloud.order.service.impl;
import cn.hutool.json.JSONUtil;
import cn.stylefeng.guns.cloud.api.order.GunsOrder;
import cn.stylefeng.guns.cloud.api.order.GunsOrderDetail;
import cn.stylefeng.guns.cloud.api.order.GunsOrderLog;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.order.mapper.GunsOrdDetailMapper;
import cn.stylefeng.guns.cloud.order.model.api.result.ApiOrderDetailResult;
import cn.stylefeng.guns.cloud.order.model.result.OrderDetailResult;
import cn.stylefeng.guns.cloud.order.service.GunsOrdDetailService;
import cn.stylefeng.guns.cloud.order.service.GunsOrdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.util.concurrent.AtomicDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class GunsOrderDetailServiceImpl extends ServiceImpl<GunsOrdDetailMapper, GunsOrderDetail> implements GunsOrdDetailService {


    @Autowired
    private GunsOrdService ordService;

    @Override
    public OrderDetailResult selectOrderDetail(String orderNo) {



        //1、订单基础表
        QueryWrapper<GunsOrder>wrapper=new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        GunsOrder order = ordService.getOne(wrapper);

        //2、订单详情表
        QueryWrapper<GunsOrderDetail>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);

        List<GunsOrderDetail> gunsOrderDetails = baseMapper.selectList(queryWrapper);

        List<OrderDetailResult.OrderProduct> products = new ArrayList<>();
        for(GunsOrderDetail detail : gunsOrderDetails){
            OrderDetailResult.OrderProduct p = new OrderDetailResult.OrderProduct();
            p.setName(detail.getProductName());
            p.setSpecs(detail.getSpecs());
            p.setProductId(detail.getProductId());
            p.setSalePrice(detail.getSalePrice());
            p.setQuantity(detail.getQuantity());
            products.add(p);
        }

        //3、组合数据返回
        OrderDetailResult orderDetailResult = new OrderDetailResult();
        orderDetailResult.setOrderNo(orderNo);
        orderDetailResult.setAddress(order.getAddress());
        orderDetailResult.setOrderTime(order.getOrderTime());
        orderDetailResult.setContacts(order.getContacts());
        orderDetailResult.setExpId(order.getExpId());
        orderDetailResult.setExpId(order.getExpId());
        orderDetailResult.setPayment(order.getPayment());
        orderDetailResult.setMobile(order.getMobile());
        orderDetailResult.setRealPayPrice(order.getRealPayPrice());
        orderDetailResult.setTotalPrice(order.getTotalPrice());
        orderDetailResult.setProducts(products);
        orderDetailResult.setStatus(order.getStatus());
        orderDetailResult.setFreightAmount(order.getFreightAmount());
        orderDetailResult.setCouponAmount(order.getCouponAmount());

        return orderDetailResult;
    }

    @Override
    public ApiOrderDetailResult selectByOrderDetail(String orderNo) {

        //1、订单基础表
        QueryWrapper<GunsOrder>wrapper=new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        GunsOrder order = ordService.getOne(wrapper);

        //2、订单详情表
        QueryWrapper<GunsOrderDetail>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);

        List<GunsOrderDetail> gunsOrderDetails = baseMapper.selectList(queryWrapper);

        ;
        List<ApiOrderDetailResult.OrderProduct> products = new ArrayList<>();
        for(GunsOrderDetail detail : gunsOrderDetails){
            ApiOrderDetailResult.OrderProduct p = new ApiOrderDetailResult.OrderProduct();
            p.setName(detail.getProductName());
            p.setSpecs(detail.getSpecs());
            p.setProductId(detail.getProductId());
            p.setSalePrice(detail.getSalePrice());
            p.setQuantity(detail.getQuantity());
            products.add(p);
        }

        //3、组合数据返回
        ApiOrderDetailResult apiOrderDetailResult = new ApiOrderDetailResult();
        apiOrderDetailResult.setOrderNo(orderNo);
        apiOrderDetailResult.setAddress(order.getAddress());
        apiOrderDetailResult.setOrderTime(order.getOrderTime());
        apiOrderDetailResult.setContacts(order.getContacts());
        apiOrderDetailResult.setExpId(order.getExpId());
        apiOrderDetailResult.setExpId(order.getExpId());
        apiOrderDetailResult.setPayment(order.getPayment());
        apiOrderDetailResult.setMobile(order.getMobile());
        apiOrderDetailResult.setRealPayPrice(order.getRealPayPrice());//实付金额
        apiOrderDetailResult.setTotalPrice(order.getTotalPrice()); //总金额
        apiOrderDetailResult.setRemark(order.getRemark());
        apiOrderDetailResult.setPayTime(order.getPayTime());
        apiOrderDetailResult.setProducts(products);
        apiOrderDetailResult.setStatus(order.getStatus());
        apiOrderDetailResult.setOrderType(order.getOrderType());
        apiOrderDetailResult.setCompleteTime(order.getCompleteTime());
        apiOrderDetailResult.setFreightAmount(order.getFreightAmount());
        apiOrderDetailResult.setCouponAmount(order.getCouponAmount());

        return apiOrderDetailResult;
    }
}
