package cn.stylefeng.guns.cloud.member.mapper;

import cn.stylefeng.guns.cloud.api.member.entity.GunsMemAccountDetail;

import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemAccountDetailParam;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemAccountDetailResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface GunsMemAccountDetailMapper extends BaseMapper<GunsMemAccountDetail> {

    List<GunsMemAccountDetailResult> getSelect(Page<GunsMemAccountDetailResult>page, GunsMemAccountDetailParam param);



}
