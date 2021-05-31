package cn.stylefeng.guns.cloud.member.service;

import cn.stylefeng.guns.cloud.api.member.entity.GunsMemIntegralDetail;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemIntegralDetailParam;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemIntegralDetailResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface GunsMemIntegralDetailService extends IService<GunsMemIntegralDetail> {
    Page<GunsMemIntegralDetailResult> getSelectList(GunsMemIntegralDetailParam param);

}
