package cn.stylefeng.guns.cloud.system.modular.sys.service;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.Advertising;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AdvertisingParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.AdvertisingStatusParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.ShowcaseParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.ShowcaseStatusParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.AdvertisingResult;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.ShowcaseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface AdvertisingService extends IService<Advertising> {


    Boolean saveHomePicture(AdvertisingParam param);

    Boolean removeAdvertisingImg(Long[] ids);

    List<AdvertisingResult> selectListAdvertisingImg();

    Boolean updateAdvertisingImg(AdvertisingParam param);

    /*List<ShowcaseResult> selectShowcaseImg();*/

    List<AdvertisingResult> ApiSelectAdvertisingImg();

    AdvertisingResult selectAdvertisingImgDetail(Long id);

}
