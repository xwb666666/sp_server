package cn.stylefeng.guns.cloud.order.controller;

import cn.stylefeng.guns.cloud.api.order.GunsOrderLog;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.order.service.GunsOrderLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orderLog")
@Api(tags = "订单日志")
public class GunsOrderLogController {
    @Autowired
    private GunsOrderLogService logService;


    @GetMapping("selectOrderLog")
    @ApiOperation("查询订单日志信息")
    public ResponseData selectOrderLog(){

        return ResponseData.success(logService.selectOrderLog());
    }



}
