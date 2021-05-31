package cn.stylefeng.guns.cloud.order.model.api.result;

import cn.stylefeng.guns.cloud.api.product.entity.GunsProBrand;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 订单列表单条数据包装
 */
@Data
public class OrderListResult {

    private String orderNo;         //订单号
    private Integer status;         //状态
    private String statusDes="待支付,已发货,待收货,已收货,已完成,已取消,退款中,待发货";
    private Integer orderType;      //订单类型
    private String orderTypeDes=" 普通订单,拼团订单,兑换订单";
    private Date orderTime;         //下单时间
    private Long shopId;            //店铺id
    private String shopName;        //店铺名称

    private List<OrderProduct> products;

    @Data
    public static class OrderProduct
    {
        private String name;      //商品名称
        private Long productId;    //商品编号
        private String picture;   //商品图片
        private Double salePrice;  //销售价格
        private Double quantity; //商品数量
        private String specs; //商品规格 如：黑色,xxl
    }
}
