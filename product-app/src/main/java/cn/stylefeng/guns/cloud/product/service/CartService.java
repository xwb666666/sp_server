package cn.stylefeng.guns.cloud.product.service;
import cn.stylefeng.guns.cloud.product.model.api.result.CartItemResult;
import cn.stylefeng.guns.cloud.product.model.api.param.AddEditCartParam;
import cn.stylefeng.guns.cloud.product.model.api.result.ShopCart;

import java.util.List;
import java.util.Map;


public interface CartService {

//    Page<Map<String,Object>> selectList(BrowseParam param);

    CartItemResult addCart(AddEditCartParam cartParam);

    CartItemResult editCart(AddEditCartParam cartParam);

    boolean delCarts(List<AddEditCartParam> list, Long memberId);

    Map<String,Object> list(AddEditCartParam param, Long memberId);

    Map<String,Object> check(List<AddEditCartParam> list,Long memberId) ;

}
