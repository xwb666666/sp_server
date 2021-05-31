package cn.stylefeng.guns.cloud.system.core.menus;

import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 组织架构菜单标识控制器
 *
 * @author fengshuonan
 * @Date 2019/9/17 17:47
 */
@RestController
@ApiResource(name = "组织架构", path = "/entMenu")
public class EntMenuFlagController {

    @ApiResource(name = "组织架构菜单", path = "/enterprise", menuFlag = true)
    public ResponseData system() {
        return ResponseData.success();
    }

    @ApiResource(name = "公司管理", path = "/company", menuFlag = true)
    public ResponseData company() {
        return ResponseData.success();
    }

    @ApiResource(name = "部门管理", path = "/dept", menuFlag = true)
    public ResponseData dept() {
        return ResponseData.success();
    }

    @ApiResource(name = "人员管理", path = "/personal", menuFlag = true)
    public ResponseData personal() {
        return ResponseData.success();
    }

    @ApiResource(name = "职位管理", path = "/duty", menuFlag = true)
    public ResponseData duty() {
        return ResponseData.success();
    }

}
