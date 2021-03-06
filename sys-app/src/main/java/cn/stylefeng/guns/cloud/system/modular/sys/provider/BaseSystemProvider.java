package cn.stylefeng.guns.cloud.system.modular.sys.provider;

import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.system.api.BaseSystemApi;
import cn.stylefeng.guns.cloud.system.api.model.BaseUserInfo;
import cn.stylefeng.guns.cloud.system.api.model.UserInfo;
import cn.stylefeng.guns.cloud.system.api.model.WorkflowRoleInfo;
import cn.stylefeng.guns.cloud.system.api.model.req.UserByRoleReq;
import cn.stylefeng.guns.cloud.system.api.model.req.UserListReq;
import cn.stylefeng.guns.cloud.system.core.tenant.exception.TenantException;
import cn.stylefeng.guns.cloud.system.core.tenant.exception.enums.TenantExceptionEnum;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntUser;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntUserAccount;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntUserDept;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntUserAccountService;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntUserDeptService;
import cn.stylefeng.guns.cloud.system.modular.ent.service.EntUserService;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysRole;
import cn.stylefeng.guns.cloud.system.modular.sys.factory.UserInfoFactory;
import cn.stylefeng.guns.cloud.system.modular.sys.mapper.SysRoleMapper;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.RoleInfo;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysPermissionService;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysRoleService;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysUserRoleService;
import cn.stylefeng.guns.cloud.system.modular.tenant.entity.TenantInfo;
import cn.stylefeng.guns.cloud.system.modular.tenant.service.TenantInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static cn.stylefeng.guns.cloud.system.core.error.SysExceptionEnum.USER_NOT_FOUND;

/**
 * ???????????????????????????
 *
 * @author fengshuonan
 * @date 2019-09-11-16:16
 */
@RestController
@Slf4j
public class BaseSystemProvider implements BaseSystemApi {

    @Autowired
    private EntUserService entUserInfoService;

    @Autowired
    private EntUserAccountService entUserAccountService;

    @Autowired
    private EntUserDeptService entUserDeptService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private TenantInfoService tenantInfoService;

    @Override
    public BaseUserInfo getUserByAccount(@RequestParam(value = "account") String account) {

        // 1. ??????????????????
        EntUserAccount userAccount = entUserAccountService.getAccountInfoByAccount(account);
        if (userAccount == null) {
            return null;
        }

        // 2. ????????????????????????
        Long infoId = userAccount.getUserId();
        EntUser userInfo = entUserInfoService.getById(infoId);
        if (userInfo == null) {
            log.error("????????????????????????????????????infoId={}", infoId);
            return null;
        }

        // 3. ?????????????????????????????????
        EntUserDept entUserDeptDuty = entUserDeptService.getUserDefaultDept(infoId);
        if (entUserDeptDuty == null) {
            log.error("?????????????????????????????????????????????infoId={}", infoId);
        }

        // 4. ??????????????????
        List<RoleInfo> userRoleInfo = sysUserRoleService.getUserRoleInfo(userAccount.getAccountId());
        if (userRoleInfo == null || userRoleInfo.size() == 0) {
            log.error("??????????????????????????????infoId={}", infoId);
        }

        // 5. ??????????????????
        Set<String> resourcesUrls = sysPermissionService.getUserResources(userRoleInfo);
        if (resourcesUrls == null || resourcesUrls.size() == 0) {
            log.error("??????????????????????????????infoId={}", infoId);
        }

        // 6. ??????????????????
        return UserInfoFactory.createUserInfo(userAccount, userInfo, entUserDeptDuty, userRoleInfo, resourcesUrls);
    }

    @Override
    public String getNameByAccountId(Long accountId) {

        //????????????id????????????id
        EntUserAccount entUserAccount = entUserAccountService.getById(accountId);
        if (entUserAccount == null) {
            throw new ServiceException(USER_NOT_FOUND);
        }

        //????????????id????????????
        EntUser entUser = entUserInfoService.getById(entUserAccount.getUserId());
        if (entUser == null) {
            throw new ServiceException(USER_NOT_FOUND);
        }

        return entUser.getName();
    }

    @Override
    public String getNameByUserId(Long userId) {

        EntUser entUser = entUserInfoService.getById(userId);
        if (entUser == null) {
            throw new ServiceException(USER_NOT_FOUND);
        }

        return entUser.getName();
    }

    @Override
    public List<WorkflowRoleInfo> getRoleList() {

        List<WorkflowRoleInfo> workflowRoleInfos = new ArrayList<>();

        List<SysRole> sysRoles = sysRoleService.list(
                new QueryWrapper<SysRole>().select("role_id as roleId, role_code as roleCode, role_name as roleName"));

        if (sysRoles.size() > 0) {
            for (SysRole sysRole : sysRoles) {
                WorkflowRoleInfo workflowRoleInfo = new WorkflowRoleInfo();
                workflowRoleInfo.setRoleId(sysRole.getRoleId());
                workflowRoleInfo.setRoleName(sysRole.getRoleName());
                workflowRoleInfo.setRoleCode(sysRole.getRoleCode());
                workflowRoleInfos.add(workflowRoleInfo);
            }
        }

        return workflowRoleInfos;
    }

    @Override
    public List<UserInfo> getUserListByRoleId(Long roleId) {
        return sysRoleMapper.getUsersByRoleId(roleId);
    }

    @Override
    public Page<UserInfo> getUsersByRoleIdByPage(@RequestBody UserByRoleReq userByRoleReq) {
        return sysRoleMapper.getUsersByRoleIdByPage(userByRoleReq.getPage(), userByRoleReq.getRoleId());
    }

    @Override
    public List<UserInfo> getUserListByUserIdsOrRoleIds(@RequestBody UserListReq userListReq) {
        return sysRoleMapper.getUsersByUserIdsOrRoleIds(userListReq.getAccountIds(), userListReq.getRoleIds());
    }

    @Override
    public String getDbNameByTenantCode(String tenantCode) {
        TenantInfo tenantInfo = tenantInfoService.getByCode(tenantCode);
        if (tenantInfo != null) {
            return tenantInfo.getDbName();
        } else {
            throw new TenantException(TenantExceptionEnum.CNAT_FIND_TENANT_ERROR);
        }
    }

}
