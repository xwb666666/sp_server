package cn.stylefeng.guns.cloud.order.controller;

import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.order.model.param.DeliverParam;
import cn.stylefeng.guns.cloud.order.model.param.OrderListParam;
import cn.stylefeng.guns.cloud.order.model.param.UpdateAddressParam;
import cn.stylefeng.guns.cloud.order.service.GunsOrdDetailService;
import cn.stylefeng.guns.cloud.order.service.GunsOrdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("order")
@Api(tags = "订单信息")
public class GunsOrdController {

    @Autowired
    private GunsOrdService ordService;

    @Autowired
    private GunsOrdDetailService detailService;

    /**
     * 查询订单信息
     * @param param
     * @return
     */
    @GetMapping("selectOrder")
    @ApiOperation("查询订单信息")
    public ResponseData selectOrder(OrderListParam param){

        return ResponseData.success(ordService.selectOrderList(param));
    }

    /**
     * 查询订单详情信息
     * @param orderNo
     * @return
     */
    @GetMapping("selectOrderDetail")
    @ApiOperation("查询订单详细信息")
    public ResponseData selectOrderDetail(String orderNo){


        return ResponseData.success(detailService.selectOrderDetail(orderNo));
    }


    /**
     * 删除订单信息
     * @param param
     * @return
     */
    @PostMapping("deleteByOrder")
    @ApiOperation("删除订单信息")
    public ResponseData deleteByOrder(@RequestBody Map<String,String> param) {

        boolean remove = ordService.deleteByOrder(param.get("orderNo"));
        if (remove) {
            return ResponseData.success();
        } else {
            return ResponseData.error("删除失败");
        }
    }

    @PostMapping("updateAddress")
    @ApiOperation("修改收货地址")
    public ResponseData cancelOrder(@RequestBody UpdateAddressParam param){

        ordService.updateAddress(param);

        return ResponseData.success();
    }

    @PostMapping("deliver")
    @ApiOperation("发货")
    public ResponseData deliver(@RequestBody DeliverParam param){

        ordService.deliver(param);
        return ResponseData.success();
    }


}
