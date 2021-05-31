package cn.stylefeng.guns.cloud.order.consumer;

import cn.stylefeng.guns.cloud.api.product.model.params.CouponsRecordParam;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProProductParam;
import cn.stylefeng.guns.cloud.api.product.model.params.RomteProductSkuParam;
import cn.stylefeng.guns.cloud.api.product.model.result.RemoteProductResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("guns-cloud-product")
@RequestMapping("/api/remote/product")
public interface RemoteProductService {

    /**
     * @description 实时调用商品信息
     * @params
     * @return
     */
    @PostMapping("/detail")
    RemoteProductResult productDetail(@RequestBody Map<String,Long> map);

    @PostMapping("/list")
    public List<RemoteProductResult> getRealProducts(List<RomteProductSkuParam> list);

    @PostMapping("/stock")
    public boolean cutStock(@RequestParam Map<String,Double> map);

    /**
     * @description 预扣减商品库存
     * @params
     * @return
     */
    @PostMapping("/update")
    ResponseData update(@RequestBody GunsProProductParam param);

    /**
     * @description 预扣减，属于商品规格的商品.   暂使用已有接口，必重写
     * @params
     * @return
     */
    @PostMapping("/skuUpdate")
    ResponseData skuUpdate(@RequestBody GunsProProductParam param);



    @PostMapping("/coupon/records")
    @ApiOperation("会员-我的优惠券列表")
    public ResponseData couponRecordPage(@RequestBody CouponsRecordParam param);
}
