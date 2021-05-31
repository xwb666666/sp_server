package cn.stylefeng.guns.cloud.order.controller.api;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.order.model.api.param.ApiOrderParam;
import cn.stylefeng.guns.cloud.order.model.api.param.CreateOrderParam;
import cn.stylefeng.guns.cloud.order.model.api.param.OrderParam;
import cn.stylefeng.guns.cloud.order.model.api.result.PreOrderResult;
import cn.stylefeng.guns.cloud.order.service.GunsOrdDetailService;
import cn.stylefeng.guns.cloud.order.service.GunsOrdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Api(tags = "app-订单")
public class ApiOrderController {

    @Autowired
    private GunsOrdService ordService;

    @Autowired
    private GunsOrdDetailService detailService;

    /**
     * 查询订单信息
     * @param param
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询订单信息")
    public ResponseData OrderList(ApiOrderParam param){

        if (param.getMemberId()==null){
            throw new ServiceException(500,"会员id不能为空");
        }
        if (param.getOrderType()==null){
            throw new ServiceException(500,"订单类型不能为空");

        }

        return ResponseData.success(ordService.orderList(param));
    }


    /**
     * 查询订单详情信息
     * @param orderNo
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation("查询订单详细信息")
    public ResponseData selectByOrderDetail(String orderNo){
        if (orderNo==null){
            throw new ServiceException(500,"订单号不能为空");
        }
        return ResponseData.success(detailService.selectByOrderDetail(orderNo));
    }


    /**
     * 取消订单
     * @param param
     * @return
     */
    @PostMapping("/cancel")
    @ApiOperation("取消订单信息")
    public ResponseData cancelOrder(@RequestBody OrderParam param){

        return ResponseData.success(ordService.cancel(param));
    }

    /**
     * 删除订单信息
     * @param param
     * @return
     */
    @PostMapping("delete")
    @ApiOperation("删除订单信息")
    public ResponseData deleteOrder(@RequestBody OrderParam param){
        ordService.delete(param);
        return ResponseData.success();
    }

    @PostMapping("/pre/order")
    @ApiOperation(value = "准备下单", notes = "从购物车准备下单结算")
    public PreOrderResult preOrder(@RequestBody OrderParam param){
        if (null == param)
            throw new ServiceException(500, "memberId缺失！");
        Long memberId = param.getMemberId();
        return ordService.preOrder(memberId);
    }

    @PostMapping("/create")
    @ApiOperation(value = "下单", notes = "下单生成订单")
    public PreOrderResult create(@RequestBody CreateOrderParam param){
        //获取前台传入参数
        if (param == null)
            throw new ServiceException(500, "参数缺失！");
        if (param.getMemberId() == null)
            throw new ServiceException(500, "memberId参数缺失！");
        return ordService.create(param);
    }


}
