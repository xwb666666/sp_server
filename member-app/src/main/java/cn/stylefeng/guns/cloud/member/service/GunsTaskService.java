package cn.stylefeng.guns.cloud.member.service;

import cn.stylefeng.guns.cloud.member.model.GunsTask;
import cn.stylefeng.guns.cloud.member.model.api.param.TaskParam;
import cn.stylefeng.guns.cloud.member.model.api.result.TaskResult;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GunsTaskService extends IService<GunsTask> {

    //查询任务列表
    public PageResult<TaskResult> search(TaskParam param);
    //新增
    public int add(TaskParam param);
    //修改
    public int edit(TaskParam param);
    //查询详情
    public TaskResult info(TaskParam param);
    //删除
    public int delete(TaskParam param);

    /**-------------------------前台任务中心---------------------------*/

    public PageResult<TaskResult> uncompletedTasks(TaskParam param);

    public int complateTask(TaskParam param);
}
