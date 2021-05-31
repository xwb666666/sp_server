package cn.stylefeng.guns.cloud.order.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemAddressResult;
import cn.stylefeng.guns.cloud.api.order.GunsOrder;
import cn.stylefeng.guns.cloud.api.order.GunsOrderDetail;
import cn.stylefeng.guns.cloud.api.order.GunsOrderLog;
import cn.stylefeng.guns.cloud.api.product.model.params.CouponsRecordParam;
import cn.stylefeng.guns.cloud.api.product.model.result.CouponProductResult;
import cn.stylefeng.guns.cloud.api.product.model.result.CouponsResult;
import cn.stylefeng.guns.cloud.api.product.model.result.RemoteProductResult;
import cn.stylefeng.guns.cloud.api.product.model.result.RemoteSkuResult;

import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.order.config.OrderRabbitConfig;
import cn.stylefeng.guns.cloud.order.constants.Constant;
import cn.stylefeng.guns.cloud.order.consumer.RemoteMemberService;
import cn.stylefeng.guns.cloud.order.consumer.RemoteProductService;
import cn.stylefeng.guns.cloud.order.mapper.GunsOrdMapper;
import cn.stylefeng.guns.cloud.order.model.OrderConstant;
import cn.stylefeng.guns.cloud.order.model.api.param.ApiOrderParam;

