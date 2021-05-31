package cn.stylefeng.guns.cloud.product.model.api.result;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CouponsResult{

    private Long id;
    /**
     * 优惠券名称
     */
    private String name;
    /**
     * 类型 1:满减 2折扣
     */
    private Integer type;
    /**
     * 满减面额
     */
    private BigDecimal amount;
    /**
     * 折扣
     */
    private BigDecimal discount;
    /**
     * 总数量(库存)，=0则禁pick
     */
    private Integer totalCount;
    /**
     * 已领取数量
     */
    private Integer pickCount;
    /**
     * 每人最多领取数量
     */
    private Integer maxPickCount;
    /**
     * 满多少元可以使用
     */
    private BigDecimal validAmount;
    /**
     * 是否允许直接领取
     */
    private Integer isCanPick;
    /**
     * 过期类型 1:固定日期 2领取后有效天数
     */
    private Integer expireType;
    /**
     * 固定日期，互动结束时间
     */
    private Date fixedDate;
    /**
     * 过期天数，领取后几天有效
     */
    private Integer expireDays;
    /**
     * 产品类型（1:全部商品参与 2:指定商品参与）
     */
    private Integer productType;
    /**
     * 图片
     */
    private String pic;
    /**
     * 描述
     */
    private String memo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 开始时间
     */
//    private Date startTime;
    /**
     * 结束时间
     */
//    private Date endTime;
    /**
     * 状态（ 1:正常 2:关闭）
     */
    private Integer status;

/**---------------------couponRecord-------------------------*/

    private List<CouponsRecordResult> records;

    public List<CouponsRecordResult> getRecords() {
        return records;
    }

    public void setRecords(List<CouponsRecordResult> records) {
        this.records = records;
    }

    private List<CouponProductResult> list;

    public List<CouponProductResult> getList() {
        return list;
    }

    public void setList(List<CouponProductResult> list) {
        this.list = list;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPickCount() {
        return pickCount;
    }

    public void setPickCount(Integer pickCount) {
        this.pickCount = pickCount;
    }

    public Integer getMaxPickCount() {
        return maxPickCount;
    }

    public void setMaxPickCount(Integer maxPickCount) {
        this.maxPickCount = maxPickCount;
    }

    public BigDecimal getValidAmount() {
        return validAmount;
    }

    public void setValidAmount(BigDecimal validAmount) {
        this.validAmount = validAmount;
    }

    public Integer getIsCanPick() {
        return isCanPick;
    }

    public void setIsCanPick(Integer isCanPick) {
        this.isCanPick = isCanPick;
    }

    public Integer getExpireType() {
        return expireType;
    }

    public void setExpireType(Integer expireType) {
        this.expireType = expireType;
    }

    public Date getFixedDate() {
        return fixedDate;
    }

    public void setFixedDate(Date fixedDate) {
        this.fixedDate = fixedDate;
    }

    public Integer getExpireDays() {
        return expireDays;
    }

    public void setExpireDays(Integer expireDays) {
        this.expireDays = expireDays;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

//    public Date getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Date startTime) {
//        this.startTime = startTime;
//    }

//    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "GunsCoupons{" +
        ", id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", amount=" + amount +
        ", discount=" + discount +
        ", totalCount=" + totalCount +
        ", pickCount=" + pickCount +
        ", maxPickCount=" + maxPickCount +
        ", validAmount=" + validAmount +
        ", isCanPick=" + isCanPick +
        ", expireType=" + expireType +
        ", fixedDate=" + fixedDate +
        ", expireDays=" + expireDays +
        ", productType=" + productType +
        ", pic=" + pic +
        ", memo=" + memo +
        ", createTime=" + createTime +
//        ", startTime=" + startTime +
//        ", endTime=" + endTime +
        ", status=" + status +
        "}";
    }
}
