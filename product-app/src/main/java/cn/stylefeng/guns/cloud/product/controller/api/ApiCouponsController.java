package cn.stylefeng.guns.cloud.product.controller.api;

import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.model.api.param.AddEditCouponsParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponProductParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponsParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponsRecordParam;
import cn.stylefeng.guns.cloud.product.model.api.result.CouponsRecordResult;
import cn.stylefeng.guns.cloud.product.model.api.result.CouponsResult;
import cn.stylefeng.guns.cloud.product.service.GunsCouponsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/coupons")
@Api(tags = "api-会员优惠劵")
public class ApiCouponsController {

    @Resource
    private GunsCouponsService couponsService;

    //我的优惠券
    @PostMapping("/records")
    @ApiOperation("会员-我的优惠券列表")
    public ResponseData couponRecordPage(@RequestBody CouponsRecordParam param) {
        if(param==null)
            throw new ServiceException(500, "请求参数不能为空！");
        PageResult<CouponsResult> result = couponsService.couponRecordPage(param);
        return ResponseData.success(result);
    }

    @PostMapping("/count")
    @ApiOperation("会员-我的优惠券数量")
    public Integer count(@RequestBody CouponsRecordParam param) {
        if(param==null)
            throw new ServiceException(500, "请求参数不能为空！");
        int result = couponsService.count(param);
        return result;
    }

    /**-----------------------------领劵中心------------------------------*/
    @PostMapping("/records/member/usable")
    @ApiOperation("会员-领劵中心-可领优惠券列表")
    public ResponseData getUseableCoupons(@RequestBody CouponsRecordParam param) {
        if(param==null)
            throw new ServiceException(500, "请求参数不能为空！");
        PageResult<CouponsResult> result = couponsService.getUseableCoupons(param);
        return ResponseData.success(result);
    }

    /**
     * @description 会员领劵
     * @params
     * @return
     */
    @PostMapping("/pick")
    @ApiOperation("会员-领取优惠券")
    public ResponseData pick(@RequestBody CouponsRecordParam param){
        if(param==null)
            throw new ServiceException(500, "请求参数不能为空！");
        int result = couponsService.pick(param);
        return ResponseData.success(result);
    }

    @PostMapping("/center/")
    @ApiOperation("领劵中心")
    public ResponseData centerList(@RequestBody CouponsRecordParam param){
        if(param==null)
            throw new ServiceException(500, "请求参数不能为空！");
        PageResult<CouponsResult> result = couponsService.centerList(param);
        return ResponseData.success(result);
    }



}
