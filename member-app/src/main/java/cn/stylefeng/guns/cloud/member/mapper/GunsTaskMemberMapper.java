package cn.stylefeng.guns.cloud.member.mapper;

import cn.stylefeng.guns.cloud.member.model.GunsTaskMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GunsTaskMemberMapper extends BaseMapper<GunsTaskMember> {

    @Select("select * from guns_task_member where task_id = #{id}")
    GunsTaskMember selectByTaskId(@Param("id") Long id);
}
