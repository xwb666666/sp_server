package cn.stylefeng.guns.cloud.product.model.api;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@TableName("guns_coupons")
public class GunsCoupons extends Model<GunsCoupons> {

    private static final long serialVersionUID = 1L;

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
    @TableField("total_count")
    private Integer totalCount;
    /**
     * 已领取数量
     */
    @TableField("pick_count")
    private Integer pickCount;
    /**
     * 每人最多领取数量
     */
    @TableField("max_pick_count")
    private Integer maxPickCount;
    /**
     * 满多少元可以使用
     */
    @TableField("valid_amount")
    private BigDecimal validAmount;
    /**
     * 是否允许直接领取
     */
    @TableField("is_can_pick")
    private Integer isCanPick;
    /**
     * 过期类型 1:固定日期 2领取后有效天数
     */
    @TableField("expire_type")
    private Integer expireType;
    /**
     * 固定日期，互动结束时间
     */
    @TableField("fixed_date")
    private Date fixedDate;
    /**
     * 过期天数，领取后几天有效
     */
    @TableField("expire_days")
    private Integer expireDays;
    /**
     * 产品类型（1:全部商品参与 2:指定商品参与）
     */
    @TableField("product_type")
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
    @TableField("create_time")
    private Date createTime;
//    /**
//     * 开始时间
//     */
//    @TableField("start_time")
//    private Date startTime;
//    /**
//     * 结束时间
//     */
//    @TableField("end_time")
//    private Date endTime;
    /**
     * 状态（ 1:正常 2:关闭）
     */
    private Integer status;


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
//
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
