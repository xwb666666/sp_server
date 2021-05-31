package cn.stylefeng.guns.cloud.member.controller.api;


import cn.stylefeng.guns.cloud.member.model.api.param.RechargeWithdrawalParam;
import cn.stylefeng.guns.cloud.member.service.RechargeWithdrawalService;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(tags = "Api-充值提现")
public class RechargeWithdrawalController {

    @Autowired
    private RechargeWithdrawalService rechargeWithdrawalService;

    @PostMapping("recharge")
    @ApiOperation("充值")
    public ResponseData recharge(@RequestBody RechargeWithdrawalParam param){

        rechargeWithdrawalService.recharge(param);

        return ResponseData.success();

    }

    /*@GetMapping("rechargeInfo")
    @ApiOperation("获取充值信息")
    public ResponseData rechargeInfo(){

        rechargeWithdrawalService.rechargeInfo();


        return ResponseData.success();

    }*/



}
