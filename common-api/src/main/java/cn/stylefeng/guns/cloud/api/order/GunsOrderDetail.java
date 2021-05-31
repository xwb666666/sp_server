package cn.stylefeng.guns.cloud.api.order;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("guns_order_detail")
@Data
public class GunsOrderDetail {
    private Long id;                //id

    private String orderNo;           //订单号

    private Long productId;         //商品id

    private  String productName;   //商品名称

    private  Double quantity;       //数量

    private Double salePrice;       //销售价格

    private Double costPrice;       //成本价格

    private Integer shipStatus;     //发货状态 0已发货 2未发货

    private Integer refundStatus;   //退款状态 0已退款 1未退款

    private String specs;           //商品规格

    private Double couponAmount;    //优惠卷金额

    private Double discountAmount;  //折扣金额

    private Double promotionAmount; //促销金额

    private Integer status;         //状态(0 正常 1申请退货 )
}
