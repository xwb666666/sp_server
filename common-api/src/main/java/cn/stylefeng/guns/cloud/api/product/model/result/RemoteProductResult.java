package cn.stylefeng.guns.cloud.api.product.model.result;

import cn.stylefeng.guns.cloud.api.product.model.params.SkuAndSkuNameItem;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProSkuResult;
import cn.stylefeng.guns.cloud.api.product.model.result.RemoteSkuResult;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 远程调用商品信息
 */
@Data
public class RemoteProductResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long shopId;
    private String code;
    private String name;
    private BigDecimal cost;
    private BigDecimal originalPrice;
    private BigDecimal presentPrice;
    private Long quantity;

    private String des;
    private String desDetails;
    private String picture;

    private Long salesVolume;
    private Long totalStock;

    private Integer isSku;

    private List<RemoteSkuResult> skuInfo;

    private Integer status; //状态 0 删除 1正常 2上架 3下架
    private String statusText;

    private Date createDate;
    private Date updateDate;
    private Date putOnDate;
    private Date lowOffDate;

    private Double couponAmount;    //优惠卷金额

    private Double discountAmount;  //折扣金额

    private Double promotionAmount; //促销金额

}
