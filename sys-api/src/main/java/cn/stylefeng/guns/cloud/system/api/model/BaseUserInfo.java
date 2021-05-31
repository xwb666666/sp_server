package cn.stylefeng.guns.cloud.system.api.model;

import cn.hutool.core.lang.Dict;
import lombok.Data;

import java.util.Set;

/**
 * 基础用户信息
 *
 * @author fengshuonan
 * @date 2019-09-11-16:49
 */
@Data
public class BaseUserInfo {

    /**
     * 账号id
     */
    private Long accountId;

    /**
     * 用户名称
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
     * 用户id
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 角色id集合
     */
    private Set<Long> roleIds;

    /**
     * 角色名称集合
     */
    private Set<String> roleNames;

    /**
     * 可用资源集合
     */
    private Set<String> resourceUrls;

    /**
     * 默认部门id
     */
    private Long deptId;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码颜值
     */
    private String salt;

    /**
     * 租户信息
     */
    private Dict tenants;

}
