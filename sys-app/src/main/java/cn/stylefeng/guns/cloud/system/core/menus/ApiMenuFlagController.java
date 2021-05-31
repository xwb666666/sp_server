package cn.stylefeng.guns.cloud.system.core.menus;

import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 接口文档菜单
 *
 * @author fengshuonan
 * @Date 2019/9/17 17:47
 */
@RestController
@ApiResource(name = "api管理", path = "/apiMenu")
public class ApiMenuFlagController {

    @ApiResource(name = "api管理菜单（一级）", path = "/api", menuFlag = true)
    public ResponseData api() {
        return ResponseData.success();
    }

    @ApiResource(name = "接口文档", path = "/swagger", menuFlag = true)
    public ResponseData swagger() {
        return ResponseData.success();
    }

}
