package cn.stylefeng.guns.cloud.system.modular.ent.service;

import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntDept;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntDeptParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntDeptResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntDeptService extends IService<EntDept> {

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void add(EntDeptParam param);

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void delete(EntDeptParam param);

    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void update(EntDeptParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    PageResult<EntDeptResult> findPageBySpec(EntDeptParam param);

    /**
     * 获取部门详情
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    EntDeptResult detail(Long deptId);

    /**
     * 部门禁用启用
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    void changeStatus(Long deptId, StatusEnum toEnum);

    /**
     * 获取部门树列表
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    List<Map<String, Object>> queryDeptTree(Long companyId);

}
