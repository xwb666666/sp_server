package cn.stylefeng.guns.cloud.system.modular.sys.controller;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ApiResource(name = "帮助与反馈", path = "/feedback")
@Api(tags = "帮助与反馈")
public class FeedBackController {

    @Autowired
    private SysFeedbackService feedbackService;


    @GetResource(name = "查看帮助与反馈", path = "/selectFeedback")
    @ApiOperation("查看帮助与反馈")
    public ResponseData selectFeedback(){
        return ResponseData.success(feedbackService.selectFeedback());

    }

    @PostResource(name = "处理帮助与反馈", path = "/disposeFeedback")
    @ApiOperation("处理帮助与反馈")
    public ResponseData disposeFeedback(@RequestBody Map<String,Integer> param){
       if (param.get("id")==null){
           throw new ServiceException(500,"id不能为空");
       }
       feedbackService.updateDisposeStatus(param.get("id"));
        return ResponseData.success();
    }

}
