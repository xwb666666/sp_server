package cn.stylefeng.guns.cloud.member.controller;

import cn.stylefeng.guns.cloud.api.member.model.validated.AddGroup;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.member.model.GunsTask;
import cn.stylefeng.guns.cloud.member.model.api.param.TaskParam;
import cn.stylefeng.guns.cloud.member.model.api.result.TaskResult;
import cn.stylefeng.guns.cloud.member.service.GunsTaskService;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
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
@RequestMapping("/task")
@Api(tags = "任务")
public class GunsTaskController {

    @Resource
    private GunsTaskService taskService;

    @PostMapping("save")
    @ApiOperation("添加")
    public ResponseData save(@Validated({AddGroup.class}) @RequestBody TaskParam param) {
        GunsTask gunsTask = new GunsTask();
        ToolUtil.copyProperties(param, gunsTask);
        taskService.save(gunsTask);
        return ResponseData.success(true);

    }

    @PostMapping("delete")
    @ApiOperation("删除")
    public ResponseData delete(@RequestBody TaskParam param) {

        boolean remove = taskService.remove(new QueryWrapper<GunsTask>().eq("id", param.getId()));
        if (remove) {
            return ResponseData.success();
        } else {
            return ResponseData.error("删除失败");
        }
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public ResponseData update( @RequestBody @Validated TaskParam param) {
        GunsTask gunsTask = new GunsTask();
        ToolUtil.copyProperties(param, gunsTask);
        taskService.save(gunsTask);
        Boolean updateParam = taskService.update(gunsTask, new QueryWrapper<GunsTask>().eq("id",param.getId()));
        if (updateParam) {
            return ResponseData.success();
        }else {
            return ResponseData.error("修改失败");
        }
    }

    @GetMapping("/info")
    @ApiOperation("数据详情")
    public ResponseData info(Long id){
        if(id==null){
            throw new ServiceException(500,"请求参数不能为空！");
        }
        GunsTask task = taskService.getById(id);
        TaskResult taskParam = new TaskResult();
        ToolUtil.copyProperties(task, taskParam);
        return ResponseData.success(taskParam);
    }

    @GetMapping("/list")
    @ApiOperation("分页查询所有")
    public ResponseData getPageList(TaskParam param) {
//        Page<GunsTaskResult> pageList = taskService.getPageList(param);
        return ResponseData.success(null);
    }

    @GetMapping("/selectList")
    @ApiOperation("查询")
    public ResponseData selectList(@Validated TaskParam param){
        IPage<GunsTask> page = new Page<>();
        taskService.getBaseMapper().selectPage(page, new QueryWrapper<GunsTask>().eq("", null));
        return ResponseData.success(null);
    }

}