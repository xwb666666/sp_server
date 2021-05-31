package cn.stylefeng.guns.cloud.system.modular.sys.controller.api;


import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiResource(name = "区域", path = "api/area")
@Api(tags = "区域接口")
public class ApiAreaController {
    @Autowired
    private SysAreaService sysAreaService;


    @ApiOperation("获取区域")
    @GetResource(path = "/list", name = "获取区域列表")
    public ResponseData getCateWithTree(){


        return ResponseData.success(sysAreaService.getCateWithTree());
    }




}
