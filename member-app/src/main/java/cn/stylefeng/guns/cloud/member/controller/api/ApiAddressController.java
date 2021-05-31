package cn.stylefeng.guns.cloud.member.controller.api;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMemAddress;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemAddressParam;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemAddressResult;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.service.GunsMemAddressService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/address")
@Api(tags = "app-收货地址接口")
public class ApiAddressController {

    @Autowired
    private GunsMemAddressService addressService;


    @PostMapping("save")
    @ApiOperation("添加收货地址信息")
    public ResponseData save(@Validated @RequestBody  GunsMemAddressParam param){
        GunsMemAddress gunsMemAddress = new GunsMemAddress();
        ToolUtil.copyProperties(param, gunsMemAddress); //给实体赋值
        gunsMemAddress.setCreateTime(new DateTime());
        addressService.saveAddress(gunsMemAddress);
        return ResponseData.success();
    }


    /**
     * 修改会员信息
     * @param param
     * @return
     */
    @PostMapping("update")
    @ApiOperation("修改收货地址信息")
    public ResponseData update(@RequestBody @Validated GunsMemAddressParam param) {
        if (param.getId()==0){
            throw new ServiceException(500,"id不能为空");
        }
        GunsMemAddress gunsMemAddress=new GunsMemAddress();
        ToolUtil.copyProperties(param, gunsMemAddress); //给实体赋值
        gunsMemAddress.setUpdateTime(new DateTime());
        Boolean update= addressService.updateById(gunsMemAddress);
        if (update){
            return ResponseData.success();
        }else {
            return ResponseData.error("修改失败");
        }
    }

    @GetMapping("/echo")
    @ApiOperation("修改数据详情")
    public ResponseData echo(Long id){
        if(id==null){
            throw new ServiceException(500,"id不能为空");
        }
        GunsMemAddress gunsMemAddress = addressService.getById(id);
        return ResponseData.success(gunsMemAddress);
    }



    @GetMapping("select")
    @ApiOperation("查询收货地址")
    public ResponseData select(Long memberId){

        if (memberId==null){
            throw new ServiceException(500,"会员id不能为空");
        }
        List<GunsMemAddressResult> list = addressService.select(memberId);

        return ResponseData.success(list);
    }


    @PostMapping("delete")
    @ApiOperation("删除收货地址")
    public ResponseData remove(@RequestBody Map<String,Long> parm){
        if (parm==null||parm.get("id")==null){
            throw new ServiceException(500,"id不能为空");
        }
        Boolean remove = addressService.removeById(parm.get("id"));
        if (remove){
            return ResponseData.success();
        }else {
            return ResponseData.error("删除失败");
        }

    }

    @GetMapping("defaultAddress")
    @ApiOperation("查询默认地址")
    public ResponseData defaultAddress(Long memberId){

        return ResponseData.success(addressService.selectDefaultAddress(memberId));
    }

    @PostMapping("UpdateDefaultAddress")
    @ApiOperation("修改默认地址")
    public ResponseData UpdateDefaultAddress(@RequestBody Map<String,Long>param){

        return ResponseData.success(addressService.UpdateDefaultAddress(param.get("memberId"),param.get("id")));
    }

}
