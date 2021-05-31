package cn.stylefeng.guns.cloud.product.service;

import cn.stylefeng.guns.cloud.api.product.entity.GunsProSkuName;
import com.baomidou.mybatisplus.extension.service.IService;


public interface GunsProSkuNameService extends IService<GunsProSkuName> {


    /**
     * 通过skuName 和 parentID 新增或直接获取 SkuName 实体
     * @param skuName
     * @param parentId
     * @return
     */
    GunsProSkuName saveOrGetSkuName(String skuName,Long parentId);

}
