package cn.stylefeng.guns.cloud.product.model.api;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

@TableName("guns_coupons_record")
public class GunsCouponsRecord extends Model<GunsCouponsRecord> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 优惠券id
     */
    @TableField("coupon_id")
    private Long couponId;
    /**
     * 领取会员id
     */
    @TableField("member_id")
    private Long memberId;
    /**
     * 面额
     */
    private BigDecimal amount;
    /**
     * 优惠码
     */
    private String code;
    /**
     * 获取方式（1:直接获取 2:新人发放）
     */
    @TableField("pick_type")
    private Integer pickType;
    /**
     * 领取时间
     */
    @TableField("pick_time")
    private Date pickTime;
    /**
     * 到期时间------根据Coupon 过期类型相应的结束日期或者领取后有效天数计算到期时间
     */
    @TableField("expire_time")
    private Date expireTime;
    /**
     * 使用时间
     */
    @TableField("use_time")
    private Date useTime;
    /**
     * 使用时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 状态
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPickType() {
        return pickType;
    }

    public void setPickType(Integer pickType) {
        this.pickType = pickType;
    }

    public Date getPickTime() {
        return pickTime;
    }

    public void setPickTime(Date pickTime) {
        this.pickTime = pickTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GunsCouponsRecord{" +
        ", id=" + id +
        ", couponId=" + couponId +
        ", memberId=" + memberId +
        ", amount=" + amount +
        ", code=" + code +
        ", pickType=" + pickType +
        ", pickTime=" + pickTime +
        ", expireTime=" + expireTime +
        ", useTime=" + useTime +
        ", status=" + status +
        "}";
    }
}
