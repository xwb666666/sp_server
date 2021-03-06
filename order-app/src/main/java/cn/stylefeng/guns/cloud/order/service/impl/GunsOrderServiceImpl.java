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

    //????????????????????????
    @Override
    public Page<OrderResult> selectOrderList(OrderListParam param) {

        Page<OrderResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        //memberId??????????????????
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
        content.put("params", JSONUtil.toJsonStr("????????????"));


        QueryWrapper<GunsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        GunsOrder gunsOrder = baseMapper.selectOne(queryWrapper);
        if (gunsOrder == null) {

            throw new ServiceException(500, "???????????????");
        }
        if (gunsOrder.getStatus() != OrderConstant.OrderStatus.Cancelled_Order.getValue()) {

            throw new ServiceException(500, "?????????????????????");
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
     * app????????????
     *
     * @param param
     * @return
     */
    @Override
    public Page<OrderListResult> orderList(ApiOrderParam param) {

        Page<OrderListResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        //memberId??????????????????
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

        //??????????????????
        GunsOrderLog orderLog = new GunsOrderLog();
        orderLog.setOrderNo(param.getOrderNo());
        orderLog.setUserId(param.getMemberId());
        orderLog.setUserType(0);
        Map<String, Object> content = new HashMap<>();
        content.put("params", JSONUtil.toJsonStr("????????????"));


        QueryWrapper<GunsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", param.getMemberId()).eq("order_no", param.getOrderNo());
        GunsOrder gunsOrder = baseMapper.selectOne(queryWrapper);


        if (gunsOrder.getStatus() == 0) {     //0?????????
            UpdateWrapper<GunsOrder> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("member_id", param.getMemberId()).eq("order_no", param.getOrderNo());
            updateWrapper.set("status", 5);
            this.update(updateWrapper);
        } else {
            throw new ServiceException(500, "?????????????????????");
        }

        orderLog.setContext(JSONUtil.toJsonStr(content));
        //??????????????????
        logService.save(orderLog);

        return true;
    }


    @Override
    @Transactional
    public Boolean delete(OrderParam param) {

        //??????????????????
        GunsOrderLog orderLog = new GunsOrderLog();
        orderLog.setOrderNo(param.getOrderNo());
        orderLog.setUserId(param.getMemberId());
        orderLog.setUserType(0);
        Map<String, Object> content = new HashMap<>();
        content.put("params", JSONUtil.toJsonStr("????????????"));


        QueryWrapper<GunsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", param.getMemberId()).eq("order_no", param.getOrderNo());
        GunsOrder gunsOrder = baseMapper.selectOne(queryWrapper);

        QueryWrapper<GunsOrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", param.getOrderNo());
        List<GunsOrderDetail> orderDetailList = detailService.list(wrapper);
        if (gunsOrder == null && orderDetailList == null) {
            throw new ServiceException(500, "???????????????");
        }
        if (gunsOrder.getStatus() == 5 || gunsOrder.getStatus() == 4) {
            this.remove(queryWrapper);
            detailService.remove(wrapper);
        } else {
            throw new ServiceException(500, "?????????????????????");
        }

        orderLog.setContext(JSONUtil.toJsonStr(content));
        //??????????????????
        logService.save(orderLog);

        return true;
    }

    /**
     * ?????? ??????????????????
     *
     * @param param
     * @return
     */
    @Override
    @Transactional
    public Boolean updateAddress(UpdateAddressParam param) {

        //??????????????????
        GunsOrderLog orderLog = new GunsOrderLog();
        orderLog.setOrderNo(param.getOrderNo());
        orderLog.setUserType(0);
        Map<String, Object> content = new HashMap<>();
        content.put("params", JSONUtil.toJsonStr("????????????"));


        if (param.getOrderNo() == null) {
            throw new ServiceException(500, "?????????????????????");
        }
        QueryWrapper<GunsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", param.getOrderNo());
        GunsOrder gunsOrder = baseMapper.selectOne(queryWrapper);
        if (gunsOrder == null) {
            throw new ServiceException(500, "??????????????????");
        }


        QueryWrapper<GunsOrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", param.getOrderNo());

        List<GunsOrderDetail> list = detailService.list(wrapper);
        for (GunsOrderDetail gunsOrderDetail:list){
            if (gunsOrderDetail.getShipStatus()!=0){

                throw new ServiceException(500,"??????????????????");
            }
        }

        gunsOrder.setAddress(param.getAddress());
        gunsOrder.setMobile(param.getMobile());
        gunsOrder.setContacts(param.getContacts());
        baseMapper.update(gunsOrder, queryWrapper);

        orderLog.setContext(JSONUtil.toJsonStr(content));
        //??????????????????
        logService.save(orderLog);

        return true;
    }

    /**
     * ?????????????????????
     * @param memberId
     * @return
     */
    @Override
    public PreOrderResult preOrder(Long memberId) {
        List<Object> values = getCartOps(memberId).values();
        List<CartItemResult> cartItemResults = new ArrayList<CartItemResult>();

        if (values == null || values.size() == 0)
            throw new ServiceException(500, "????????????????????????");
        for (int i = 0; i < values.size(); i++) {
            CartItemResult item = JSON.parseObject(String.valueOf(values.get(i)), CartItemResult.class);
            if ( item.isCheck() ) { cartItemResults.add(item); };
        }
        PreOrderResult preOrderResult = packingPreOrder(cartItemResults);
        return preOrderResult;
    }

    /**
     * @return
     * @description ??????
     * @params
     */
    @Override
    @Transactional
    public PreOrderResult create(CreateOrderParam _param) {
        Long memberId = _param.getMemberId();
        DateTime now = DateTime.now();
        List<CreateOrderParam.ShopCart.ProductItem> orderProducts = new ArrayList<>();  //???????????????redis?????????

        /** ???????????????member?????? **/
        GunsMemAddressResult address = remoteMemAddresses(memberId, _param.getAddressId());

        GunsOrder order = packingOrder(_param, memberId, address, now);

        execute(_param, orderProducts, order, now);

        // ???????????????????????????
        deleteCartRedis(memberId, orderProducts);

        orderLog(now, order);
        PreOrderResult preOrderResult = new PreOrderResult();
        ToolUtil.copyProperties(_param, preOrderResult);
        ToolUtil.copyProperties(order, preOrderResult);
        return preOrderResult;
    }

    @Override
    public Boolean deliver(DeliverParam param) {

        //??????????????????
        GunsOrderLog orderLog = new GunsOrderLog();
        orderLog.setOrderNo(param.getOrderNo());
        orderLog.setUserType(0);
        Map<String, Object> content = new HashMap<>();
        content.put("params", JSONUtil.toJsonStr("????????????"));

        QueryWrapper<GunsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", param.getOrderNo());
        GunsOrder gunsOrder = baseMapper.selectOne(queryWrapper);
        if (gunsOrder == null) {
            throw new ServiceException(500, "???????????????");
        }


        QueryWrapper<GunsOrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", param.getOrderNo()).in("product_id",param.getProductIds());

        List<GunsOrderDetail> list = detailService.list(wrapper);
        for (GunsOrderDetail gunsOrderDetail:list){
            if (gunsOrderDetail.getShipStatus()==0){

                gunsOrderDetail.setShipStatus(1);
                detailService.updateById(gunsOrderDetail);
            }else {
                throw new ServiceException(500,"????????????????????????");
            }

        }

        gunsOrder.setAddress(param.getAddress());
        gunsOrder.setExpId(param.getExpId());
        gunsOrder.setExpNo(param.getExpNo());
        baseMapper.update(gunsOrder, queryWrapper);


        orderLog.setContext(JSONUtil.toJsonStr(content));
        //??????????????????
        logService.save(orderLog);

        return true;
    }



    /******************************************************************************************/



    //????????????????????????????????????????????????
    private PreOrderResult packingPreOrder(List<CartItemResult> cartItemResults) {
        PreOrderResult preOrderResult = new PreOrderResult();
        BigDecimal totalPriginalPrice = new BigDecimal(0);
        List<PreOrderResult.ShopCart> shopCarts = new ArrayList<>();
        Map<Object, List<CartItemResult>> collect = cartItemResults.stream().collect(Collectors.groupingBy(CartItemResult::getShopId));
        for (Map.Entry<Object, List<CartItemResult>> shipKey : collect.entrySet()) {
            //??????obj??????????????????????????????
            List<PreOrderResult.ShopCart.ProductItem> cartItems = new ArrayList<>();
            PreOrderResult.ShopCart shopCart = new PreOrderResult.ShopCart();
            BigDecimal oresentPrice = new BigDecimal(0);
            BigDecimal priginalPrice = new BigDecimal(0);
            BigDecimal costPrice = new BigDecimal(0);

            shopCart.setShopId((long) shipKey.getKey());
            shopCart.setShopName(shipKey.getValue().get(0).getShopName());
            //???redis???result??????ShopCart.CartItem, ?????????
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
            //??????
            totalPriginalPrice = totalPriginalPrice.add(BigDecimal.valueOf(shopCart.getTotalPresentPrice()));
        }
        preOrderResult.setTotalPrice(totalPriginalPrice.doubleValue());
        preOrderResult.setShopCarts(shopCarts);

        /** ?????????????????? **/
        preOrderResult.setExpId(001);
        preOrderResult.setExpNo("123456789");
        preOrderResult.setMobile("13012345678");
        preOrderResult.setAddress("??????????????????");
        preOrderResult.setContacts("??????");
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
        order.setStatus(OrderConstant.OrderStatus.New_Order.getValue()); //??????: 0????????????1????????????2????????????3????????????4????????????5?????????
        order.setOrderType(OrderConstant.OrderType.Normal_Order.getValue());  //???????????? 0???????????? 1???????????? 2????????????
        order.setPayment(param.getPayment());
        order.setRemark(param.getRemark());
        /** ?????????????????????????????????id???0??????????????????????????? */
        //if (StringUtils.isEmpty(param.getContacts()) && StringUtils.isEmpty(param.getAddress()) && StringUtils.isEmpty(param.getMobile())) {
            if(address!=null){
                order.setAddress(address.getAddress());   //????????????
                order.setMobile(address.getPhone()); //??????
                order.setContacts(address.getName());  //??????
            }
        //}


//        Double couponAmount = 0d;
//        if(useCoupon != null && useCoupon.getAmount() != null){
//            couponAmount = useCoupon.getAmount().doubleValue();
//        }
        order.setCouponAmount(param.getCouponAmount()); //???????????????
        order.setDiscountAmount(0d);   //????????????
        order.setPromotionAmount(0d);  //????????????

        /** ??????MemberAddress??????????????????????????????????????? **/
        order.setFreightAmount(0d); //??????
        return order;
    }

    /**
     * @description ?????????????????????????????? ??????????????????GunsOrderDetail??????
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
                /** ??????????????????????????? **/
                RemoteProductResult remoteProduct_ = remoteProduct(productId, _product.getSkuId());
                remoteProduct_.setQuantity(Long.valueOf(_product.getQuantity().longValue()));
                GunsOrderDetail detail = packingOrderDetail(order.getOrderNo(), remoteProduct_, _product);
                //??????????????????
                if(remoteProduct_.getStatus() < 1 || remoteProduct_.getStatus() > 2){  //???????????? 1.?????? 2.??????
                    throw new ServiceException(500, "????????????????????????????????????????????????");
                }
                detail.setSalePrice(remoteProduct_.getPresentPrice().doubleValue());
                detail.setCostPrice(remoteProduct_.getCost().doubleValue());

                double nowPrice = remoteProduct_.getPresentPrice().doubleValue();
                if (remoteProduct_.getIsSku() == 1) {
                    //?????????????????????????????????
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
                    throw new ServiceException(500, "??????????????????????????????????????????");
                }

                //?????? = ????????????
                //detail.setPromotionAmount( BigDecimal.valueOf(promotionAmount).divide(BigDecimal.valueOf(Long.valueOf(number))).doubleValue() );
                //?????? = ????????????
                //detail.setDiscountAmount( BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(Long.valueOf(number))).doubleValue() );

                /** ????????? ???????????? **/
                /** ????????????????????????????????? **/
                Double coupon = 0d;
                Double validCoupon = 0d;
                Double discount = 0d;
                Double maxDisValue = 0d;    //?????????????????????????????????
                List<CouponsResult> useCoupons= new ArrayList<CouponsResult>();
                useCoupons = remoteCouponsResult(_param, order, useCoupons);
                for(CouponsResult couponsResult : useCoupons){
                    for(CouponProductResult productResult : couponsResult.getList()){
                        if(productResult.getProductId().equals(remoteProduct_.getId())){
                            //????????????????????????,???????????????
                            if(couponsResult.getType() == 1){
                                //??????
                                coupon = couponsResult.getAmount().doubleValue();
                            }
                            if(couponsResult.getType() == 2){
                                //??????
                                discount = couponsResult.getDiscount().doubleValue();
                            }
                            validCoupon = couponsResult.getValidAmount().doubleValue();
                        }
                    }
                }
                if(nowPrice >= validCoupon){
                    //??????????????????
                    double realCoupon = coupon >= nowPrice ? nowPrice : coupon;
                    detail.setCouponAmount(realCoupon);
                }
//                if(coupon != 0){
//                    detail.setCouponAmount( BigDecimal.valueOf(coupon)
//                            .divide(
//                                    BigDecimal.valueOf(Long.valueOf(number))).doubleValue() );
//                }
                //????????? = ???????????? - (????????????
                double v = BigDecimal.valueOf(nowPrice)
                        .subtract(
                                BigDecimal.valueOf(detail.getCouponAmount())).doubleValue();
                detail.setSalePrice( v > 0 ? v : 0 );

                /** TODO ??????????????? **/
                /*if(nowPrice >= validCoupon){
                    //?????? = nowPrice - ((nowPrice * ??????) >= maxDisValue ? (nowPrice * ??????) : maxDisValue )
                    double realDisCount = nowPrice - ((nowPrice * discount) >= maxDisValue ? (nowPrice * discount) : maxDisValue );
                    detail.setDiscountAmount(realDisCount);
                }*/

                remoteProduct_.setCouponAmount(detail.getCouponAmount());
                remoteProduct_.setPresentPrice(BigDecimal.valueOf(detail.getSalePrice()));
                detailList.add(remoteProduct_);

                //??????????????? ????????????????????????????????? (PresentPrice = PresentPrice - PromotionAmount - DiscountAmount - CostPrice)
                _product.setPresentPrice( _product.getPresentPrice() - remoteProduct_.getPromotionAmount() - remoteProduct_.getDiscountAmount() - remoteProduct_.getCouponAmount() );

                _product.setPresentPrice(detail.getSalePrice());
                orderProducts.add(_product);
                /** ???????????????????????? **/
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
        detail.setSpecs(sku);  //??????

        detail.setId(ToolUtil.getIdGenLong());
        detail.setOrderNo(orderNo);
        detail.setProductId(_product.getId());
        detail.setProductName(_product.getName());
        detail.setQuantity(_param.getQuantity());

        detail.setShipStatus(0);    //?????????,??????????????????
        detail.setRefundStatus(0);  //?????????,??????????????????
        //detail.setCostPrice(_product.getCost().doubleValue());
        //detail.setSalePrice(_product.getPresentPrice().doubleValue());
        detail.setCouponAmount(0d); //???????????????
        detail.setDiscountAmount(0d);   //????????????
        detail.setPromotionAmount(0d);  //????????????
        return detail;
    }

    /** ???????????? **/
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
                        //????????????????????????
                        useCoupons.add(couponsResult);
                    }
                }
            }
        }
        return useCoupons;
    }

    private GunsOrder execute(CreateOrderParam _param, List<CreateOrderParam.ShopCart.ProductItem> orderProducts, GunsOrder order, DateTime now) {

        double coupon = _param.getCouponAmount();  //?????????
        double discount = order.getDiscountAmount(); //??????
        double promotionAmount = order.getPromotionAmount();    //??????
        double freightAmount = order.getFreightAmount();//??????
        List<RemoteProductResult> detailList = packingDetailList(_param, orderProducts, order, promotionAmount);

        BigDecimal totalP = new BigDecimal(0);
        BigDecimal couponAmount = new BigDecimal(0);
        BigDecimal proAmount = new BigDecimal(0);
        BigDecimal disAmount = new BigDecimal(0);
        for(RemoteProductResult detail : detailList){
            //???????????????. totalP += detail.getPresentPrice() * detail.getQuantity()
            totalP = totalP.add( detail.getPresentPrice()
                            .multiply(
                                    BigDecimal.valueOf(detail.getQuantity()))
            );
            //???????????? =  ?????????????????????
            couponAmount = couponAmount.add( BigDecimal.valueOf(detail.getCouponAmount()));
            //??????
            //disAmount = disAmount.add(BigDecimal.valueOf(detail.getDiscountAmount()));
            //??????
            //proAmount = proAmount.add(BigDecimal.valueOf(detail.getPromotionAmount()));

        }
        // ?????? = ?????? - ????????? - ?????? - ?????? - ??????
        double realPayPrice = totalP
                .subtract(BigDecimal.valueOf(coupon))
                .subtract(BigDecimal.valueOf(discount))
                .subtract(BigDecimal.valueOf(promotionAmount))
                .subtract(BigDecimal.valueOf(freightAmount)) .doubleValue();
        //???????????? ?????? || ????????????
        if(totalP.doubleValue() != _param.getTotalPrice() || realPayPrice != _param.getRealPayPrice()){
            throw new ServiceException(500, "????????????????????????????????????????????????");    //??????????????????
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
        // TODO ?????????orderId???orderDetailId?????????????????????
        //boolean result = productService.cutStock(map);

        String routingKey = OrderRabbitConfig.STOCK;
        String exchangeName = OrderRabbitConfig.STOCK_EXCHANGE_NAME;
//        final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                System.err.println("correlationData: " + correlationData);
//                String messageId = correlationData.getId();
//                if (ack) {
//                    //??????confirm???????????? ???????????????
//                    log.info("-------------????????????confirm???????????? ?????????");
//                } else {
//                    //????????????????????????????????????:?????? ?????????????????????
//                    log.info("-------------????????????????????????...");
//                }
//            }
//        };
//        rabbitTemplate.setConfirmCallback(confirmCallback);
        //???????????????????????????,????????????????????????
//        final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback(){
//            @Override
//            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
//                log.error("?????? mq??????   queue ?????????,message:{},replyCode:{},replyText:{},exchange:{},routing:{}", message.toString(), i, s, s2, routingKey);
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
        gunsOrderLog.setUserType(2); //0.????????? 2.??????
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
