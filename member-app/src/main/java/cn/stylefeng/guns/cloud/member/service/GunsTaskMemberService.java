package cn.stylefeng.guns.cloud.member.service;

import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.model.GunsTaskMember;
import com.baomidou.mybatisplus.extension.service.IService;

public interface GunsTaskMemberService extends IService<GunsTaskMember> {

    /**-------------------------前台任务中心---------------------------*/

    int saveOne(GunsTaskMember gunsTaskMember);
}
