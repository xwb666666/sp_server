package cn.stylefeng.guns.cloud.system.modular.sys.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.auth.api.model.LoginUser;
import cn.stylefeng.guns.cloud.libs.context.auth.LoginContext;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.exp.RequestEmptyException;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;
import cn.stylefeng.guns.cloud.model.tree.TreeNode;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.system.core.log.anno.BusinessLog;
import cn.stylefeng.guns.cloud.system.core.log.enums.LogAnnotionOpTypeEnum;
import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysMenu;
import cn.stylefeng.guns.cloud.system.modular.sys.model.MenuNode;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.MenuCondition;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.SysMenuParam;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysAppService;
import cn.stylefeng.guns.cloud.system.modular.sys.service.SysMenuService;
import cn.stylefeng.guns.cloud.system.modular.sys.wrapper.MenuWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 菜单控制器
 *
 * @author fengshuonan
 * @Date 2019/9/26 22:49
 */
@RestController
@ApiResource(name = "菜单管理接口", path = "/menu")
@Api(tags = "菜单管理接口")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysAppService appService;

    /**
     * 获取左侧菜单列表
     *
     * @author fengshuonan
     * @Date 2018/2/28 16:45
     */
    @GetResource(name = "获取左侧菜单列表", path = "/getLeftMenuList")
    @ApiOperation("获取左侧菜单列表")
    public ResponseData getLeftMenuList(@RequestParam(value = "appId") String appId) {
        if (ObjectUtil.isEmpty(appId) || appId.equals("undefined")) {
            throw new RequestEmptyException("appId为空");
        }

        LoginUser loginUser = LoginContext.me().getLoginUser();
        Long accountId = loginUser.getAccountId();

        List<MenuNode> leftMenuList = this.sysMenuService.getLeftMenuList(Long.valueOf(appId), accountId);
        return ResponseData.success(leftMenuList);
    }

    /**
     * 新增菜单
     *
     * @author fengshuonan
     * @Date 2018/2/9 15:12
     */
    @PostResource(name = "新增菜单", path = "/add")
    @ApiOperation("新增菜单")
    @BusinessLog(title = "新增菜单", opType = LogAnnotionOpTypeEnum.ADD)
    public ResponseData addMenu(@RequestBody SysMenuParam sysMenuParam) {
        this.sysMenuService.addMenu(sysMenuParam);
        return ResponseData.success();
    }

    /**
     * 编辑菜单
     *
     * @author fengshuonan
     * @Date 2018/2/9 16:39
     */
    @PostResource(name = "编辑菜单", path = "/update")
    @ApiOperation("编辑菜单")
    @BusinessLog(title = "编辑菜单", opType = LogAnnotionOpTypeEnum.EDIT)
    public ResponseData editMenu(@RequestBody SysMenuParam sysMenuParam) {
        this.sysMenuService.updateMenu(sysMenuParam);
        return ResponseData.success();
    }

    /**
     * 删除菜单
     *
     * @author fengshuonan
     * @Date 2018/2/9 16:40
     */
    @PostResource(name = "删除菜单", path = "/delete")
    @ApiOperation("删除菜单")
    public ResponseData deleteMenu(@RequestBody Map<String, Long[]> param) {
        Long[] menuIds = param.get("menuIds");
        if (ObjectUtil.isEmpty(menuIds)) {
            throw new ServiceException(400, "菜单列表为空!");
        }
        this.sysMenuService.deleteMenuBatch(menuIds);
        return ResponseData.success();
    }

    /**
     * 启用菜单
     *
     * @author fengshuonan
     * @Date 2018/2/9 16:40
     */
    @GetResource(name = "启用禁用菜单", path = "/changeStatus")
    @ApiOperation("启用禁用菜单")
    public ResponseData enableMenu(@RequestParam(value = "menuId") Long menuId, @RequestParam("status") Integer status) {
        if (menuId == null || status == null) {
            throw new RequestEmptyException("请求菜单id或状态为空！");
        }

        this.sysMenuService.changeStatus(menuId, StatusEnum.toEnum(status));
        return ResponseData.success();
    }

    /**
     * 获取菜单列表
     *
     * @author fengshuonan
     * @Date 2018/2/9 16:40
     */
    @PostResource(name = "获取菜单列表", path = "/list")
    @ApiOperation("获取菜单列表")
    public ResponseData listMenu(@RequestBody MenuCondition menuCondition) {

        IPage<Map<String, Object>> menuList = this.sysMenuService.getMenuList(menuCondition);

        //包装菜单列表结果
        List<Map<String, Object>> records = menuList.getRecords();
        Object warp = new MenuWrapper(records).wrap();
        menuList.setRecords((List<Map<String, Object>>) warp);
        return ResponseData.success(menuList);
    }

    /**
     * 获取父级菜单列表
     *
     * @author fengshuonan
     * @Date 2018/2/26 15:01
     */
    @GetResource(name = "获取父级菜单列表", path = "/getSelectMenuTreeList")
    @ApiOperation("获取父级菜单列表")
    public ResponseData getSelectMenuTreeList(@RequestParam(value = "appId") Long appId) {
        if (appId == null) {
            throw new RequestEmptyException("应用id为空！");
        }
        List<TreeNode> appList = this.sysMenuService.getSelectMenuTreeList(appId);
        return ResponseData.success(appList);
    }

    /**
     * 获取菜单详情
     *
     * @author fengshuonan
     * @Date 2018/2/27 15:39
     */
    @GetResource(name = "获取菜单详情", path = "/getMenuDetail")
    @ApiOperation("获取菜单详情")
    public ResponseData getMenuDetail(@RequestParam(value = "menuId") Long menuId) {
        if (menuId == null) {
            throw new RequestEmptyException("菜单id为空！");
        }
        SysMenu menu = this.sysMenuService.getById(menuId);
        return ResponseData.success(menu);
    }

}

