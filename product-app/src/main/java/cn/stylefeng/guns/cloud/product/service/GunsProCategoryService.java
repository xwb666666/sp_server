package cn.stylefeng.guns.cloud.product.service;

import cn.stylefeng.guns.cloud.api.product.entity.GunsProCategory;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProCategoryResult;
import cn.stylefeng.guns.cloud.product.model.api.result.ApiGunsProCategoryResult;
import cn.stylefeng.guns.cloud.product.model.api.result.CategoryResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GunsProCategoryService extends IService<GunsProCategory> {

    List<GunsProCategoryResult> getCateWithTree();

    List<GunsProCategoryResult> CategoryList();

    public List<GunsProCategoryResult> getAllCate(Long id);

    Boolean removeId(Long id);

    List<ApiGunsProCategoryResult> OneCategoryList();
}
