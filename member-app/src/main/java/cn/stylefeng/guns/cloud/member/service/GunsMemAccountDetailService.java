package cn.stylefeng.guns.cloud.member.service;

import cn.stylefeng.guns.cloud.api.member.entity.GunsMemAccountDetail;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemAccountDetailParam;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemAccountDetailResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface GunsMemAccountDetailService extends IService<GunsMemAccountDetail> {

    Page<GunsMemAccountDetailResult> getSelect(GunsMemAccountDetailParam param);
}
