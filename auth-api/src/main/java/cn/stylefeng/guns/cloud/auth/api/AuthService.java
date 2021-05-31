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
package cn.stylefeng.guns.cloud.auth.api;

import cn.stylefeng.guns.cloud.auth.api.model.LoginUser;
import cn.stylefeng.guns.cloud.auth.api.model.TokenReq;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Auth服务对外接口
 *
 * @author fengshuonan
 * @date 2018-02-07 9:57
 */
@RequestMapping("/authService")
public interface AuthService {

    /**
     * 通过token获取用户信息
     *
     * @author fengshuonan
     * @Date 2018/1/12 16:32
     */
    @RequestMapping(value = "/getLoginUserByToken", method = RequestMethod.POST)
    LoginUser getLoginUserByToken(@RequestBody TokenReq tokenReq);

}
