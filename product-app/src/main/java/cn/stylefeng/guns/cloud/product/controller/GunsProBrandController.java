package cn.stylefeng.guns.cloud.product.controller;
import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProBrand;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProBrandParam;
import cn.stylefeng.guns.cloud.api.product.model.params.PageBrandParam;
import cn.stylefeng.guns.cloud.api.product.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.product.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.service.GunsProBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 品牌表服务提供者
 *
 * @author stylefeng
 * @Date 2021-03-23 13:17:31
 */
@RestController
@RequestMapping("/product/brand")
@Api(tags = "品牌信息")
public class GunsProBrandController {


    @Autowired
    private GunsProBrandService gunsProBrandService;

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2021-03-23
     */
    @PostMapping("/add")
    @ApiOperation("添加品牌")
    public ResponseData add(@RequestBody @Validated({AddGroup.class})  GunsProBrandParam param) {
        GunsProBrand gunsProBrand=new GunsProBrand();
        ToolUtil.copyProperties(param,gunsProBrand);
        gunsProBrand.setCreateDate(new DateTime());
        boolean save = gunsProBrandService.save(gunsProBrand);
        if (save){
            return ResponseData.success();
        }else {
            return ResponseData.success("添加失败");
        }
    }

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2021-03-23
     */
    @PostMapping("/delete")
    @ApiOperation("删除品牌")
    public ResponseData delete(@RequestBody Map<String,Long>  para) {
        if (para.get("id")==null){
            throw new ServiceException(50,"id不能为空");
        }
        gunsProBrandService.removeId(para.get("id"));
        return ResponseData.success();
    }

    /**
     * 修改
     *
     * @author stylefeng
     * @Date 2021-03-23
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改品牌")
    public ResponseData update(@RequestBody GunsProBrandParam param) {

        GunsProBrand gunsProBrand=new GunsProBrand();
        ToolUtil.copyProperties(param,gunsProBrand);
        gunsProBrand.setUpdateDate(new DateTime());
        gunsProBrandService.update(gunsProBrand);
        return ResponseData.success();
    }

    /**
     * 查询单条详情
     *
     * @author stylefeng
     * @Date 2021-03-23
     */
    @GetMapping(value = "/findBySpec")
    @ApiOperation("查询单条品牌信息")
    public ResponseData findBySpec(Long id) {
        if (id==null){
            throw new ServiceException(500,"id不能为空");
        }
        return ResponseData.success( gunsProBrandService.findBySpec(id));
    }

    /**
     * 分页查询列表
     *
     * @author stylefeng
     * @Date 2021-03-23
     * @return
     */
    @GetMapping("/findPageBySpec")
    @ApiOperation("分页查询所有品牌信息")
    public ResponseData findPageBySpec(PageBrandParam param) {

        return ResponseData.success(gunsProBrandService.findPageBySpec(param));
    }





    @GetMapping("/selectList")
    @ApiOperation("查询所有品牌信息")
    public ResponseData selectList() {

        return ResponseData.success(gunsProBrandService.selectList());
    }


}
