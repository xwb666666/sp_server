package cn.stylefeng.guns.cloud.product.service.Impl;

import cn.stylefeng.guns.cloud.api.product.entity.GunsProSkuName;
import cn.stylefeng.guns.cloud.product.mapper.GunsProSkuNameMapper;
import cn.stylefeng.guns.cloud.product.service.GunsProSkuNameService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class GunsProSkuNameServiceImpl extends ServiceImpl<GunsProSkuNameMapper, GunsProSkuName> implements GunsProSkuNameService {


    @Override
    public GunsProSkuName saveOrGetSkuName(String skuName, Long parentId) {
        //首先查找
        GunsProSkuName entity = baseMapper.selectOne(new QueryWrapper<GunsProSkuName>()
                .eq("name", skuName)
                .eq("parent_id", parentId));
        //如果查询不到，新增
        if (entity == null) {
            entity = new GunsProSkuName();
            entity.setName(skuName);
            entity.setParentId(parentId);
            baseMapper.insert(entity);
        }
        return entity;
    }
}
