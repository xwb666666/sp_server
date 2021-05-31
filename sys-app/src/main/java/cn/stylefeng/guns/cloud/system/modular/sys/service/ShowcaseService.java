package cn.stylefeng.guns.cloud.system.modular.sys.service;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.Advertising;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.Showcase;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AdvertisingParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AdvertisingStatusParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.ShowcaseParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.AdvertisingResult;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.ShowcaseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface ShowcaseService extends IService<Showcase> {


    Boolean saveShowcaseImg(ShowcaseParam param);

    Boolean removeShowcaseImg(Long[] ids);

    List<ShowcaseResult> selectShowcaseImg();

    Boolean updateShowcaseImg(ShowcaseParam param);

    ShowcaseResult selectAdvertisingImgDetail(Long id);
}
