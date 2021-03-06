package cn.stylefeng.guns.gateway.core.exception.aop;

import cn.stylefeng.guns.cloud.model.exp.ApiServiceException;
import cn.stylefeng.guns.cloud.model.web.response.ErrorResponseData;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.gateway.core.exception.GunsGatewayException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

import static cn.stylefeng.guns.gateway.core.exception.enums.GatewayExceptionEnum.GATEWAY_ERROR;

/**
 * 全局错误拦截器
 *
 * @author fengshuonan
 * @Date 2019/5/12 21:15
 */
@Order(-200)
@Slf4j
public class GunsGatewayExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {

        log.error("网关异常！", throwable);

        //定义返回结果
        ResponseData responseData;

        //设置response的header
        ServerHttpResponse response = exchange.getResponse();

        //如果是Guns网关异常
        if (throwable instanceof GunsGatewayException) {
            GunsGatewayException gunsGatewayException = (GunsGatewayException) throwable;

            Integer code = gunsGatewayException.getCode();
            String errorMessage = gunsGatewayException.getErrorMessage();

            responseData = new ErrorResponseData(code, errorMessage);

            //设置响应状态码500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

        } else if (throwable instanceof ApiServiceException) {

            //如果是接口调用异常
            ApiServiceException apiServiceException = (ApiServiceException) throwable;

            Integer code = apiServiceException.getCode();
            String errorMessage = apiServiceException.getErrorMessage();

            responseData = new ErrorResponseData(code, errorMessage);

            //设置响应状态码500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

        } else {

            //如果是运行时异常，不可知的异常
            responseData = ResponseData.error(GATEWAY_ERROR.getMessage());

            //设置响应状态码500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");

        //渲染响应信息
        String resultString = JSON.toJSONString(responseData);
        byte[] bytes = (resultString).getBytes(StandardCharsets.UTF_8);
        DataBuffer wrap = exchange.getResponse().bufferFactory().wrap(bytes);

        return exchange.getResponse().writeWith(Flux.just(wrap));
    }
}