package cn.stylefeng.guns.cloud.member.mapper;

import cn.stylefeng.guns.cloud.member.model.GunsTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GunsTaskMapper extends BaseMapper<GunsTask> {

    @Select("select * from guns_task ")
    @Results(id="studentMap", value={
            @Result(column="id", property="id", id=true),
            @Result(column="username", property="username"),
            @Result(column="name", property="name"),
            @Result(column="id", property="taskMembers", javaType= List.class,
                    many=@Many(select="cn.stylefeng.guns.cloud.member.mapper.GunsTaskMemberMapper.selectByTaskId"))
    })
    List<GunsTask> selectAll();
}
