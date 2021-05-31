package cn.stylefeng.guns.cloud.system.modular.tenant.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.tenant.entity.TenantInfo;
import cn.stylefeng.guns.cloud.system.modular.tenant.params.TenantInfoParam;
import cn.stylefeng.guns.cloud.system.modular.tenant.service.TenantInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;


/**
 * 租户表控制器
 *
 * @author stylefeng
 * @date 2019-06-16 20:57:22
 */
@RestController
@ApiResource(name = "多租户管理")
public class TenantInfoController {

    @Resource
    private TenantInfoService tenantInfoService;

    /**
     * 查询列表
     *
     * @author stylefeng
     * @date 2019-06-16
     */
    @GetResource(name = "查询租户列表", path = "/tenantInfo/page")
    public ResponseData page(TenantInfoParam tenantInfoParam) {
        return ResponseData.success(this.tenantInfoService.page(tenantInfoParam));
    }

    /**
     * 新增接口
     *
     * @author stylefeng
     * @date 2019-06-16
     */
    @PostResource(name = "新增租户", path = "/tenantInfo/add")
    public ResponseData add(@RequestBody @Validated(TenantInfoParam.add.class) TenantInfoParam tenantInfoParam) {
        this.tenantInfoService.add(tenantInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author stylefeng
     * @date 2019-06-16
     */
    @PostResource(name = "编辑租户", path = "/tenantInfo/edit")
    public ResponseData edit(@RequestBody @Validated(TenantInfoParam.edit.class) TenantInfoParam tenantInfoParam) {
        this.tenantInfoService.update(tenantInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author stylefeng
     * @date 2019-06-16
     */
    @PostResource(name = "删除租户", path = "/tenantInfo/delete")
    public ResponseData delete(@RequestBody @Validated(TenantInfoParam.delete.class) TenantInfoParam tenantInfoParam) {
        this.tenantInfoService.delete(tenantInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author stylefeng
     * @date 2019-06-16
     */
    @GetResource(name = "租户详情", path = "/tenantInfo/detail")
    public ResponseData detail(@Validated(TenantInfoParam.detail.class) TenantInfoParam tenantInfoParam) {
        TenantInfo detail = this.tenantInfoService.getById(tenantInfoParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 获取租户列表
     *
     * @author stylefeng
     * @date 2019-06-16
     */
    @GetResource(name = "获取租户列表", path = "/tenantInfo/listTenants")
    public ResponseData listTenants() {
        LambdaQueryWrapper<TenantInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(TenantInfo::getName, TenantInfo::getCode);

        List<TenantInfo> list = this.tenantInfoService.list(queryWrapper);
        return ResponseData.success(list);
    }

}


