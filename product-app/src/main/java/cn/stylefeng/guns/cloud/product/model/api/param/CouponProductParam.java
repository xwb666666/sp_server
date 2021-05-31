package cn.stylefeng.guns.cloud.product.model.api.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "优惠券关联商品入参")
public class CouponProductParam {

    private Long id;
    @ApiModelProperty(value = "优惠券id", dataType="Long")
    private Long couponId;
    @ApiModelProperty(value = "商品id", dataType="Long")
    private Long productId;

    private List<Long> productIds;

    private Integer page=1;
    private Integer pageSize=20;

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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
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