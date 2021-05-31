package cn.stylefeng.guns.cloud.product.service;

import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.model.api.GunsCoupons;
import cn.stylefeng.guns.cloud.product.model.api.param.AddEditCouponsParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponProductParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponsParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponsRecordParam;
import cn.stylefeng.guns.cloud.product.model.api.result.CouponProductResult;
import cn.stylefeng.guns.cloud.product.model.api.result.CouponsRecordResult;
import cn.stylefeng.guns.cloud.product.model.api.result.CouponsResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

public interface GunsCouponsService extends IService<GunsCoupons>{

    public PageResult<CouponsResult> couponsSearch(CouponsParam param);

    public CouponsResult info(CouponsParam param);

    public int add(AddEditCouponsParam param);

    public int edit(AddEditCouponsParam param);

    public PageResult<CouponProductResult> productPage(CouponProductParam param);

    public int deleteProduct(CouponProductParam param);
    public int deleteProductBatch(CouponProductParam param);

    /**---------------------------------会员我的劵-----------------------------------*/

    public PageResult<CouponsResult> couponRecordPage(CouponsRecordParam param);

    public int count(CouponsRecordParam param);


    public PageResult<CouponsResult> getUseableCoupons(CouponsRecordParam param);

    public int pick(CouponsRecordParam param);

    /**---------------------------------领劵中心-----------------------------------*/

    public PageResult<CouponsResult> centerList(CouponsRecordParam param);
}
