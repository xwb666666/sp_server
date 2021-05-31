package cn.stylefeng.guns.cloud.api.product.model.result;

import java.math.BigDecimal;
import java.util.Date;

public class CouponsRecordResult {

    private Long id;
    /**
     * 优惠券id
     */
    private Long couponId;
    /**
     * 领取会员id
     */
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
    private Integer pickType;
    /**
     * 领取时间
     */
    private Date pickTime;
    /**
     * 到期时间
     */
    private Date expireTime;
    /**
     * 使用时间
     */
    private Date useTime;
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
        ", startTime=" + startTime +
        ", status=" + status +
        "}";
    }
}
