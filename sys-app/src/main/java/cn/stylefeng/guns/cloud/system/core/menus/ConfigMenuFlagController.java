package cn.stylefeng.guns.cloud.system.core.menus;

import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 参数配置菜单
 *
 * @author fengshuonan
 * @Date 2019/9/17 17:47
 */
@RestController
@ApiResource(name = "参数配置", path = "/configMenu")
public class ConfigMenuFlagController {

    @ApiResource(name = "参数配置菜单（一级）", path = "/configMenu", menuFlag = true)
    public ResponseData configMenu() {
        return ResponseData.success();
    }

    @ApiResource(name = "参数管理", path = "/configs", menuFlag = true)
    public ResponseData configs() {
        return ResponseData.success();
    }

}
