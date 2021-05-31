package cn.stylefeng.guns.cloud.product.service;
import cn.stylefeng.guns.cloud.api.product.entity.GunsProBrand;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProBrandParam;
import cn.stylefeng.guns.cloud.api.product.model.params.PageBrandParam;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProBrandResult;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.product.model.api.result.BrandResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2021-03-23
 */
public interface GunsProBrandService extends IService<GunsProBrand> {


    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2021-03-23
     */
    Boolean update(GunsProBrand gunsProBrand);

    /**
     * 查询单条数据，Specification模式
     *
     * @author stylefeng
     * @Date 2021-03-23
     */
    GunsProBrandResult findBySpec(Long id);


    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2021-03-23
     */
     PageResult<GunsProBrandResult> findPageBySpec(PageBrandParam param);


    List<BrandResult> select();

    Boolean removeId(Long id);

    List<GunsProBrandResult> selectList();
}
