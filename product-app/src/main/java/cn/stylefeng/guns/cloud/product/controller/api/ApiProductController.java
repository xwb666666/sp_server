package cn.stylefeng.guns.cloud.product.controller.api;

import cn.stylefeng.guns.cloud.api.product.model.params.HomeParam;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.model.api.param.ApiProductDetailParam;
import cn.stylefeng.guns.cloud.product.model.api.param.SearchParam;
import cn.stylefeng.guns.cloud.product.model.api.result.ApiProductDetailResult;
import cn.stylefeng.guns.cloud.product.model.api.result.GroupResult;
import cn.stylefeng.guns.cloud.product.model.api.result.HomeResult;
import cn.stylefeng.guns.cloud.product.model.api.result.SearchResult;
import cn.stylefeng.guns.cloud.product.service.GunsProGroupService;
import cn.stylefeng.guns.cloud.product.service.GunsProductService;
import io.swagger.annotations.Api;;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/product")
@Api(tags = "app-首页")
public class ApiProductController {

    @Resource
    private GunsProductService productService;

    @Resource
    private GunsProGroupService groupService;

    @PostMapping("search")
    @ApiOperation("商品搜索")
    private ResponseData productSearch(@RequestBody SearchParam param) {

        try {
            PageResult<SearchResult> result = productService.searchFromEs(param);
            return ResponseData.success(result);
        } catch (IOException ex) {
            return ResponseData.error(ex.getMessage());
        }
    }

    @GetMapping("group")
    @ApiOperation("首页分组")
    private ResponseData group() {

        List<GroupResult> select = groupService.select();
        return ResponseData.success(select);
    }

    @PostMapping("detail")
    @ApiOperation("商品详情")
    @CrossOrigin
    private ResponseData productDetail(@RequestBody ApiProductDetailParam param) {

        ApiProductDetailResult detail = productService.apiProductDetail(param);
        return ResponseData.success(detail);
    }

    @PostMapping("home")
    @ApiOperation("首页数据")
    public HomeResult home(@RequestBody HomeParam param){
        HomeResult result = productService.home(param);
        return result;
    }

}
