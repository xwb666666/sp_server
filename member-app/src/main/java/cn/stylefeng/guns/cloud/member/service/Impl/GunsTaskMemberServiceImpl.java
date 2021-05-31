package cn.stylefeng.guns.cloud.member.service.Impl;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.constants.Constants;
import cn.stylefeng.guns.cloud.member.model.GunsTaskMember;
import cn.stylefeng.guns.cloud.member.mapper.GunsTaskMemberMapper;
import cn.stylefeng.guns.cloud.member.service.GunsTaskMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class GunsTaskMemberServiceImpl extends ServiceImpl<GunsTaskMemberMapper, GunsTaskMember> implements GunsTaskMemberService {


    @Override
    public int saveOne(GunsTaskMember taskMember){
        GunsTaskMember gunsTaskMember = new GunsTaskMember();
        gunsTaskMember.setId(ToolUtil.getIdGenLong());
        gunsTaskMember.setMemberId(taskMember.getMemberId());
        gunsTaskMember.setStatus(Constants.ENABLE);
        gunsTaskMember.setCreateDate(new DateTime());
        gunsTaskMember.setTaskId(taskMember.getTaskId());
        return baseMapper.insert(gunsTaskMember);
    }
}
