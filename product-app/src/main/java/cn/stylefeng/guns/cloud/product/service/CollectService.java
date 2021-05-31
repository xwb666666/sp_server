package cn.stylefeng.guns.cloud.product.service;
import cn.stylefeng.guns.cloud.api.member.entity.GunsProCollect;
import cn.stylefeng.guns.cloud.product.model.api.param.AddCollectParam;
import cn.stylefeng.guns.cloud.product.model.api.param.CollectParam;
import cn.stylefeng.guns.cloud.product.model.api.result.CollectResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface CollectService extends IService<GunsProCollect> {


    Page<CollectResult> selectList(CollectParam param);

    Boolean addSave(GunsProCollect gunsProCollect);

    Boolean removeId(Long id);

}
