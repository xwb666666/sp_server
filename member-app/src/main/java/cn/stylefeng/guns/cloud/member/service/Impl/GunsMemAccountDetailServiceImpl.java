package cn.stylefeng.guns.cloud.member.service.Impl;

import cn.stylefeng.guns.cloud.api.member.entity.GunsMemAccount;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMemAccountDetail;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMember;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemAccountDetailParam;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemAccountDetailResult;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemAccountDetailMapper;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemAccountMapper;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemMapper;
import cn.stylefeng.guns.cloud.member.service.GunsMemAccountDetailService;
import cn.stylefeng.guns.cloud.member.service.GunsMemService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
public class GunsMemAccountDetailServiceImpl extends ServiceImpl<GunsMemAccountDetailMapper, GunsMemAccountDetail> implements GunsMemAccountDetailService {

@Autowired
private GunsMemService gunsMemService;

    @Override
    public Page<GunsMemAccountDetailResult> getSelect(@Validated GunsMemAccountDetailParam param) {
        GunsMemParamResult gunsMemParamResult = gunsMemService.getById(param.getMemberId());
        if (gunsMemParamResult==null){
            throw new ServiceException(500,"会员信息不存在");
        }

        Page<GunsMemAccountDetailResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());
        Page<GunsMemAccountDetailResult> result = page.setRecords(baseMapper.getSelect(page,param));
        return result;
    }
}
