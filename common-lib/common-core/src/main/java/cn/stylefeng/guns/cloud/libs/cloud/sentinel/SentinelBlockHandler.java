package cn.stylefeng.guns.cloud.libs.cloud.sentinel;

import cn.stylefeng.guns.cloud.libs.cloud.sentinel.enums.BlockExceptionEnums;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * sentinel错误响应的格式化
 *
 * @author fengshuonan
 * @Date 2019/9/1 15:51
 */
public class SentinelBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        BlockExceptionEnums blockExceptionEnums = null;

        if (e instanceof FlowException) {

            //限流异常
            blockExceptionEnums = BlockExceptionEnums.FLOW_EXCEPTION;

        } else if (e instanceof DegradeException) {

            //降级异常
            blockExceptionEnums = BlockExceptionEnums.DEGRADE_EXCEPTION;

        } else if (e instanceof ParamFlowException) {

            //热点参数限流
            blockExceptionEnums = BlockExceptionEnums.PARAM_FLOW_EXCEPTION;

        } else if (e instanceof SystemBlockException) {

            //系统规则
            blockExceptionEnums = BlockExceptionEnums.SYSTEM_BLOCK_EXCEPTION;

        } else if (e instanceof AuthorityException) {

            //授权规则不通过
            blockExceptionEnums = BlockExceptionEnums.AUTHORITY_EXCEPTION;

        } else {

            //未知流控
            blockExceptionEnums = BlockExceptionEnums.FLOW_EXCEPTION;

        }

        // http状态码
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        httpServletResponse.setContentType("application/json;charset=utf-8");

        JSON.writeJSONString(httpServletResponse.getWriter(), blockExceptionEnums.getErrorResponseData());
    }

}