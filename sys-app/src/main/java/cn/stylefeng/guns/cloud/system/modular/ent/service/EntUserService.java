package cn.stylefeng.guns.cloud.system.modular.ent.service;

import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntUser;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.*;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntUserQueryResult;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntUserResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntUserService extends IService<EntUser> {

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void add(EntUserParam param);

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void delete(EntUserParam param);

    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void update(EntUserParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    PageResult<EntUserQueryResult> findPageBySpec(EntUserQueryParam param);

    /**
     * 获取用户在字典表对应的信息
     *
     * @author stylefeng
     * @date 2019/10/14
     */
    List<Map<String, Object>> queryInfoByDictType(String dictTypeCode);

    /**
     * 获取人员详情
     *
     * @author stylefeng
     * @date 2019/10/15
     */
    EntUserResult detail(Long userId);

    /**
     * 改变人员的状态
     *
     * @author stylefeng
     * @date 2019/10/15
     */
    void changeStatus(Long userId, StatusEnum toEnum);

    /**
     * 获取公司下拉列表
     *
     * @author stylefeng
     * @date 2019/10/27
     */
    List<Map<String, Object>> getCompanySelect();

    /**
     * 获取部门下拉列表
     *
     * @author stylefeng
     * @date 2019/10/27
     */
    List<Map<String, Object>> getDeptSelect(Long companyId);

    /**
     * 获取职务下拉列表
     *
     * @author stylefeng
     * @date 2019/10/27
     */
    List<Map<String, Object>> getDutySelect();

    /**
     * 获取所有的角色信息
     *
     * @param userId
     * @author stylefeng
     * @date 2019/10/27
     */
    List<Map<String, Object>> getRoles(Long userId);

    /**
     * 保存用户的角色信息
     *
     * @author stylefeng
     * @date 2019/10/28
     */
    void saveRoles(EntUserRoleParam param);

    /**
     * 获取当前登录人的信息
     *
     * @return
     * @author stylefeng
     * @date 2019/10/30
     */
    Map<String, Object> getCurrentUser();

    /**
     * 保存当前登录人信息
     *
     * @param
     * @author stylefeng
     * @date 2019/10/30
     */
    void saveCurrentUser(EntCurrentParam param);

    /**
     * 修改密码
     *
     * @author stylefeng
     * @date 2019/10/30
     */
    void updatePassword(EntPasswordParam passwordParam);

    /**
     * 重置密码
     *
     * @author stylefeng
     * @date 2019/10/30
     */
    String resetPassword(Long uerId);
}
