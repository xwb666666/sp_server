package cn.stylefeng.guns.cloud.system.modular.dbs.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.param.BaseParam;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.dbs.entity.DatabaseInfo;
import cn.stylefeng.guns.cloud.system.modular.dbs.param.DatabaseInfoParam;
import cn.stylefeng.guns.cloud.system.modular.dbs.service.DatabaseInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;


/**
 * 数据库信息表控制器
 *
 * @author stylefeng
 * @date 2019-06-15 17:05:23
 */
@RestController
@ApiResource(name = "数据源管理")
public class DatabaseInfoController {

    @Resource
    private DatabaseInfoService databaseInfoService;

    /**
     * 查询列表
     *
     * @author stylefeng
     * @date 2019-06-15
     */
    @GetResource(name = "查询数据源列表", path = "/databaseInfo/page")
    public ResponseData page(DatabaseInfoParam databaseInfoParam) {
        return ResponseData.success(databaseInfoService.page(databaseInfoParam));
    }

    /**
     * 新增接口
     *
     * @author stylefeng
     * @date 2019-06-15
     */
    @PostResource(name = "新增数据源", path = "/databaseInfo/add")
    public ResponseData add(@RequestBody @Validated(DatabaseInfoParam.add.class) DatabaseInfoParam databaseInfoParam) {
        databaseInfoService.add(databaseInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author stylefeng
     * @date 2019-06-15
     */
    @PostResource(name = "编辑数据源", path = "/databaseInfo/edit")
    public ResponseData edit(@RequestBody @Validated(DatabaseInfoParam.edit.class) DatabaseInfoParam databaseInfoParam) {
        databaseInfoService.edit(databaseInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author stylefeng
     * @date 2019-06-15
     */
    @PostResource(name = "删除数据源", path = "/databaseInfo/delete")
    public ResponseData delete(@RequestBody @Validated(DatabaseInfoParam.delete.class) DatabaseInfoParam databaseInfoParam) {
        databaseInfoService.delete(databaseInfoParam);
        return ResponseData.success();
    }

    /**
     * 数据源详情
     *
     * @author stylefeng
     * @date 2019-06-15
     */
    @PostResource(name = "数据源详情", path = "/databaseInfo/detail")
    public ResponseData detail(@RequestBody @Validated(BaseParam.detail.class) DatabaseInfoParam databaseInfoParam) {
        DatabaseInfo databaseInfo = databaseInfoService.getById(databaseInfoParam.getId());
        databaseInfo.setPassword("***");
        return ResponseData.success(databaseInfo);
    }

}


