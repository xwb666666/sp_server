package cn.stylefeng.guns.cloud.product.controller;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProProductParam;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProQueryParam;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProListResult;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProProductResult;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.service.GunsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "/product")
@Api(tags = "商品管理")
public class ProductController {

    @Resource
    private GunsProductService productService;

    @PostMapping("/add")
    @ApiOperation(value = "商品添加")
    public ResponseData add(@Validated @RequestBody GunsProProductParam param) {

        productService.addProduct(param);
        return ResponseData.success();
    }

    @PostMapping("/update")
    @ApiOperation("修改商品")
    public ResponseData update(@RequestBody GunsProProductParam param){
        productService.update(param);
        return ResponseData.success();
    }


    /**
     * 假删除（添加到回收站）
     *
     * @param para
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除商品")
    public ResponseData delete(@Validated @RequestBody Map<String, Long> para) {

        productService.deleteById(para.get("id"));
        return ResponseData.success();
    }

    /**
     * 回收站删除（彻底删除）
     *
     * @param para
     * @return
     */
    @PostMapping("/remove")
    @ApiOperation(value = "删除商品")
    public ResponseData remove(@Validated @RequestBody Map<String, Long> para) {
        productService.remove(para.get("id"));
        return ResponseData.success();
    }

    @PostMapping("/detail")
    @ApiOperation("获取商品详情")
    public ResponseData detail(@Validated @RequestBody Map<String,Long> para){
        Long id=para.get("id");
        GunsProProductResult result= productService.detail(id);
        return ResponseData.success(result);
    }

    @PostMapping("/list")
    @ApiOperation("商品列表")
    public ResponseData getProductList(@RequestBody GunsProQueryParam param)
    {
        PageResult<GunsProListResult> result= productService.getProductList(param);
        return ResponseData.success(result);
    }

    @PostMapping("/uptosale")
    @ApiOperation("商品上架功能")
    public ResponseData upToSale(@RequestBody Map<String,Long> param){
        Long id=param.get("id");
        try {
            productService.upToSale(id);
        }
        catch (Exception ex)
        {
            return ResponseData.error(ex.getMessage());
        }
        return ResponseData.success();
    }

    @PostMapping("/downfromsale")
    @ApiOperation("商品下架")
    public ResponseData downFromSale(@RequestBody Map<String,Long> param)
    {
        Long id=param.get("id");
        try {
            boolean b = productService.downFromSale(id);
        }
        catch (Exception ex)
        {
            return ResponseData.error(ex.getMessage());
        }
        return ResponseData.success();
    }

//    @GetMapping("/test_es")
//    @ApiOperation("测试es")
//    public ResponseData testEs() throws IOException {
//        IndexRequest request=new IndexRequest("users");
//        request.id("1");
//        String jsonString = "{" +
//                "\"user\":\"kimchy\"," +
//                "\"postDate\":\"2013-01-30\"," +
//                "\"message\":\"trying out Elasticsearch\"" +
//                "}";
//        request.source(jsonString, XContentType.JSON);
//        IndexResponse response=restHighLevelClient.index(request, AppConfig.COMMON_OPTIONS);
//        return ResponseData.success(response);
//    }


}