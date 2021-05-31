package cn.stylefeng.guns.cloud.member.service.Impl;

import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMemIntegralDetail;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemParamResult;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.model.GunsTask;
import cn.stylefeng.guns.cloud.member.mapper.GunsTaskMapper;
import cn.stylefeng.guns.cloud.member.model.GunsTaskMember;
import cn.stylefeng.guns.cloud.member.model.api.param.TaskParam;
import cn.stylefeng.guns.cloud.member.model.api.result.TaskMemberResult;
import cn.stylefeng.guns.cloud.member.model.api.result.TaskResult;
import cn.stylefeng.guns.cloud.member.service.GunsMemIntegralDetailService;
import cn.stylefeng.guns.cloud.member.service.GunsMemService;
import cn.stylefeng.guns.cloud.member.service.GunsTaskMemberService;
import cn.stylefeng.guns.cloud.member.service.GunsTaskService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GunsTaskServiceImpl extends ServiceImpl<GunsTaskMapper, GunsTask> implements GunsTaskService {

    @Resource
    private GunsTaskMemberService gunsTaskMemberService;

    @Resource
    private GunsMemService gunsMemService;

    @Resource
    private GunsMemIntegralDetailService gunsMemIntegralDetailService;

    @Override
    public PageResult<TaskResult> search(TaskParam param) {
        vaildParam(param);
        IPage<GunsTask> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());

        QueryWrapper<GunsTask> queryWrapper = new QueryWrapper<>();
        if (param.getStatus() != null )
            queryWrapper.eq("status", param.getStatus());
        if (param.getTaskName() != null )
            queryWrapper.like("name", param.getTaskName());

        page = baseMapper.selectPage(page, queryWrapper);
        PageResult<TaskResult> result = new PageResult<>();

        result.setPage(page.getCurrent());
        result.setPageSize(page.getSize());
        result.setTotalPage(page.getPages());
        result.setTotalRows(page.getTotal());

        ArrayList<Object> rows = new ArrayList<>();
        page.getRecords().forEach(item -> {
            TaskResult taskResult = new TaskResult();
            ToolUtil.copyProperties(item, taskResult);
            rows.add(taskResult);
        });
        return result;
    }

    @Override
    public int add(TaskParam param) {
        vaildParam(param);
        GunsTask gunsTask = new GunsTask();
        ToolUtil.copyProperties(param, gunsTask);
        return baseMapper.insert(gunsTask);
    }

    @Override
    public int edit(TaskParam param) {
        vaildParam(param);
        return 0;
    }

    @Override
    public TaskResult info(TaskParam param) {
        vaildParam(param);
        QueryWrapper<GunsTask> queryWrapper = new QueryWrapper<>();
        GunsTask gunsTask = baseMapper.selectOne(queryWrapper);
        TaskResult result = new TaskResult();
        ToolUtil.copyProperties(gunsTask, result);
        return result;
    }

    @Override
    public int delete(TaskParam param) {
        vaildParam(param);
        GunsTask gunsTask = new GunsTask();
        ToolUtil.copyProperties(param, gunsTask);
        return baseMapper.delete(new QueryWrapper<GunsTask>().eq("id", param.getId()));
    }



    @Override
    public PageResult<TaskResult> uncompletedTasks(TaskParam param) {
        vaildParam(param);
        IPage<TaskResult> page = new Page<>();
        page.setSize(param.getPageSize());
        page.setCurrent(param.getPage());

        QueryWrapper<GunsTask> queryWrapper = new QueryWrapper<>();
        if (param.getStatus() != null )
            queryWrapper.eq("status", param.getStatus());
        if (param.getTaskName() != null )
            queryWrapper.eq("name", param.getTaskName());
        if (param.getMemberId() != null )
            queryWrapper.eq("member_id", param.getMemberId());

        //过滤
        List<GunsTask> gunsTasks = baseMapper.selectAll();
        ArrayList<TaskResult> list = new ArrayList<>();
        gunsTasks.forEach(item -> {
            List<GunsTaskMember> memTaskList = item.getTaskMembers();
            boolean falg = false;
            for(GunsTaskMember memTask : memTaskList){
                if(param.getMemberId().equals(memTask.getMemberId())) {
                    falg = true;
                }
            }
            if(!falg){
                TaskResult taskResult = new TaskResult();
                ToolUtil.copyProperties(item, taskResult);
                list.add(taskResult);
            }
        });
        page.setRecords(list);
        PageResult<TaskResult> result = new PageResult<>();
        result.setRows(list);
        result.setPage(page.getCurrent());
        result.setTotalPage(page.getPages());
        result.setTotalRows(page.getTotal());
        result.setPageSize(param.getPageSize());
        return result;
    }

    /**
     * @description 完成任务
     * @params
     * @return
     */
    @Override
    public int complateTask(TaskParam param) {
        vaildParam(param);
        GunsMemParamResult member = gunsMemService.getById(param.getMemberId());
        if(member == null)
            throw new ServiceException(500, "没有该会员信息！");
        GunsTaskMember gunsTaskMember = new GunsTaskMember();
        DateTime dateTime = new DateTime();
        gunsTaskMember.setId(ToolUtil.getIdGenLong());
        gunsTaskMember.setMemberId(member.getId());
        gunsTaskMember.setTaskId(param.getId());
        gunsTaskMember.setCreateDate(dateTime);
        gunsTaskMember.setStatus(1);
        gunsTaskMemberService.getBaseMapper().insert(gunsTaskMember);

        //待完成，任务完成后增加会员积分
//        GunsMemIntegralDetail integralDetail = new GunsMemIntegralDetail();
//        integralDetail.setId(ToolUtil.getIdGenLong());
//        integralDetail.setMemberId(param.getMemberId());
//        integralDetail.setFerUser(member.getNickName());
//        integralDetail.setOperationType(param.getTaskType());
        /** TODO 金豆变化 查询推荐任务 **/
//        integralDetail.setChangeFer(gunsTaskMember.get);
        //当前金豆
//        integralDetail.setCurrFer();
        //金豆变化类型 0 金豆增加 1金豆减少
//        integralDetail.setFerChange(0);
//        integralDetail.setRemark(param.getMemo());
//        gunsMemIntegralDetailService.save();
        return 0;
    }


    private void vaildParam(TaskParam param) {
        if(param == null)
            throw new ServiceException(500, "请求参数不能为空！");
    }

}
