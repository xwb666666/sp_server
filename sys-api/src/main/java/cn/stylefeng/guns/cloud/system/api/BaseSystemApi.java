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
package cn.stylefeng.guns.cloud.system.api;

import cn.stylefeng.guns.cloud.system.api.model.BaseUserInfo;
import cn.stylefeng.guns.cloud.system.api.model.UserInfo;
import cn.stylefeng.guns.cloud.system.api.model.WorkflowRoleInfo;
import cn.stylefeng.guns.cloud.system.api.model.req.UserByRoleReq;
import cn.stylefeng.guns.cloud.system.api.model.req.UserListReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 登录相关的api
 *
 * @author fengshuonan
 * @date 2018-02-07 9:57
 */
@RequestMapping("/baseSystem")
public interface BaseSystemApi {

    /**
     * 校验账号密码，获取用户
     *
     * @author fengshuonan
     * @Date 2019-09-11 15:46
     */
    @RequestMapping(value = "/getUserByAccount", method = RequestMethod.POST)
    BaseUserInfo getUserByAccount(@RequestParam(value = "account") String account);

    /**
     * 获取用户名称通过账号id
     *
     * @author fengshuonan
     * @Date 2019/12/1 12:51
     */
    @RequestMapping(value = "/getNameByAccountId", method = RequestMethod.POST)
    String getNameByAccountId(@RequestParam(value = "accountId") Long accountId);

    /**
     * 获取用户名称通过用户详情id
     *
     * @author fengshuonan
     * @Date 2019/12/1 12:51
     */
    @RequestMapping(value = "/getNameByUserId", method = RequestMethod.POST)
    String getNameByUserId(@RequestParam(value = "userId") Long userId);

    /**
     * 获取角色列表
     *
     * @author fengshuonan
     * @Date 2019/12/1 13:21
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    List<WorkflowRoleInfo> getRoleList();

    /**
     * 获取用户列表通过角色id
     *
     * @author fengshuonan
     * @Date 2019/12/1 13:22
     */
    @RequestMapping(value = "/getUserListByRoleId", method = RequestMethod.POST)
    List<UserInfo> getUserListByRoleId(@RequestParam(value = "roleId") Long roleId);

    /**
     * 获取用户列表通过角色id(带分页)
     *
     * @author fengshuonan
     * @Date 2019/12/1 13:22
     */
    @RequestMapping(value = "/getUsersByRoleIdByPage", method = RequestMethod.POST)
    Page<UserInfo> getUsersByRoleIdByPage(@RequestBody UserByRoleReq userByRoleReq);

    /**
     * 获取用户列表，通过用户id或角色id的集合
     *
     * @author fengshuonan
     * @Date 2019/12/1 16:21
     */
    @RequestMapping(value = "/getUserListByUserIdsOrRoleIds", method = RequestMethod.POST)
    List<UserInfo> getUserListByUserIdsOrRoleIds(@RequestBody UserListReq userListReq);

    /**
     * 获取数据库名称通过租户编码
     *
     * @author fengshuonan
     * @Date 2020/9/23 21:09
     */
    @RequestMapping(value = "/getDbNameByTenantCode", method = RequestMethod.POST)
    String getDbNameByTenantCode(@RequestBody String tenantCode);

}
