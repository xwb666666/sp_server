package cn.stylefeng.guns.cloud.product.service;
import cn.stylefeng.guns.cloud.api.member.entity.GunsProBrowse;
import cn.stylefeng.guns.cloud.api.member.entity.GunsProCollect;
import cn.stylefeng.guns.cloud.product.model.api.param.BrowseParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CollectParam;
import cn.stylefeng.guns.cloud.product.model.api.result.BrowseResult;
import cn.stylefeng.guns.cloud.product.model.api.result.CollectResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;


public interface BrowseService extends IService<GunsProBrowse> {


    Page<Map<String,Object>> selectList(BrowseParam param);

    Boolean addSave(GunsProBrowse gunsProBrowse);

    Boolean removeIds(Long[] ids);

}
