package cn.stylefeng.guns.cloud.member.controller;


import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemAddressResult;
import cn.stylefeng.guns.cloud.member.service.GunsMemAddressService;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("member/address")
@Api(tags = "收货地址")
public class GunsMemAddressController {

    @Autowired
    private GunsMemAddressService addressService;


    /**
     * 查询会员收货地址
     * @param memberId
     * @return
     */
    @GetMapping("select")
    @ApiOperation("查询收货地址")
    public ResponseData select(Long memberId){
        List<GunsMemAddressResult> list = addressService.select(memberId);

        return ResponseData.success(list);

    }



}
