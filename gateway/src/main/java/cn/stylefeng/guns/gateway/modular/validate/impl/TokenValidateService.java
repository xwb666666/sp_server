/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.gateway.modular.validate.impl;

import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.cloud.auth.api.model.LoginUser;
import cn.stylefeng.guns.cloud.auth.api.model.TokenReq;
import cn.stylefeng.guns.cloud.model.resource.ResourceDefinition;
import cn.stylefeng.guns.cloud.system.api.model.req.ResourceUrlReq;
import cn.stylefeng.guns.gateway.core.exception.GunsGatewayException;
import cn.stylefeng.guns.gateway.core.exception.enums.GatewayExceptionEnum;
import cn.stylefeng.guns.gateway.core.jwt.JwtTokenUtil;
import cn.stylefeng.guns.gateway.modular.cache.AuthServiceCache;
import cn.stylefeng.guns.gateway.modular.cache.ResourceServiceCache;
import cn.stylefeng.guns.gateway.modular.validate.BaseValidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 纯token验证鉴权服
 *
 * @author fengshuonan
 * @date 2018-08-13 21:52
 */
@Service
@Slf4j
public class TokenValidateService extends BaseValidateService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ResourceServiceCache resourceServiceCache;

    @Autowired
    private AuthServiceCache authServiceCache;

    @Override
    public boolean validate(String token, String requestPath) {

        ResourceUrlReq resourceUrlReq = new ResourceUrlReq();
        resourceUrlReq.setUrl(requestPath);

        ResourceDefinition currentResource = resourceServiceCache.getResourceByUrl(resourceUrlReq);

        //找不到资源，不允许访问
        if (currentResource == null) {
            log.warn("当前请求url：{}在资源库中不存在", requestPath);
            throw new GunsGatewayException(GatewayExceptionEnum.CACHE_URL_NULL);

        } else {
            Boolean requiredLogin = currentResource.getRequiredLogin();

            //如果需要登录
            if (requiredLogin) {

                //如果没有token
                if (StrUtil.isEmpty(token)) {
                    throw new GunsGatewayException(GatewayExceptionEnum.TOKEN_EMPTY);
                }

                //判断token是否正确
                Boolean tokenFlag = jwtTokenUtil.checkToken(token);
                if (!tokenFlag) {
                    throw new GunsGatewayException(GatewayExceptionEnum.TOKEN_ERROR);
                }

                //获取当前登录用户
                TokenReq tokenReq = new TokenReq();
                tokenReq.setToken(token);
                LoginUser loginUserByToken = authServiceCache.getLoginUserByToken(tokenReq);
                if (loginUserByToken == null) {
                    log.warn("当前登录用户在redis缓存中不存在：{}", tokenReq);
                    throw new GunsGatewayException(GatewayExceptionEnum.TOKEN_EXPIRED);
                }

                return true;
            } else {
                return true;
            }
        }
    }
}
