package cn.stylefeng.guns.cloud.product.mapper;

import cn.stylefeng.guns.cloud.api.member.entity.GunsProBrowse;
import cn.stylefeng.guns.cloud.api.member.entity.GunsProCollect;
import cn.stylefeng.guns.cloud.product.model.api.param.BrowseParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CollectParam;
import cn.stylefeng.guns.cloud.product.model.api.result.BrowseResult;
import cn.stylefeng.guns.cloud.product.model.api.result.CollectResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface BrowseMapper extends BaseMapper<GunsProBrowse> {
    List<BrowseResult> selectList(Page<BrowseResult> page, BrowseParam param);


}
