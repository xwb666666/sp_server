package cn.stylefeng.guns.cloud.product.controller.api;

import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.model.api.param.AddEditCartParam;
import cn.stylefeng.guns.cloud.product.model.api.result.CartItemResult;
import cn.stylefeng.guns.cloud.product.model.api.result.ShopCart;
import cn.stylefeng.guns.cloud.product.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/cart")
@Api(tags = "购物车")
public class ApiCartController {

    @Resource
    private CartService cartService;

    @PostMapping("/add")
    @ApiOperation(value = "添加", notes = "必传参数{id:1, skuId:1, memberId:1, number:1}")
    public ResponseData add(@Validated @RequestBody AddEditCartParam param) {
        return ResponseData.success(cartService.addCart(param));
    }

    @PostMapping("/edit")
    @ApiOperation(value = "编辑", notes = "必传参数{id:1, skuId:1, shopId:1, memberId:1, number:1}")
    public ResponseData edit(@Validated @RequestBody AddEditCartParam param) {
        return ResponseData.success(cartService.editCart(param));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "必传参数memberId，[{id:1, skuId:1, shopId:1}]")
    public ResponseData delete(@Validated @RequestBody List<AddEditCartParam> list) {
        Long memberId = 0L;
        try {
            memberId = list.get(0).getMemberId();//配合前端，减少调试
        } catch (Exception e){
            throw new ServiceException(500, "参数格式不正确！缺少member信息。");
        }
        return ResponseData.success(cartService.delCarts(list, memberId));
    }

    /**
     * 购物车列表，分页待做
     * @param param
     * @return
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表查询", notes = "必传参数memberId")
    public ResponseData list(@Validated @RequestBody AddEditCartParam param) {
        Long memberId = param.getMemberId();
        Map<String,Object> list = cartService.list(param, memberId);
        return ResponseData.success(list);
    }

    @PostMapping("/check")
    @ApiOperation(value = "勾选", notes = "必传参数memberId,[{id:1, check:1, shopId:1}]")
    public ResponseData check(@Validated @RequestBody List<AddEditCartParam> list) {
        Map<String,Object> result=new HashMap<>();
        Long memberId = 0l;
        if(list !=null && list.get(0) != null){
            memberId = list.get(0).getMemberId();
        }
        result = cartService.check(list, memberId);
        return ResponseData.success(result);
    }
}