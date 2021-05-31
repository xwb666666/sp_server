package cn.stylefeng.guns.cloud.system.modular.sys.mapper;

import cn.stylefeng.guns.cloud.system.modular.sys.entity.SysMenu;
import cn.stylefeng.guns.cloud.system.modular.sys.model.params.MenuCondition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author fengshuonan
 * @since 2019-09-10
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 获取分页map列表
     *
     * @author fengshuonan
     * @Date 2019-09-10
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") MenuCondition paramCondition);

    /**
     * 获取相同的菜单
     *
     * @author fengshuonan
     * @Date 2018/2/27 18:22
     */
    List<SysMenu> getSameMenu(SysMenu menu);

    /**
     * 添加时
     *
     * @author fengshuonan
     * @Date 2018/2/27 18:22
     */
    List<SysMenu> getSameMenuWhenAdd(SysMenu menu);

    /**
     * 根据应用id获取菜单资源集合
     *
     * @author stylefeng
     * @date 2019/11/3
     */
    Set<String> getMenuResCodesByAppId(@Param("appId") Long appId);
}
