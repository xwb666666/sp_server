package cn.stylefeng.guns.cloud.system.core.menus;

import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 基础数据中心的菜单
 *
 * @author fengshuonan
 * @Date 2019/9/17 17:47
 */
@RestController
@ApiResource(name = "文件菜单", path = "/fileMenuFlagController")
public class FileMenuFlagController {

    @ApiResource(name = "文件管理", path = "/files", menuFlag = true)
    public ResponseData files() {
        return ResponseData.success();
    }

}
