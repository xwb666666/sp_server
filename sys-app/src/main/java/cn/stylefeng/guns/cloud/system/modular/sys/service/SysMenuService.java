package cn.stylefeng.guns.cloud.system.modular.sys.service;

import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.tree.TreeNode;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysMenu;
import cn.stylefeng.guns.cloud.system.modular.sys.model.MenuNode;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.MenuCondition;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysMenuParam;
import cn.stylefeng.guns.cloud.system.modular.sys.model.result.SysMenuResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 新增
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void add(SysMenuParam param);

    /**
     * 删除
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void delete(SysMenuParam param);

    /**
     * 更新
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    void update(SysMenuParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    SysMenuResult findBySpec(SysMenuParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    List<SysMenuResult> findListBySpec(SysMenuParam param);

    /**
     * 获取当前用户页面左侧菜单列表
     *
     * @author fengshuonan
     * @Date 2018/2/28 10:51
     */
    List<MenuNode> getLeftMenuList(Long appId, Long accountId);

    /**
     * 检查是否有同名或者code相同的菜单
     *
     * @author fengshuonan
     * @Date 2018/2/9 15:48
     */
    boolean haveSameMenu(SysMenu menu);

    /**
     * 添加菜单
     *
     * @author fengshuonan
     * @Date 2018/2/9 15:17
     */
    void addMenu(SysMenuParam sysMenuParam);

    /**
     * 修改菜单
     *
     * @author fengshuonan
     * @Date 2018/2/9 15:48
     */
    void updateMenu(SysMenuParam sysMenuParam);

    /**
     * 删除菜单
     *
     * @author fengshuonan
     * @Date 2018/2/9 15:51
     */
    void deleteMenu(Long menuId);

    /**
     * 删除菜单
     *
     * @author fengshuonan
     * @Date 2018/2/9 15:51
     */
    void deleteMenuBatch(Long[] menuIds);

    /**
     * 获取菜单列表
     *
     * @author fengshuonan
     * @Date 2018/2/9 16:43
     */
    IPage<Map<String, Object>> getMenuList(MenuCondition menuCondition);

    /**
     * 获取某个应用的下拉菜单树列表
     *
     * @author fengshuonan
     * @Date 2018/2/9 17:48
     */
    List<TreeNode> getSelectMenuTreeList(Long appId);

    /**
     * 修改状态
     *
     * @author fengshuonan
     * @Date 2018/3/1 15:10
     */
    void changeStatus(Long menuId, StatusEnum statusEnum);

    /**
     * 根据appId获取菜单集合
     *
     * @author stylefeng
     * @date 2019/11/3
     */
    Set<String> getMenuResCodesByAppId(Long appId);
}
