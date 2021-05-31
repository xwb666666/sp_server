package cn.stylefeng.guns.cloud.product.model.api.result;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

public class CouponProductResult {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long couponId;
    private Long productId;



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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "GunsCouponProduct{" +
        ", id=" + id +
        ", couponId=" + couponId +
        ", productId=" + productId +
        "}";
    }
}