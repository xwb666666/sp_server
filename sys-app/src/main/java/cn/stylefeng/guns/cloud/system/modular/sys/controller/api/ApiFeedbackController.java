package cn.stylefeng.guns.cloud.system.modular.sys.controller.api;


import cn.hutool.core.date.DateTime;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.libs.utils.ToolUtil;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysFeedback;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysFeedbackParam;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiResource(name = "帮助与反馈", path = "api/feedback")
@Api(tags = "帮助与反馈")
public class ApiFeedbackController {
    @Autowired
    private SysFeedbackService feedbackService;


    @PostResource(name = "帮助与反馈", path = "/feedback")
    @ApiOperation("帮助与反馈")
    public ResponseData feedback(@RequestBody SysFeedbackParam param){
        if (param.getMemberId()==null){
            throw new ServiceException(500,"用户不能为空");
        }
        SysFeedback sysFeedback=new SysFeedback();
        ToolUtil.copyProperties(param,sysFeedback);
        sysFeedback.setCreateTime(new DateTime());
        feedbackService.save(sysFeedback);
        return ResponseData.success();
    }


}
