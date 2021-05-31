package cn.stylefeng.guns.cloud.member.controller.api;

import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.model.GunsTask;
import cn.stylefeng.guns.cloud.member.model.api.param.TaskParam;
import cn.stylefeng.guns.cloud.member.model.api.result.TaskResult;
import cn.stylefeng.guns.cloud.member.service.GunsTaskService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/api/task")
@Api(tags = "Api-任务")
public class ApiGunsTaskController {

    @Resource
    private GunsTaskService taskService;

    @GetMapping("/list")
    @ApiOperation("分页查询所有")
    public ResponseData getPageList(TaskParam param) {
        PageResult<TaskResult> pageList = new PageResult<>();
        pageList = taskService.uncompletedTasks(param);
        return ResponseData.success(pageList);
    }

    @GetMapping("/selectList")
    @ApiOperation("查询")
    public ResponseData selectList(@Validated TaskParam param){
        IPage<GunsTask> page = new Page<>();
        QueryWrapper<GunsTask> queryWrapper = new QueryWrapper<GunsTask>();
        if(param.getTaskName() != null)
            queryWrapper.eq("name", param.getTaskName());

        taskService.getBaseMapper().selectPage(page, queryWrapper);
        return ResponseData.success(null);
    }

}