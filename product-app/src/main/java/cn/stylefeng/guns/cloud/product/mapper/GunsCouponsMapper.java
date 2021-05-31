package cn.stylefeng.guns.cloud.product.mapper;

import cn.stylefeng.guns.cloud.product.model.api.GunsCoupons;
import cn.stylefeng.guns.cloud.product.model.api.result.CouponsResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface GunsCouponsMapper extends BaseMapper<GunsCoupons> {

    //List<CouponsResult> notUse(Page<CouponsResult> page, Long memberId);

    @Select("select a.id, a.name, a.type, a.pic, b.amount, a.discount, a.total_count totalCount, a.pick_count pickCount, a.max_pick_count maxPickCount, a.valid_amount validAmount, " +
            "a.is_can_pick isCanPick, a.expire_type expireType, a.fixed_date fixedDate, a.expire_days expireDays, a.product_type productType, a.memo, a.create_time createTime, a.status, " +
            "b.code, b.pick_type pickType, b.pick_time pickTime, b.start_time startTime, b.expire_time expireTime, b.use_time useTime, b.status recordStatus" +
            " from guns_coupons a, guns_coupons_record b where a.id = b.coupon_id and b.member_id = #{member} and b.`status` < 2 and NOW() < b.expire_time")
    List<CouponsResult> notUseListByMember(@Param("member") Long member);

    @Select("select a.id, a.name, a.type, a.pic, b.amount, a.discount, a.total_count totalCount, a.pick_count pickCount, a.max_pick_count maxPickCount, a.valid_amount validAmount, " +
            "a.is_can_pick isCanPick, a.expire_type expireType, a.fixed_date fixedDate, a.expire_days expireDays, a.product_type productType, a.memo, a.create_time createTime, a.status, " +
            "b.code, b.pick_type pickType, b.pick_time pickTime, b.start_time startTime, b.expire_time expireTime, b.use_time useTime, b.status recordStatus" +
            " from guns_coupons a, guns_coupons_record b where a.id = b.coupon_id and b.member_id = #{member} and b.`status` = 2 and NOW() < b.expire_time")
    List<CouponsResult> useListByMember(@Param("member") Long member);

    @Select("select a.id, a.name, a.type, a.pic, b.amount, a.discount, a.total_count totalCount, a.pick_count pickCount, a.max_pick_count maxPickCount, a.valid_amount validAmount, " +
            "a.is_can_pick isCanPick, a.expire_type expireType, a.fixed_date fixedDate, a.expire_days expireDays, a.product_type productType, a.memo, a.create_time createTime, a.status, " +
            "b.code, b.pick_type pickType, b.pick_time pickTime, b.start_time startTime, b.expire_time expireTime, b.use_time useTime, b.status recordStatus" +
            " from guns_coupons a, guns_coupons_record b where a.id = b.coupon_id and b.member_id = #{member} and NOW() > b.expire_time ")
    List<CouponsResult> expireListByMember(@Param("member") Long member);


    /**-----------------------------领劵中心----------------------------------*/

    @Select("select m.number, m.id, m.name, m.type, m.pic, m.amount, m.discount, m.total_count totalCount, m.pick_count pickCount, m.max_pick_count maxPickCount, " +
                "m.valid_amount validAmount, m.is_can_pick isCanPick, m.expire_type expireType, m.fixed_date fixedDate, m.expire_days expireDays, m.product_type productType, " +
                "m.memo, m.create_time createTime, m.status " +
            "from (SELECT IFNULL( b.count , 0 ) AS number, a.id, a.name, a.type, a.pic, a.amount, a.discount, a.total_count, a.pick_count, a.max_pick_count, " +
                        "a.valid_amount, a.is_can_pick, a.expire_type, a.fixed_date, a.expire_days, a.product_type, a.memo, a.create_time, a.status " +
                    "FROM guns_coupons a " +
                    "LEFT JOIN (select count(e.coupon_id) count, e.coupon_id FROM guns_coupons d, guns_coupons_record e WHERE d.id = e.coupon_id and e.member_id = #{member} GROUP BY e.coupon_id ) b ON a.id = b.coupon_id " +
                    "WHERE a.`status` = 1 and a.total_count > 0 and a.is_can_pick = 1 ) m " +
            "where m.max_pick_count > m.number and m.fixed_date > NOW()")
    List<CouponsResult> centerList(@Param("member") Long member);
}