import cn.stylefeng.guns.cloud.order.model.api.param.CreateOrderParam;
import cn.stylefeng.guns.cloud.order.model.api.param.OrderParam;
import cn.stylefeng.guns.cloud.order.model.api.result.OrderListResult;
import cn.stylefeng.guns.cloud.order.model.param.DeliverParam;
import cn.stylefeng.guns.cloud.order.model.param.OrderListParam;
import cn.stylefeng.guns.cloud.order.model.param.UpdateAddressParam;
import cn.stylefeng.guns.cloud.order.model.api.result.CartItemResult;
import cn.stylefeng.guns.cloud.order.model.result.OrderResult;
import cn.stylefeng.guns.cloud.order.model.api.result.PreOrderResult;
import cn.stylefeng.guns.cloud.order.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.redis.core.*;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GunsOrderServiceImpl extends ServiceImpl<GunsOrdMapper, GunsOrder> implements GunsOrdService {

    @Autowired
    private GunsOrdDetailService detailService;
    @Autowired
    private RemoteProductService productService;
    @Autowired
    private RemoteMemberService memberService;
    @Autowired
    private GunsOrderLogService logService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //后台订单基础信息
    @Override
    public Page<OrderResult> selectOrderList(OrderListParam param) {

        Page<OrderResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        //memberId查询订单信息
        Page<OrderResult> orderResultPage = page.setRecords(baseMapper.selectOrderList(page, param));
        for (OrderResult item : orderResultPage.getRecords()) {
            String orderNo = item.getOrderNo();
            QueryWrapper<GunsOrderDetail> query = new QueryWrapper<>();
            query.eq("order_no", orderNo).last("limit 3");
            List<GunsOrderDetail> details = detailService.list(query);

            List<OrderResult.OrderProduct> products = new ArrayList<>();
            details.forEach(detail -> {
                OrderResult.OrderProduct p = new OrderResult.OrderProduct();
                p.setName(detail.getProductName());
                p.setSpecs(detail.getSpecs());
                p.setProductId(detail.getProductId());
                p.setSalePrice(detail.getSalePrice());
                p.setQuantity(detail.getQuantity());
                products.add(p);
            });
            item.setProducts(products);
        }

        return orderResultPage;
    }

    @Override
    @Transactional
    public Boolean deleteByOrder(String orderNo) {

        GunsOrderLog orderLog = new GunsOrderLog();
        orderLog.setOrderNo(orderNo);
        orderLog.setUserType(0);
        Map<String, Object> content = new HashMap<>();
        content.put("params", JSONUtil.toJsonStr("删除成功"));


        QueryWrapper<GunsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        GunsOrder gunsOrder = baseMapper.selectOne(queryWrapper);
        if (gunsOrder == null) {

            throw new ServiceException(500, "订单不存在");
        }
        if (gunsOrder.getStatus() != OrderConstant.OrderStatus.Cancelled_Order.getValue()) {

            throw new ServiceException(500, "此订单无法删除");
        }

        QueryWrapper<GunsOrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        List<GunsOrderDetail> list = detailService.list(wrapper);

        this.remove(queryWrapper);
        detailService.remove(wrapper);

        orderLog.setContext(JSONUtil.toJsonStr(content));
        logService.save(orderLog);

        return true;
    }

    /**
     * app订单列表
     *
     * @param param
     * @return
     */
    @Override
    public Page<OrderListResult> orderList(ApiOrderParam param) {

        Page<OrderListResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        //memberId查询订单信息
        Page<OrderListResult> orderListResults = page.setRecords(baseMapper.selectForOrderList(page, param));
        for (OrderListResult item : orderListResults.getRecords()) {
            String orderNo = item.getOrderNo();
            QueryWrapper<GunsOrderDetail> query = new QueryWrapper<>();
            query.eq("order_no", orderNo).last("limit 2");
            List<GunsOrderDetail> details = detailService.list(query);

            List<OrderListResult.OrderProduct> products = new ArrayList<>();
            details.forEach(detail -> {
                OrderListResult.OrderProduct p = new OrderListResult.OrderProduct();
                p.setName(detail.getProductName());
                p.setSpecs(detail.getSpecs());
                p.setProductId(detail.getProductId());
                p.setSalePrice(detail.getSalePrice());
                p.setQuantity(detail.getQuantity());
                products.add(p);
            });
            item.setProducts(products);
        }

        return orderListResults;
    }

    @Override
    @Transactional
    public Boolean cancel(OrderParam param) {

        //获取日志信息
        GunsOrderLog orderLog = new GunsOrderLog();
        orderLog.setOrderNo(param.getOrderNo());
        orderLog.setUserId(param.getMemberId());
        orderLog.setUserType(0);
        Map<String, Object> content = new HashMap<>();
        content.put("params", JSONUtil.toJsonStr("取消成功"));


        QueryWrapper<GunsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", param.getMemberId()).eq("order_no", param.getOrderNo());
        GunsOrder gunsOrder = baseMapper.selectOne(queryWrapper);


        if (gunsOrder.getStatus() == 0) {     //0待付款
            UpdateWrapper<GunsOrder> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("member_id", param.getMemberId()).eq("order_no", param.getOrderNo());
            updateWrapper.set("status", 5);
            this.update(updateWrapper);
        } else {
            throw new ServiceException(500, "此订单无法删除");
        }

        orderLog.setContext(JSONUtil.toJsonStr(content));
        //保存日志信息
        logService.save(orderLog);

        return true;
    }


    @Override
    @Transactional
    public Boolean delete(OrderParam param) {

        //获取日志信息
        GunsOrderLog orderLog = new GunsOrderLog();
        orderLog.setOrderNo(param.getOrderNo());
        orderLog.setUserId(param.getMemberId());
        orderLog.setUserType(0);
        Map<String, Object> content = new HashMap<>();
        content.put("params", JSONUtil.toJsonStr("删除成功"));


        QueryWrapper<GunsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", param.getMemberId()).eq("order_no", param.getOrderNo());
        GunsOrder gunsOrder = baseMapper.selectOne(queryWrapper);

        QueryWrapper<GunsOrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", param.getOrderNo());
        List<GunsOrderDetail> orderDetailList = detailService.list(wrapper);
        if (gunsOrder == null && orderDetailList == null) {
            throw new ServiceException(500, "订单不存在");
        }
        if (gunsOrder.getStatus() == 5 || gunsOrder.getStatus() == 4) {
            this.remove(queryWrapper);
            detailService.remove(wrapper);
        } else {
            throw new ServiceException(500, "此订单不能删除");
        }

        orderLog.setContext(JSONUtil.toJsonStr(content));
        //保存日志信息
        logService.save(orderLog);

        return true;
    }

    /**
     * 后台 修改收货地址
     *
     * @param param
     * @return
     */
    @Override
    @Transactional
    public Boolean updateAddress(UpdateAddressParam param) {

        //获取日志信息
        GunsOrderLog orderLog = new GunsOrderLog();
        orderLog.setOrderNo(param.getOrderNo());
        orderLog.setUserType(0);
        Map<String, Object> content = new HashMap<>();
        content.put("params", JSONUtil.toJsonStr("修改成功"));


        if (param.getOrderNo() == null) {
            throw new ServiceException(500, "订单号不能为空");
        }
        QueryWrapper<GunsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", param.getOrderNo());
        GunsOrder gunsOrder = baseMapper.selectOne(queryWrapper);
        if (gunsOrder == null) {
            throw new ServiceException(500, "此订单不存在");
        }


        QueryWrapper<GunsOrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", param.getOrderNo());

        List<GunsOrderDetail> list = detailService.list(wrapper);
        for (GunsOrderDetail gunsOrderDetail:list){
            if (gunsOrderDetail.getShipStatus()!=0){

                throw new ServiceException(500,"不能修改地址");
            }
        }

        gunsOrder.setAddress(param.getAddress());
        gunsOrder.setMobile(param.getMobile());
        gunsOrder.setContacts(param.getContacts());
        baseMapper.update(gunsOrder, queryWrapper);

        orderLog.setContext(JSONUtil.toJsonStr(content));
        //保存日志信息
        logService.save(orderLog);

        return true;
    }

    /**
     * 预订单，去结算
     * @param memberId
     * @return
     */
    @Override
    public PreOrderResult preOrder(Long memberId) {
        List<Object> values = getCartOps(memberId).values();
        List<CartItemResult> cartItemResults = new ArrayList<CartItemResult>();

        if (values == null || values.size() == 0)
            throw new ServiceException(500, "购物车数据为空！");
        for (int i = 0; i < values.size(); i++) {
            CartItemResult item = JSON.parseObject(String.valueOf(values.get(i)), CartItemResult.class);
            if ( item.isCheck() ) { cartItemResults.add(item); };
        }
        PreOrderResult preOrderResult = packingPreOrder(cartItemResults);
        return preOrderResult;
    }

    /**
     * @return
     * @description 下单
     * @params
     */
    @Override
    @Transactional
    public PreOrderResult create(CreateOrderParam _param) {
        Long memberId = _param.getMemberId();
        DateTime now = DateTime.now();
        List<CreateOrderParam.ShopCart.ProductItem> orderProducts = new ArrayList<>();  //累计商品，redis中删除

        /** 调用，查询member信息 **/
        GunsMemAddressResult address = remoteMemAddresses(memberId, _param.getAddressId());

        GunsOrder order = packingOrder(_param, memberId, address, now);

        execute(_param, orderProducts, order, now);

        // 删除购物车已购商品
        deleteCartRedis(memberId, orderProducts);

        orderLog(now, order);
        PreOrderResult preOrderResult = new PreOrderResult();
        ToolUtil.copyProperties(_param, preOrderResult);
        ToolUtil.copyProperties(order, preOrderResult);
        return preOrderResult;
    }

    @Override
    public Boolean deliver(DeliverParam param) {

        //获取日志信息
        GunsOrderLog orderLog = new GunsOrderLog();
        orderLog.setOrderNo(param.getOrderNo());
        orderLog.setUserType(0);
        Map<String, Object> content = new HashMap<>();
        content.put("params", JSONUtil.toJsonStr("发货成功"));

        QueryWrapper<GunsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", param.getOrderNo());
        GunsOrder gunsOrder = baseMapper.selectOne(queryWrapper);
        if (gunsOrder == null) {
            throw new ServiceException(500, "订单不存在");
        }


        QueryWrapper<GunsOrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", param.getOrderNo()).in("product_id",param.getProductIds());

        List<GunsOrderDetail> list = detailService.list(wrapper);
        for (GunsOrderDetail gunsOrderDetail:list){
            if (gunsOrderDetail.getShipStatus()==0){

                gunsOrderDetail.setShipStatus(1);
                detailService.updateById(gunsOrderDetail);
            }else {
                throw new ServiceException(500,"订单状态不能修改");
            }

        }

        gunsOrder.setAddress(param.getAddress());
        gunsOrder.setExpId(param.getExpId());
        gunsOrder.setExpNo(param.getExpNo());
        baseMapper.update(gunsOrder, queryWrapper);


        orderLog.setContext(JSONUtil.toJsonStr(content));
        //保存日志信息
        logService.save(orderLog);

        return true;
    }



    /******************************************************************************************/



    //购物车商品选中准备下单，计算价格
    private PreOrderResult packingPreOrder(List<CartItemResult> cartItemResults) {
        PreOrderResult preOrderResult = new PreOrderResult();
        BigDecimal totalPriginalPrice = new BigDecimal(0);
        List<PreOrderResult.ShopCart> shopCarts = new ArrayList<>();
        Map<Object, List<CartItemResult>> collect = cartItemResults.stream().collect(Collectors.groupingBy(CartItemResult::getShopId));
        for (Map.Entry<Object, List<CartItemResult>> shipKey : collect.entrySet()) {
            //根据obj分组店铺信息获取数据
            List<PreOrderResult.ShopCart.ProductItem> cartItems = new ArrayList<>();
            PreOrderResult.ShopCart shopCart = new PreOrderResult.ShopCart();
            BigDecimal oresentPrice = new BigDecimal(0);
            BigDecimal priginalPrice = new BigDecimal(0);
            BigDecimal costPrice = new BigDecimal(0);

            shopCart.setShopId((long) shipKey.getKey());
            shopCart.setShopName(shipKey.getValue().get(0).getShopName());
            //将redis的result重组ShopCart.CartItem, 并计算
            for (CartItemResult cir : shipKey.getValue()) {
                PreOrderResult.ShopCart.ProductItem cartItem = new PreOrderResult.ShopCart.ProductItem();
                ToolUtil.copyProperties(cir, cartItem);

                cartItem.setQuantity(cir.getNumber());
                cartItem.setSkuInfo(listToString(cir.getSkuInfo(), ','));
                cartItems.add(cartItem);
                oresentPrice = oresentPrice.add(BigDecimal.valueOf(cir.getPresentPrice()).multiply(BigDecimal.valueOf(cir.getNumber())));
                priginalPrice = priginalPrice.add(BigDecimal.valueOf(cir.getOriginalPrice()).multiply(BigDecimal.valueOf(cir.getNumber())));
                costPrice = costPrice.add(BigDecimal.valueOf(cir.getCost()).multiply(BigDecimal.valueOf(cir.getNumber())));
            }
            shopCart.setTotalPresentPrice(oresentPrice.doubleValue());
            shopCart.setTotalOriginalPrice(priginalPrice.doubleValue());
            shopCart.setTotalCost(costPrice.doubleValue());
            shopCart.setProducts(cartItems);
            shopCarts.add(shopCart);
            //统计
            totalPriginalPrice = totalPriginalPrice.add(BigDecimal.valueOf(shopCart.getTotalPresentPrice()));
        }
        preOrderResult.setTotalPrice(totalPriginalPrice.doubleValue());
        preOrderResult.setShopCarts(shopCarts);

        /** 默认收货信息 **/
        preOrderResult.setExpId(001);
        preOrderResult.setExpNo("123456789");
        preOrderResult.setMobile("13012345678");
        preOrderResult.setAddress("北京市朝阳区");
        preOrderResult.setContacts("张三");
        return preOrderResult;
    }

    private GunsOrder packingOrder(CreateOrderParam param, Long memberId, GunsMemAddressResult address, DateTime now) {
        GunsOrder order = new GunsOrder();
        ToolUtil.copyProperties(param, order);
        order.setOrderNo(ToolUtil.getIdGen());
        order.setMemberId(memberId);
        order.setPayTime(null);
        order.setOrderTime(now);
        order.setCompleteTime(null);
        order.setStatus(OrderConstant.OrderStatus.New_Order.getValue()); //状态: 0待支付，1已支付，2已发货，3已收货，4已完成，5已取消
        order.setOrderType(OrderConstant.OrderType.Normal_Order.getValue());  //订单类型 0普通订单 1拼团订单 2兑换订单
        order.setPayment(param.getPayment());
        order.setRemark(param.getRemark());
        /** 调用会员默认地址，地址id为0情况为查询默认地址 */
        //if (StringUtils.isEmpty(param.getContacts()) && StringUtils.isEmpty(param.getAddress()) && StringUtils.isEmpty(param.getMobile())) {
            if(address!=null){
                order.setAddress(address.getAddress());   //详细地址
                order.setMobile(address.getPhone()); //电话
                order.setContacts(address.getName());  //姓名
            }
        //}


//        Double couponAmount = 0d;
//        if(useCoupon != null && useCoupon.getAmount() != null){
//            couponAmount = useCoupon.getAmount().doubleValue();
//        }
        order.setCouponAmount(param.getCouponAmount()); //优惠劵金额
        order.setDiscountAmount(0d);   //折扣金额
        order.setPromotionAmount(0d);  //促销金额

        /** 获取MemberAddress地址，与物流信息，计算运费 **/
        order.setFreightAmount(0d); //运费
        return order;
    }

    /**
     * @description 验价，商品状态确认， 计算价格组装GunsOrderDetail保存
     * @params
     * @return
     */
    private List<RemoteProductResult> packingDetailList(CreateOrderParam _param
            , List<CreateOrderParam.ShopCart.ProductItem> orderProducts
            , GunsOrder order


            , double promotionAmount) {
        List<RemoteProductResult> detailList = new ArrayList<>();
        List<CreateOrderParam.ShopCart> _shopCarts = _param.getShopCarts();
        for (CreateOrderParam.ShopCart _shop : _shopCarts) {
            int number = _shop.getProducts().size();
            for (CreateOrderParam.ShopCart.ProductItem _product : _shop.getProducts()) {
                Long productId = _product.getProductId();
                Double skuId = null;
                /** 调用，查询产品详情 **/
                RemoteProductResult remoteProduct_ = remoteProduct(productId, _product.getSkuId());
                remoteProduct_.setQuantity(Long.valueOf(_product.getQuantity().longValue()));
                GunsOrderDetail detail = packingOrderDetail(order.getOrderNo(), remoteProduct_, _product);
                //商品状态确认
                if(remoteProduct_.getStatus() < 1 || remoteProduct_.getStatus() > 2){  //商品状态 1.正常 2.上架
                    throw new ServiceException(500, "商品价格状态异动，无法下单购买！");
                }
                detail.setSalePrice(remoteProduct_.getPresentPrice().doubleValue());
                detail.setCostPrice(remoteProduct_.getCost().doubleValue());

                double nowPrice = remoteProduct_.getPresentPrice().doubleValue();
                if (remoteProduct_.getIsSku() == 1) {
                    //属于规格商品，重组价格
                    List<RemoteSkuResult> skuList = remoteProduct_.getSkuInfo();
                    for (RemoteSkuResult skuItem : skuList) {
                        if (skuItem.getId().equals(_product.getSkuId())) {
                            skuId = Double.valueOf(skuItem.getId());
                            nowPrice = skuItem.getPresentPrice().longValue();
                            detail.setSalePrice(skuItem.getPresentPrice());
                            detail.setCostPrice(skuItem.getCost());
                        }
                    }
                }
                if(nowPrice != _product.getPresentPrice()){
                    throw new ServiceException(500, "商品价格异常，无法下单购买！");
                }

                //促销 = 规则分摊
                //detail.setPromotionAmount( BigDecimal.valueOf(promotionAmount).divide(BigDecimal.valueOf(Long.valueOf(number))).doubleValue() );
                //折扣 = 规则分摊
                //detail.setDiscountAmount( BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(Long.valueOf(number))).doubleValue() );

                /** 优惠券 规则分摊 **/
                /** 调用，获取会员的优惠券 **/
                Double coupon = 0d;
                Double validCoupon = 0d;
                Double discount = 0d;
                Double maxDisValue = 0d;    //最多折扣多少元。缺字段
                List<CouponsResult> useCoupons= new ArrayList<CouponsResult>();
                useCoupons = remoteCouponsResult(_param, order, useCoupons);
                for(CouponsResult couponsResult : useCoupons){
                    for(CouponProductResult productResult : couponsResult.getList()){
                        if(productResult.getProductId().equals(remoteProduct_.getId())){
                            //该商品可以使用劵,只取一个劵
                            if(couponsResult.getType() == 1){
                                //满减
                                coupon = couponsResult.getAmount().doubleValue();
                            }
                            if(couponsResult.getType() == 2){
                                //折扣
                                discount = couponsResult.getDiscount().doubleValue();
                            }
                            validCoupon = couponsResult.getValidAmount().doubleValue();
                        }
                    }
                }
                if(nowPrice >= validCoupon){
                    //满规则金额减
                    double realCoupon = coupon >= nowPrice ? nowPrice : coupon;
                    detail.setCouponAmount(realCoupon);
                }
//                if(coupon != 0){
//                    detail.setCouponAmount( BigDecimal.valueOf(coupon)
//                            .divide(
//                                    BigDecimal.valueOf(Long.valueOf(number))).doubleValue() );
//                }
                //销售价 = 真实价格 - (优惠券）
                double v = BigDecimal.valueOf(nowPrice)
                        .subtract(
                                BigDecimal.valueOf(detail.getCouponAmount())).doubleValue();
                detail.setSalePrice( v > 0 ? v : 0 );

                /** TODO 折扣缺字段 **/
                /*if(nowPrice >= validCoupon){
                    //折扣 = nowPrice - ((nowPrice * 折扣) >= maxDisValue ? (nowPrice * 折扣) : maxDisValue )
                    double realDisCount = nowPrice - ((nowPrice * discount) >= maxDisValue ? (nowPrice * discount) : maxDisValue );
                    detail.setDiscountAmount(realDisCount);
                }*/

                remoteProduct_.setCouponAmount(detail.getCouponAmount());
                remoteProduct_.setPresentPrice(BigDecimal.valueOf(detail.getSalePrice()));
                detailList.add(remoteProduct_);

                //商品比价： 实时价格与购物车内价格 (PresentPrice = PresentPrice - PromotionAmount - DiscountAmount - CostPrice)
                _product.setPresentPrice( _product.getPresentPrice() - remoteProduct_.getPromotionAmount() - remoteProduct_.getDiscountAmount() - remoteProduct_.getCouponAmount() );

                _product.setPresentPrice(detail.getSalePrice());
                orderProducts.add(_product);
                /** 预扣减库存。。。 **/
                preCutStock(Double.valueOf(detail.getProductId()), detail.getQuantity(), skuId, order.getOrderNo());
                detailService.save(detail);
            }
        }
        return detailList;
    }

    private GunsOrderDetail packingOrderDetail(String orderNo, RemoteProductResult _product, CreateOrderParam.ShopCart.ProductItem _param) {
        GunsOrderDetail detail = new GunsOrderDetail();
        ToolUtil.copyProperties(_product, detail);
        String sku = "";
        if(_product!= null && _product.getSkuInfo() != null){
            for( RemoteSkuResult item : _product.getSkuInfo() ){
                if(_param.getSkuId().equals(item.getId())){
                    sku = item.getSkuNames().toString();
                }
            }
        }
        detail.setSpecs(sku);  //规格

        detail.setId(ToolUtil.getIdGenLong());
        detail.setOrderNo(orderNo);
        detail.setProductId(_product.getId());
        detail.setProductName(_product.getName());
        detail.setQuantity(_param.getQuantity());

        detail.setShipStatus(0);    //未发货,字段不能为空
        detail.setRefundStatus(0);  //未退款,字段不能为空
        //detail.setCostPrice(_product.getCost().doubleValue());
        //detail.setSalePrice(_product.getPresentPrice().doubleValue());
        detail.setCouponAmount(0d); //优惠劵金额
        detail.setDiscountAmount(0d);   //折扣金额
        detail.setPromotionAmount(0d);  //促销金额
        return detail;
    }

    /** 远程调用 **/
    private RemoteProductResult remoteProduct(Long productId, Long skuId) {
        Map<String, Long> map = new HashMap<>();
        map.put("productId", productId);
        map.put("skuId", skuId);
        RemoteProductResult result = productService.productDetail(map);
        result.setCouponAmount(0d);
        result.setDiscountAmount(0d);
        result.setPromotionAmount(0d);
        //ApiProductDetailResult product = (ApiProductDetailResult) resultProduct.getData();
        return result;
    }
    private GunsMemAddressResult remoteMemAddresses(Long memberId, Long addressId) {
        GunsMemAddressResult resultMember = memberService.getMemAddress(memberId, addressId);
        return resultMember;
    }
    private List<CouponsResult> remoteCouponsResult(CreateOrderParam param, GunsOrder order, List<CouponsResult> useCoupons) {
        CouponsRecordParam couponsRecordParam = new CouponsRecordParam();
        couponsRecordParam.setMemberId(order.getMemberId());
        couponsRecordParam.setStatus(Constant.COUPON_RECORD_STATUS_PICK);
        ResponseData responseData = productService.couponRecordPage(couponsRecordParam);
        List<Long> couponIds = new ArrayList<>();//param.getCouponIds();
        couponIds.add(1390593941904166913l);couponIds.add(2l);
        if(responseData.getData() != null && couponIds != null && couponIds.size() > 0){
            PageResult<CouponsResult> pages = ((JSONObject) responseData.getData()).toJavaObject(PageResult.class);
            List rows = pages.getRows();
            for(Object jsonObject :rows){
                CouponsResult couponsResult = ((JSONObject) jsonObject).toJavaObject(CouponsResult.class);
                for(Long coupon : couponIds){
                    if(coupon.equals(couponsResult.getId())){
                        //会员使用的优惠券
                        useCoupons.add(couponsResult);
                    }
                }
            }
        }
        return useCoupons;
    }

    private GunsOrder execute(CreateOrderParam _param, List<CreateOrderParam.ShopCart.ProductItem> orderProducts, GunsOrder order, DateTime now) {

        double coupon = _param.getCouponAmount();  //优惠券
        double discount = order.getDiscountAmount(); //折扣
        double promotionAmount = order.getPromotionAmount();    //促销
        double freightAmount = order.getFreightAmount();//运费
        List<RemoteProductResult> detailList = packingDetailList(_param, orderProducts, order, promotionAmount);

        BigDecimal totalP = new BigDecimal(0);
        BigDecimal couponAmount = new BigDecimal(0);
        BigDecimal proAmount = new BigDecimal(0);
        BigDecimal disAmount = new BigDecimal(0);
        for(RemoteProductResult detail : detailList){
            //实时价累计. totalP += detail.getPresentPrice() * detail.getQuantity()
            totalP = totalP.add( detail.getPresentPrice()
                            .multiply(
                                    BigDecimal.valueOf(detail.getQuantity()))
            );
            //总优惠券 =  商品累计优惠券
            couponAmount = couponAmount.add( BigDecimal.valueOf(detail.getCouponAmount()));
            //折扣
            //disAmount = disAmount.add(BigDecimal.valueOf(detail.getDiscountAmount()));
            //促销
            //proAmount = proAmount.add(BigDecimal.valueOf(detail.getPromotionAmount()));

        }
        // 实付 = 总价 - 优惠劵 - 促销 - 折扣 - 运费
        double realPayPrice = totalP
                .subtract(BigDecimal.valueOf(coupon))
                .subtract(BigDecimal.valueOf(discount))
                .subtract(BigDecimal.valueOf(promotionAmount))
                .subtract(BigDecimal.valueOf(freightAmount)) .doubleValue();
        //验证价格 总价 || 付款金额
        if(totalP.doubleValue() != _param.getTotalPrice() || realPayPrice != _param.getRealPayPrice()){
            throw new ServiceException(500, "商品价格状态异动，无法下单购买！");    //异常事务回滚
        }

        order.setTotalPrice(totalP.doubleValue());
        order.setRealPayPrice(realPayPrice);
        order.setOrderTime(now);
        baseMapper.insert(order);
        return order;
    }

    private void deleteCartRedis(Long memberId, List<CreateOrderParam.ShopCart.ProductItem> orderProducts) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps(memberId);
        for (CreateOrderParam.ShopCart.ProductItem prod : orderProducts) {
            String key2 = prod.getProductId()+"_"+prod.getSkuId();
            cartOps.delete(key2);
        }
    }

    private void preCutStock(Double productId, Double quantity, Double skuId, String orderId){
        Map<String, Double> map = new HashMap<>();
        map.put("productId", productId);
        map.put("quantity", quantity);
        map.put("skuId", skuId);
        // TODO 待添加orderId，orderDetailId，等做状态变更
        //boolean result = productService.cutStock(map);

        String routingKey = OrderRabbitConfig.STOCK;
        String exchangeName = OrderRabbitConfig.STOCK_EXCHANGE_NAME;
//        final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                System.err.println("correlationData: " + correlationData);
//                String messageId = correlationData.getId();
//                if (ack) {
//                    //如果confirm返回成功 则进行更新
//                    log.info("-------------扣减库存confirm返回成功 则更新");
//                } else {
//                    //失败则进行具体的后续操作:重试 或者补偿等手段
//                    log.info("-------------扣减库存异常处理...");
//                }
//            }
//        };
//        rabbitTemplate.setConfirmCallback(confirmCallback);
        //消息进入交换器回调,不管是否进入队列
//        final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback(){
//            @Override
//            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
//                log.error("投递 mq消息   queue 不可达,message:{},replyCode:{},replyText:{},exchange:{},routing:{}", message.toString(), i, s, s2, routingKey);
//                String messageId = message.getMessageProperties().getMessageId();
//                log.error("******************returnedMessage-messageId:"+messageId);
//            }
//        };
//
//        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData(orderId);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, map, correlationData);

    }

    private void orderLog(DateTime now, GunsOrder order) {
        GunsOrderLog gunsOrderLog = new GunsOrderLog();
        gunsOrderLog.setOrderNo(order.getOrderNo());
        gunsOrderLog.setId(ToolUtil.getIdGenLong());
        gunsOrderLog.setUserId(order.getMemberId());
        gunsOrderLog.setUserType(2); //0.管理员 2.会员
        gunsOrderLog.setCreateTime(now);
        gunsOrderLog.setContext(JSON.toJSONString(order));
        logService.save(gunsOrderLog);
    }

    private String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return list.isEmpty() ? "" : sb.toString().substring(0, sb.toString().length() - 1);
    }

    private BoundHashOperations<String, Object, Object> getCartOps(Long memberId) {
        String prefix = "cart:";
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(prefix + memberId.toString());
        return operations;
    }

}
