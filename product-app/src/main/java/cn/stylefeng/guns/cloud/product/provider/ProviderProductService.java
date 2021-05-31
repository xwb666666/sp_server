package cn.stylefeng.guns.cloud.product.provider;

import cn.stylefeng.guns.cloud.api.member.entity.GunsProBrowse;
import cn.stylefeng.guns.cloud.api.member.entity.GunsProCollect;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProProduct;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProSku;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProProductParam;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProQueryParam;
import cn.stylefeng.guns.cloud.api.product.model.params.RomteProductSkuParam;
import cn.stylefeng.guns.cloud.api.product.model.result.*;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.model.api.param.CollectParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponsRecordParam;
import cn.stylefeng.guns.cloud.product.model.api.result.CouponsResult;
import cn.stylefeng.guns.cloud.product.service.*;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/remote/product")
@ApiOperation("远程服务商品")
public class ProviderProductService {

    @Autowired
    private GunsProductService productService;
    @Autowired
    private GunsProSkuService proSkuService;
    @Autowired
    private GunsCouponsService couponsService;

    @Autowired
    private CollectService collectService;
    @Autowired
    private BrowseService browseService;

    @PostMapping("/detail")
    @ApiOperation("获取商品详情")
    public RemoteProductResult detail(@Validated @RequestBody Map<String,Long> map){
        Long productId = (Long) map.get("productId");
        Long skuId = (Long) map.get("skuId");
        RemoteProductResult result = new RemoteProductResult();

        GunsProProduct product = productService.getOne(new QueryWrapper<GunsProProduct>().eq("id", productId));
        ToolUtil.copyProperties(product, result);
        List<GunsProSku> skuList = proSkuService.list(new QueryWrapper<GunsProSku>().eq("product_id", product.getId()));
        List<RemoteSkuResult> skuResultList = new ArrayList<>();
        if (skuList != null && skuList.size() > 0) {
            skuList.forEach(item -> {
                RemoteSkuResult skuResult = new RemoteSkuResult();
                ToolUtil.copyProperties(item, skuResult);
                skuResultList.add(skuResult);

            });
        }
        result.setSkuInfo(skuResultList);
        return result;
    }

    @PostMapping("/stock")
    @ApiOperation("查找id，扣减库存")
    public boolean cutStock(@RequestParam Map<String,Double> map) {
        boolean o = productService.editProductStock(map);
        return o;
    }

//    @PostMapping("/skuProducts")
//    public List<RemoteProductResult> getRealProducts(List<RomteProductSkuParam> list){
//        List<RemoteProductResult> realProducts = productService.getRealProducts(list);
//        return realProducts;
//    }


    @GetMapping("selectCollect")
    @ApiOperation("获取收藏商品")
    public Integer selectCollect(@RequestParam Long memberId){
        QueryWrapper<GunsProCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",memberId);
        int count = collectService.count(queryWrapper);
        return count;
    }

    @GetMapping("selectBrowse")
    @ApiOperation("获取浏览记录")
    public Integer selectBrowse(@RequestParam Long memberId){
        QueryWrapper<GunsProBrowse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",memberId);
        int count = browseService.count(queryWrapper);
        return count;
    }



    @PostMapping("/coupon/records")
    @ApiOperation("会员-我的优惠券列表")
    public ResponseData couponRecordPage(@RequestBody CouponsRecordParam param) {
        if(param==null)
            throw new ServiceException(500, "请求参数不能为空！");
        PageResult<CouponsResult> result = couponsService.couponRecordPage(param);
        return ResponseData.success(result);
    }


}