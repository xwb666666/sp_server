package cn.stylefeng.guns.cloud.product.mapper;

import cn.stylefeng.guns.cloud.api.member.entity.GunsProCollect;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemberQueryParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CollectParam;
import cn.stylefeng.guns.cloud.product.model.api.result.CollectResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface CollectMapper extends BaseMapper<GunsProCollect> {
    List<CollectResult> selectList(Page<CollectResult> page, CollectParam param);


}
