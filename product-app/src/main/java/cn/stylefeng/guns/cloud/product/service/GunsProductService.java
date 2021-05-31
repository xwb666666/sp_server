package cn.stylefeng.guns.cloud.product.service;

import cn.stylefeng.guns.cloud.api.product.entity.GunsProProduct;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProProductParam;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProQueryParam;
import cn.stylefeng.guns.cloud.api.product.model.params.HomeParam;
import cn.stylefeng.guns.cloud.api.product.model.params.RomteProductSkuParam;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProListResult;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProProductResult;
import cn.stylefeng.guns.cloud.api.product.model.result.RemoteProductResult;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.product.model.api.param.ApiProductDetailParam;
import cn.stylefeng.guns.cloud.product.model.api.param.SearchParam;
import cn.stylefeng.guns.cloud.product.model.api.result.ApiProductDetailResult;
import cn.stylefeng.guns.cloud.product.model.api.result.HomeResult;
import cn.stylefeng.guns.cloud.product.model.api.result.ProductResult;
import cn.stylefeng.guns.cloud.product.model.api.result.SearchResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GunsProductService extends IService<GunsProProduct> {

    boolean addProduct(GunsProProductParam param) ;

    boolean deleteById(Long id);

    boolean remove(Long id);

    GunsProProductResult detail(Long id);

    boolean update(GunsProProductParam param);

    PageResult<GunsProListResult> getProductList(GunsProQueryParam param);

    PageResult<SearchResult> searchFromEs(SearchParam param) throws IOException;

    boolean upToSale(Long id) throws IOException;

    boolean downFromSale(Long id) throws IOException;

    ApiProductDetailResult apiProductDetail(ApiProductDetailParam param);

    List<RemoteProductResult> getRealProducts(List<RomteProductSkuParam> list);

    public boolean editProductStock(Map<String,Double> map);

    HomeResult  home(HomeParam param);

    PageResult<ProductResult> categoryProductList(Long memberId);

    PageResult<ProductResult> product();

    PageResult<ProductResult> hotListProduct();

    PageResult<ProductResult> brandActivityProduct();

    PageResult<ProductResult> seckillTodayProduct();
}
