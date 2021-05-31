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
package cn.stylefeng.guns.cloud.auth.api.model;

import cn.hutool.core.lang.Dict;
import lombok.Data;

import java.util.Set;

/**
 * 当前用户的登录信息
 *
 * @author fengshuonan
 * @Date 2018/8/22 下午6:19
 */
@SuppressWarnings("ALL")
@Data
public class LoginUser {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账号id
     */
    private Long accountId;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 角色id集合
     */
    private Set<Long> roleIds;

    /**
     * 角色名称集合(角色code，英文名称)
     */
    private Set<String> roleNames;

    /**
     * 可用资源集合
     */
    private Set<String> resourceUrls;

    /**
     * 租户信息
     */
    private Dict tenants;

}
