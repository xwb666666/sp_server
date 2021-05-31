package cn.stylefeng.guns.cloud.product.service.Impl;

import cn.stylefeng.guns.cloud.api.product.entity.GunsProProduct;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProSku;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.product.model.api.result.CartItemResult;
import cn.stylefeng.guns.cloud.product.model.api.param.AddEditCartParam;
import cn.stylefeng.guns.cloud.product.model.api.result.ShopCart;
import cn.stylefeng.guns.cloud.product.service.CartService;
import cn.stylefeng.guns.cloud.product.service.GunsProSkuService;
import cn.stylefeng.guns.cloud.product.service.GunsProductService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private GunsProductService gunsProductService;

    @Autowired
    private GunsProSkuService gunsProSkuService;



    @Override
    public CartItemResult addCart(AddEditCartParam cartParam) {
        CartItemResult item = new CartItemResult();
        Long key = cartParam.getMemberId();
        if (null == key)
            throw new ServiceException(500,"没有传入memberId！");
        BoundHashOperations ops = getOps(key);
        //redis查询小key是否购物车里面已存在
        String key2 = cartParam.getId() + "_" + (cartParam.getSkuId()==null?0:cartParam.getSkuId());
        Object value = ops.get(key2);
        if (value != null) {
            item = JSON.parseObject(String.valueOf(value), CartItemResult.class);
            item.setNumber(cartParam.getNumber() + item.getNumber());
        } else {
            item = addProductToRedis(cartParam, item);
        }
        ops.put(key2, JSON.toJSONString(item));
        return item;
    }

    @Override
    public CartItemResult editCart(AddEditCartParam cartParam) {
        CartItemResult item = new CartItemResult();
        Long key = cartParam.getMemberId();
        BoundHashOperations ops = getOps(key);
        //redis查询小key是否购物车里面已存在
        String key2 = cartParam.getId() + "_" + cartParam.getSkuId();
        Object zHash2 = ops.get(key2);
        if (zHash2 != null) {
            item = JSON.parseObject(String.valueOf(zHash2), CartItemResult.class);
            item.setNumber(cartParam.getNumber());
        }
        ops.put(key2, JSON.toJSONString(item));
        return item;
    }

    @Override
    public boolean delCarts(List<AddEditCartParam> list, Long memberId) {
        BoundHashOperations<String, Object, Object> ops = getOps(memberId);
        for(AddEditCartParam addEditCartParam : list) {
            String key2 = addEditCartParam.getId()+"_"+addEditCartParam.getSkuId();
            ops.delete(key2);
        }
        return true;
    }

    /**
     * 获取redis购物车信息,组装结构
     * @param param
     * @return
     */
    @Override
    public Map<String,Object> list(AddEditCartParam param, Long memberId) {
        Map<String,Object> result = new HashMap<>();
        BigDecimal totalPrice = new BigDecimal(0);

        BoundHashOperations ops = getOps(memberId);
        ArrayList<CartItemResult> cartItemResults = new ArrayList<CartItemResult>();

        List values = ops.values();
        for(Object str : values){
            CartItemResult item = JSON.parseObject(String.valueOf(str), CartItemResult.class);
            cartItemResults.add(item);
        }

        List<ShopCart> shopCarts = packageData(cartItemResults);
        for(ShopCart shopCart : shopCarts){
            totalPrice = totalPrice.add(BigDecimal.valueOf(shopCart.getTotalPresentPrice()));
        }
        result.put("cartList",shopCarts);
        result.put("totalPrice",totalPrice);
        return result;
    }

    /**
     * @description 购物车商品勾选
     * @params
     * @return
     */
    @Override
    public Map<String,Object> check(List<AddEditCartParam> list,Long memberId) {
        if (null == memberId)
            throw new ServiceException(500, "没有传入memberId！");
        return getList(list, memberId);
    }



    /***************************************************************************************/



    private CartItemResult addProductToRedis(AddEditCartParam cartParam, CartItemResult item) {
        //不存在则新增
        item.setCheck(true);
        item.setProductId(cartParam.getId());
        item.setNumber(cartParam.getNumber());

        GunsProProduct product = gunsProductService.getById(cartParam.getId());
        if(product==null)
            throw new ServiceException(500,"商品不存在！");

        //启用规格为1
        if (product.getIsSku()==1) {
            QueryWrapper<GunsProSku> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", cartParam.getSkuId()).eq("product_id", cartParam.getId());
            GunsProSku sku = gunsProSkuService.getOne(queryWrapper);
            if(sku==null)
                throw new ServiceException(500,"sku信息错误！");


            List<String> skus = JSON.parseArray(sku.getSkuNames(), String.class);
            item.setSkuInfo(skus);
            item.setOriginalPrice(sku.getOriginalPrice());
            item.setPresentPrice(sku.getPresentPrice());
            item.setCost(sku.getCost());
        } else {
            if(product.getStatus() != 2)
                throw new ServiceException(500, "商品下架，无法购买！");

            item.setSkuInfo(null);
            item.setOriginalPrice(product.getOriginalPrice());
            item.setPresentPrice(product.getPresentPrice());
            item.setCost(product.getCost()!=null ? product.getCost().doubleValue():0.0);
        }
        item.setProductName(product.getName());
        item.setShopId(product.getShopId());
        item.setShopName("自营店");
        item.setPic(product.getPictures());
        item.setSkuId(cartParam.getSkuId());
        return item;
    }

    private Map<String,Object> getList(List<AddEditCartParam> list,Long memberId) {
        Map<String,Object> result = new HashMap<>();
        //拿勾选商品，与redis内信息对比，修改，commit
        BoundHashOperations ops = getOps(memberId);
        List values = ops.values();
        //遍历values获取所有的productList的check状态
        for(Object bigKey : values){
            //循环构建 CartItemResult
            CartItemResult item = JSON.parseObject(String.valueOf(bigKey), CartItemResult.class);
            String key2 = item.getProductId() + "_" + item.getSkuId();
            Object zHash2 = ops.get(key2);
            if (zHash2 == null)
                continue;
            item = JSON.parseObject(String.valueOf(zHash2), CartItemResult.class);
            for(AddEditCartParam aecp : list){
                String checkKey = aecp.getId()+"_"+aecp.getSkuId();
                if(key2.equals(checkKey)){
                    //更新key2对应的valueMap里面的check
                    item.setCheck(aecp.isCheck());
                }
            }
            ops.put(key2, JSON.toJSONString(item));
        }
        result = this.list(null,memberId);
        return result;
    }

    /**
     * @description 组装返回数据格式
     * @params
     * @return
     */
    private List<ShopCart> packageData(List<CartItemResult> cartItemResults) {
        List<ShopCart> shopCarts = new ArrayList<>();
        Map<Object,List<CartItemResult>> collect = cartItemResults.stream().collect(Collectors.groupingBy(CartItemResult::getShopId));
        for(Map.Entry<Object, List<CartItemResult>> shipKey : collect.entrySet()){
            //根据obj分组内的店铺信息获取数据
            List<ShopCart.CartItem> cartItems = new ArrayList<>();
            ShopCart shopCart = new ShopCart();
            shopCart.setShopId((long)shipKey.getKey());
            shopCart.setShopName(shipKey.getValue().get(0).getShopName());

            BigDecimal totalOresentPrice = new BigDecimal(0);
            BigDecimal totalPriginalPrice = new BigDecimal(0);
            BigDecimal totalCostPrice = new BigDecimal(0);
            //将redis的result重组ShopCart.CartItem, 并计算
            for(CartItemResult cir : shipKey.getValue()) {
                ShopCart.CartItem cartItem = new ShopCart.CartItem();
                cartItem.setProductId(cir.getProductId());
                cartItem.setProductName(cir.getProductName());
                cartItem.setCheck(cir.isCheck());
                cartItem.setNumber(cir.getNumber());
                cartItem.setCost(cir.getCost());
                cartItem.setOriginalPrice(cir.getOriginalPrice());
                cartItem.setPresentPrice(cir.getPresentPrice());
                cartItem.setPic(cir.getPic());
                cartItem.setSkuId(cir.getSkuId());
                cartItem.setSkuInfo(listToString(cir.getSkuInfo(),','));
                cartItems.add(cartItem);
                totalOresentPrice = totalOresentPrice.add(BigDecimal.valueOf(cir.getPresentPrice()).multiply(BigDecimal.valueOf(cir.getNumber())));
                totalPriginalPrice = totalPriginalPrice.add(BigDecimal.valueOf(cir.getOriginalPrice()).multiply(BigDecimal.valueOf(cir.getNumber())));
                totalCostPrice = totalCostPrice.add(BigDecimal.valueOf(cir.getCost()).multiply(BigDecimal.valueOf(cir.getNumber())));
            }
            shopCart.setTotalPresentPrice(totalOresentPrice.doubleValue());
            shopCart.setTotalOriginalPrice(totalPriginalPrice.doubleValue());
            shopCart.setTotalCost(totalCostPrice.doubleValue());
            shopCart.setList(cartItems);
            shopCarts.add(shopCart);
        }
        return shopCarts;
    }

    private  String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return list.isEmpty() ? "" : sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * @description 购物车redis大key封装
     * @params
     * @return
     */
    private BoundHashOperations<String,Object, Object> getOps(Long memberId) {
        String prefix="cart:";
        BoundHashOperations<String,Object,Object> operations = redisTemplate.boundHashOps(prefix+memberId.toString());
        return operations;
    }

}
