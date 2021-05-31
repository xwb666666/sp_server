package cn.stylefeng.guns.cloud.product.controller.api;


import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.service.GunsProBrandService;
import cn.stylefeng.guns.cloud.product.service.GunsProCategoryService;
import cn.stylefeng.guns.cloud.product.service.GunsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@Api(tags = "app-分类")
public class ApiCategoryController {

    @Autowired
    private GunsProBrandService brandService;

    @Autowired
    private GunsProCategoryService categoryService;

    @Autowired
    private GunsProductService productService;


    @GetMapping("brand")
    @ApiOperation("品牌")
    public ResponseData brand(){

        return ResponseData.success(brandService.select());

    }

    @GetMapping("category")
    @ApiOperation("分类")
    public ResponseData category(){

        return ResponseData.success(categoryService.CategoryList());

    }

}
