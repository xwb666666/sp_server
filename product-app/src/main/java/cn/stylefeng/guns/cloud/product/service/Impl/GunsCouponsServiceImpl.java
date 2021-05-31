package cn.stylefeng.guns.cloud.product.service.Impl;

import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.product.constants.Constant;
import cn.stylefeng.guns.cloud.product.mapper.GunsCouponsMapper;
import cn.stylefeng.guns.cloud.product.model.api.GunsCouponProduct;
import cn.stylefeng.guns.cloud.product.model.api.GunsCoupons;
import cn.stylefeng.guns.cloud.product.model.api.GunsCouponsRecord;
import cn.stylefeng.guns.cloud.product.model.api.param.AddEditCouponsParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponProductParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponsParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CouponsRecordParam;
import cn.stylefeng.guns.cloud.product.model.api.result.CouponProductResult;
import cn.stylefeng.guns.cloud.product.model.api.result.CouponsResult;
import cn.stylefeng.guns.cloud.product.service.GunsCouponProductService;
import cn.stylefeng.guns.cloud.product.service.GunsCouponsRecordService;
import cn.stylefeng.guns.cloud.product.service.GunsCouponsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GunsCouponsServiceImpl extends ServiceImpl<GunsCouponsMapper, GunsCoupons> implements GunsCouponsService {

    @Resource
    private GunsCouponProductService couponProductService;

    @Resource
    private GunsCouponsRecordService couponsRecordService;

    @Override
    public PageResult<CouponsResult> couponsSearch(CouponsParam param) {
        if (param.getPage() == null || param.getPage() == 0)
            param.setPage(1);
        if (param.getPageSize() == null || param.getPageSize() == 0)
            param.setPageSize(10);

        IPage<GunsCoupons> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        QueryWrapper<GunsCoupons> queryWrapper = new QueryWrapper<>();

        if (param.getName() != null && param.getName() != "")
            queryWrapper.like("name", param.getName().trim());
        if (param.getType() != null)
            queryWrapper.eq("type", param.getType());
        if (param.getStatus() != null)
            queryWrapper.eq("status", param.getStatus());
        if (param.getExpireType() != null)
            queryWrapper.eq("expire_type", param.getExpireType());

        page = baseMapper.selectPage(page, queryWrapper);
        PageResult<CouponsResult> result = new PageResult<>();

        result.setPage(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotalPage(page.getPages());
        result.setTotalRows(page.getTotal());
        List<GunsCoupons> rows = page.getRecords();
        List<CouponsResult> rows_result = new ArrayList<>();
        rows.forEach(item -> {
            CouponsResult couponsResult = new CouponsResult();
            ToolUtil.copyProperties(item, couponsResult);
            rows_result.add(couponsResult);
        });
        result.setRows(rows_result);
        return result;
    }

    @Override
    public CouponsResult info(CouponsParam param) {
        QueryWrapper<GunsCoupons> queryWrapper = new QueryWrapper<>();
        GunsCoupons gunsCoupon = baseMapper.selectOne(queryWrapper);
        CouponsResult result = new CouponsResult();
        ArrayList<CouponProductResult> couponProductResults = new ArrayList<>();
        List<GunsCouponProduct> list = couponProductService.getBaseMapper().selectList(new QueryWrapper<GunsCouponProduct>().eq("coupon_id",gunsCoupon.getId()));
        list.forEach(item -> {
            CouponProductResult couponProductResult = new CouponProductResult();
            ToolUtil.copyProperties(item, couponProductResult);
            couponProductResults.add(couponProductResult);
        });
        ToolUtil.copyProperties(gunsCoupon, result);
        return result;
    }

    @Override
    @Transactional
    public int add(AddEditCouponsParam param) {
        if(param == null)
            throw new ServiceException(500, "请求参数不能为空！");
        GunsCoupons gunsCoupons = new GunsCoupons();
        ToolUtil.copyProperties(param, gunsCoupons);
        SimpleDateFormat df = new SimpleDateFormat(Constant.DATE_TIME);
        Date date = new Date();
        String today = df.format(date);
        try{
            gunsCoupons.setCreateTime(df.parse(today));
        } catch (ParseException e){
            e.printStackTrace();
        }
        gunsCoupons.setId(ToolUtil.getIdGenLong());
        if(param.getList() != null){
            for(CouponProductResult product : param.getList()){
                GunsCouponProduct gunsCouponProduct = new GunsCouponProduct();
                ToolUtil.copyProperties(product, gunsCouponProduct);
                gunsCouponProduct.setCouponId(gunsCoupons.getId());
                gunsCouponProduct.setId(ToolUtil.getIdGenLong());
                couponProductService.getBaseMapper().insert(gunsCouponProduct);
            }
        }
        int result = baseMapper.insert(gunsCoupons);
        return result;
    }

    @Override
    @Transactional
    public int edit(AddEditCouponsParam param) {
        if(param == null)
            throw new ServiceException(500, "请求参数不能为空！");
        GunsCoupons gunsCoupons = baseMapper.selectById(param.getId());
        if(gunsCoupons.getStatus() != 1)
            throw new ServiceException(500, "优惠券已关闭!");

        //修改优惠券关联产品，先删除再增加
        ToolUtil.copyProperties(gunsCoupons, param);
        List<GunsCouponProduct> list = couponProductService.getBaseMapper().selectList(new QueryWrapper<GunsCouponProduct>().eq("coupon_id", gunsCoupons.getId()));
        list.forEach(item ->{
            couponProductService.getBaseMapper().deleteById(item.getId());
        });
        if(param.getList() != null){
            List<CouponProductResult> checkProductList = param.getList();
            for(CouponProductResult product : checkProductList){
                GunsCouponProduct gunsCouponProduct = new GunsCouponProduct();
                ToolUtil.copyProperties(product, gunsCouponProduct);
                gunsCouponProduct.setCouponId(gunsCoupons.getId());
                gunsCouponProduct.setId(ToolUtil.getIdGenLong());
                couponProductService.getBaseMapper().insert(gunsCouponProduct);
            }
        }
        int result = baseMapper.update(gunsCoupons, new QueryWrapper<GunsCoupons>().eq("id",param.getId()));
        return result;
    }

    //查询优惠券与商品关联列表
    @Override
    public PageResult<CouponProductResult> productPage(CouponProductParam param) {
        if (param.getPage() == null || param.getPage() == 0)
            param.setPage(1);
        if (param.getPageSize() == null || param.getPageSize() == 0)
            param.setPageSize(10);

        IPage<GunsCoupons> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        QueryWrapper<GunsCoupons> queryWrapper = new QueryWrapper<>();

        if (param.getCouponId() != null ){
            queryWrapper.eq("coupon_id", param.getCouponId());
        }

        page = baseMapper.selectPage(page, queryWrapper);
        PageResult<CouponProductResult> result = new PageResult<>();

        result.setPage(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotalPage(page.getPages());
        result.setTotalRows(page.getTotal());
        List<GunsCoupons> rows = page.getRecords();
        List<CouponProductResult> rows_result = new ArrayList<>();
        rows.forEach(item -> {
            CouponProductResult couponsResult = new CouponProductResult();
            ToolUtil.copyProperties(item, couponsResult);
            rows_result.add(couponsResult);
        });
        result.setRows(rows_result);
        return result;
    }
    //删除优惠券与商品关联
    @Override
    @Transactional
    public int deleteProduct(CouponProductParam param) {
        if(param == null || param.getProductId() == null || param.getCouponId() == null)
            throw new ServiceException(500, "请求参数不能为空！");
        int result = couponProductService.getBaseMapper().delete(new QueryWrapper<GunsCouponProduct>()
                .eq("coupon_id", param.getCouponId())
                .eq("product_id", param.getProductId()));
        return result;
    }
    @Override
    @Transactional
    public int deleteProductBatch(CouponProductParam param) {
        if(param == null || param.getCouponId() == null)
            throw new ServiceException(500, "请求参数不能为空！");
        Long couponId = param.getCouponId();
        List<Long> productIds = param.getProductIds();
        int result = 0;
        productIds.forEach(item ->{
            couponProductService.getBaseMapper().delete(new QueryWrapper<GunsCouponProduct>()
                    .eq("coupon_id", couponId)
                    .eq("product_id", item));
        });
        return result;
    }

    /**---------------------------------会员操作-----------------------------------*/

    /**
     * @description 根据memberId查询可以获取的优惠券，并且是有库存的
     * @params memberId, status
     * @return
     */
    @Override
    public PageResult<CouponsResult> couponRecordPage(CouponsRecordParam param) {
        if (param.getPage() == null || param.getPage() == 0)
            param.setPage(1);
        if (param.getPageSize() == null || param.getPageSize() == 0)
            param.setPageSize(10);

        IPage<CouponsResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());

        Integer recordStatus = param.getStatus();
        Long memberId = param.getMemberId();
        if(recordStatus == Constant.COUPON_RECORD_STATUS_PICK){
            //领取，通过状态与过期时间判断
            List<CouponsResult> couponsResults = baseMapper.notUseListByMember(memberId);
            for(CouponsResult couponsResult : couponsResults){
                List<GunsCouponProduct> productList = couponProductService.getBaseMapper().selectList(new QueryWrapper<GunsCouponProduct>().eq("coupon_id", couponsResult.getId()));
                List<CouponProductResult> couponProductResults = new ArrayList<CouponProductResult>();
                productList.forEach(item -> {
                    CouponProductResult temp = new CouponProductResult();
                    ToolUtil.copyProperties(item, temp);
                    couponProductResults.add(temp);
                });
                couponsResult.setList(couponProductResults);
            }
            page.setRecords(couponsResults);
        }
        if(recordStatus == Constant.COUPON_RECORD_STATUS_USE){
            //使用，通过状态与过期时间判断
            page.setRecords(baseMapper.useListByMember(memberId));
        }
        if(recordStatus == Constant.COUPON_RECORD_STATUS_EXP){
            //过期，通过过期时间判断
            page.setRecords(baseMapper.expireListByMember(memberId));
        }

        PageResult<CouponsResult> result = new PageResult<>();

        result.setPage(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotalPage(page.getPages());
        result.setTotalRows(page.getTotal());
        result.setRows(page.getRecords());
        return result;
    }

    @Override
    public int count(CouponsRecordParam param) {
        Long memberId = param.getMemberId();
            //通过状态与过期时间判断
        int size1 = baseMapper.notUseListByMember(memberId).size();
        //通过状态与过期时间判断
        int size2 = baseMapper.useListByMember(memberId).size();
            //通过过期时间判断
        int size3 = baseMapper.expireListByMember(memberId).size();
        return size1+size2+size3;
    }

    @Override
    public PageResult<CouponsResult> getUseableCoupons(CouponsRecordParam param) {
        if (param.getPage() == null || param.getPage() == 0)
            param.setPage(1);
        if (param.getPageSize() == null || param.getPageSize() == 0)
            param.setPageSize(10);

        IPage<CouponsResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());

        Integer recordStatus = param.getStatus();
        Long memberId = param.getMemberId();
        if(recordStatus == 1){
            //通过状态与过期时间判断
            page.setRecords(baseMapper.notUseListByMember(memberId));
        }
        if(recordStatus == 2){
            //通过状态与过期时间判断
            page.setRecords(baseMapper.useListByMember(memberId));
        }
        if(recordStatus == 3){
            //通过过期时间判断
            page.setRecords(baseMapper.expireListByMember(memberId));
        }

        PageResult<CouponsResult> result = new PageResult<>();

        result.setPage(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotalPage(page.getPages());
        result.setTotalRows(page.getTotal());
        result.setRows(page.getRecords());
        return result;
    }

    /**
     * @description 会员领取优惠劵
     * @params memberId,couponId
     * @return
     */
    @Override
    @Transactional
    public synchronized int pick(CouponsRecordParam param) {
        GunsCoupons gunsCoupons = baseMapper.selectById(param.getCouponId());
        if(gunsCoupons == null)
            throw new ServiceException(500, "查询优惠券出现异常！");
        if(gunsCoupons.getTotalCount() == 0)
            throw new ServiceException(500, "优惠券库存不足!");
        if(gunsCoupons.getStatus() != 1)
            throw new ServiceException(500, "优惠券已关闭!");
        if(gunsCoupons.getIsCanPick() != 1)
            throw new ServiceException(500, "优惠券无法直接领取!");

        GunsCouponsRecord gunsCouponsRecord = new GunsCouponsRecord();
        gunsCouponsRecord.setCouponId(gunsCoupons.getId());
        gunsCouponsRecord.setId(ToolUtil.getIdGenLong());
        gunsCouponsRecord.setAmount(gunsCoupons.getAmount());
        gunsCouponsRecord.setCode(ToolUtil.getIdGen());
        gunsCouponsRecord.setPickType(gunsCoupons.getIsCanPick());
        gunsCouponsRecord.setMemberId(param.getMemberId());
        gunsCouponsRecord.setStatus(Constant.COUPON_RECORD_IS_PICK);
        SimpleDateFormat df = new SimpleDateFormat(Constant.DATE_TIME);
        Date date = new Date();
        String today = df.format(date);
        try {
            gunsCouponsRecord.setPickTime(df.parse(today));
        } catch (ParseException e){
            throw new ServiceException(500, "系统异常！获取时间错误！");
        }
        //根据优惠券过期类型，计算过期日期
        if(gunsCoupons.getExpireType()==1){
            //固定日期
            gunsCouponsRecord.setStartTime(gunsCoupons.getCreateTime());
            gunsCouponsRecord.setExpireTime(gunsCoupons.getFixedDate());
        }
        if(gunsCoupons.getExpireType()==2){
            //领取后有效天数
            String expireTime = df.format(new Date(date.getTime() + (gunsCoupons.getExpireDays() * 24 * 60 * 60 * 1000)));
            try {
                gunsCouponsRecord.setStartTime(gunsCouponsRecord.getPickTime());
                gunsCouponsRecord.setExpireTime(df.parse(expireTime));
            } catch (ParseException e){
                throw new ServiceException(500, "系统异常！获取时间错误！");
            }
        }

        List<GunsCouponsRecord> records = couponsRecordService.getBaseMapper().selectList(new QueryWrapper<GunsCouponsRecord>()
                .eq("coupon_id", param.getCouponId())
                .eq("member_id", param.getMemberId()));
        int totalCount = gunsCoupons.getTotalCount()-1;
        int result = 0;
        //查询该用户领取该劵数量是否超出最大可领取数
        if(records != null && records.size() < gunsCoupons.getMaxPickCount()){
            result = couponsRecordService.getBaseMapper().insert(gunsCouponsRecord);
            gunsCoupons.setTotalCount(totalCount);
            baseMapper.update(null, new UpdateWrapper<GunsCoupons>()
                    .eq("id", gunsCoupons.getId())
                    .set("pick_count",gunsCoupons.getPickCount()-1)
                    .set("total_count",gunsCoupons.getTotalCount()));
        }
        return result;
    }

    /**-----------------------------领劵中心------------------------------*/

    /**
     * @description 会员可领的优惠劵(查询所有状态为正常，库存足够，可直接领取，过期类型为固定日期的劵固定时间>NOW()的劵)
     * @params memberId
     * @return
     */
    @Override
    public PageResult<CouponsResult> centerList(CouponsRecordParam param) {
        if (param.getPage() == null || param.getPage() == 0)
            param.setPage(1);
        if (param.getPageSize() == null || param.getPageSize() == 0)
            param.setPageSize(10);

        IPage<CouponsResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());

        Long memberId = param.getMemberId();
        if(memberId == null)
            throw new ServiceException(500, "会员信息缺失！");
        //获取
        page.setRecords(baseMapper.centerList(memberId));

        PageResult<CouponsResult> result = new PageResult<>();

        result.setPage(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotalPage(page.getPages());
        result.setTotalRows(page.getTotal());
        result.setRows(page.getRecords());
        return result;
    }


}