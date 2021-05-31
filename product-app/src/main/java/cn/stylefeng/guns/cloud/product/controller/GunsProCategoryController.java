package cn.stylefeng.guns.cloud.product.controller;

import cn.stylefeng.guns.cloud.api.product.entity.GunsProCategory;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProCategoryParam;
import cn.stylefeng.guns.cloud.api.product.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.api.product.model.validated.UpdateGroup;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.service.GunsProCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product/category")
@Api(tags = "分类信息")
public class GunsProCategoryController {

    @Resource
    GunsProCategoryService service;

    @RequestMapping(value = "/getCate",method = RequestMethod.GET)
    @ApiOperation("查询所有分类信息")
    public ResponseData getCateWithTree() {
        return ResponseData.success(service.getCateWithTree());
    }



    /**
     * 删除
     * @param para
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation("删除分类信息")
    public ResponseData delete(@RequestBody Map<String,Long> para) {
        if (para.get("id")==null){
            throw new ServiceException(500,"id不能为空");
        }
        service.removeId(para.get("id"));
       return ResponseData.success();

    }


    /**
     * 添加
     * @param param
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加分类信息")
    public ResponseData add(@Validated({AddGroup.class}) @RequestBody GunsProCategoryParam param) {
        GunsProCategory entity = new GunsProCategory();
        ToolUtil.copyProperties(param, entity); //参数到实体copy
        Boolean result = service.save(entity);
        if (result) {
            return ResponseData.success();
        }else {
            return ResponseData.error("添加失败");
        }
    }


    /**
     * 修改
     * @param param
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改分类信息")
    public ResponseData update(@Validated({UpdateGroup.class}) @RequestBody GunsProCategoryParam param){
        GunsProCategory gunsProCategory =new GunsProCategory();
        ToolUtil.copyProperties(param, gunsProCategory); //参数到实体copy
        Boolean result=service.updateById(gunsProCategory);
        if (result) {
            return ResponseData.success();
        }else {
            return ResponseData.error("更新失败");
        }

    }

    /**
     *
     * @param para 主键id
     * @return
     */
    @PostMapping("/echo")
    @ApiOperation("修改数据详情")
    public ResponseData echo(@Validated @RequestBody Map<String,Long> para ){

       GunsProCategory gunsProCategory= service.getById(para.get("id"));

        return ResponseData.success(gunsProCategory);
    }

    @GetMapping( "/getCateById")
    @ApiOperation("根据id查询分类")
    public ResponseData getCateById(@Validated Long id) {
        if(id==null)
            return ResponseData.error("缺少参数id");
        return ResponseData.success(service.getById(id));
    }


    @GetMapping("/getChildrenById")
    @ApiOperation("根据id查询下级分类")
    public ResponseData getChildrenById(@Validated Long id) {
        if(id==null)
            return ResponseData.error("缺少参数id");
        return ResponseData.success(service.list(new QueryWrapper<GunsProCategory>().eq("parent_id",id)));
    }


}
