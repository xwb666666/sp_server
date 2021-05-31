package cn.stylefeng.guns.cloud.product.controller;

import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.model.api.param.AddEditCouponsParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponProductParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponsParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponsRecordParam;
import cn.stylefeng.guns.cloud.product.model.api.result.CouponProductResult;
import cn.stylefeng.guns.cloud.product.model.api.result.CouponsResult;
import cn.stylefeng.guns.cloud.product.service.GunsCouponsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/coupons")
@Api(tags = "后台优惠劵")
public class GunsCouponsController {

    @Resource
    private GunsCouponsService couponsService;

    @PostMapping("/search")
    @ApiOperation("优惠券列表")
    public ResponseData couponsPage(@RequestBody CouponsParam param) {
        if(param==null)
            throw new ServiceException(500, "请求参数不能为空！");
        PageResult<CouponsResult> result = couponsService.couponsSearch(param);
        return ResponseData.success(result);
    }

    @PostMapping("/info")
    @ApiOperation("优惠券详情")
    public ResponseData info(@RequestBody CouponsParam param){
        if(param==null)
            throw new ServiceException(500, "请求参数不能为空！");
        CouponsResult info = couponsService.info(param);
        return ResponseData.success(info);
    }

    @PostMapping("/add")
    @ApiOperation("优惠券新增")
    public ResponseData add(@RequestBody AddEditCouponsParam param){
        if(param==null)
            throw new ServiceException(500, "请求参数不能为空！");
        int add = couponsService.add(param);
        return ResponseData.success(add);
    }

    @PostMapping("/edit")
    @ApiOperation("优惠券编辑")
    public ResponseData edit(@RequestBody AddEditCouponsParam param){
        if(param==null)
            throw new ServiceException(500, "请求参数不能为空！");
        int add = couponsService.edit(param);
        return ResponseData.success(add);
    }

    /**---------------------------------会员操作-----------------------------------*/

//    @PostMapping("/memberPage")
//    @ApiOperation("会员优惠券列表")
//    public ResponseData memberCouponPage(@RequestBody CouponsParam param) {
//        if(param==null)
//            throw new ServiceException(500, "请求参数不能为空！");
//        PageResult<CouponsResult> result = couponsService.memberCouponPage(param);
//        return ResponseData.success(result);
//    }
//
//    /**
//     * @description 会员领劵
//     * @params
//     * @return
//     */
//    @PostMapping("/pick")
//    @ApiOperation("会员优惠券领取")
//    public ResponseData pick(@RequestBody CouponsRecordParam param){
//        if(param==null)
//            throw new ServiceException(500, "请求参数不能为空！");
//        int result = couponsService.pick(param);
//        return ResponseData.success(result);
//    }
//
//    @PostMapping("/record/list")
//    @ApiOperation("领取记录")
//    public ResponseData recordSearch(@RequestBody CouponsParam param) {
//        if(param==null)
//            throw new ServiceException(500, "请求参数不能为空！");
//        PageResult<CouponsRecordResult> detail = couponsService.recordSearch(param);
//        return ResponseData.success(detail);
//    }

    /**
     * @description
     * @params coupon_id 优惠券id
     * @return
     */
    @PostMapping("/products")
    @ApiOperation("优惠券关联商品列表")
    @ApiParam
    public ResponseData productPage(@RequestBody CouponProductParam param) {
        if(param==null)
            throw new ServiceException(500, "请求参数不能为空！");
        PageResult<CouponProductResult> result = couponsService.productPage(param);
        return ResponseData.success(result);
    }
}
