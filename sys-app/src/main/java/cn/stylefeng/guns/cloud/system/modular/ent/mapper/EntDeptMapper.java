package cn.stylefeng.guns.cloud.system.modular.ent.mapper;

import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntDept;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntDeptParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntDeptResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntDeptMapper extends BaseMapper<EntDept> {

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    Page<EntDeptResult> customPageList(@Param("page") Page page, @Param("paramCondition") EntDeptParam paramCondition);

    /**
     * 获取部门树列表
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    List<Map<String, Object>> queryDeptTree(@Param("companyId") Long companyId,
                                            @Param("nodeType") Integer nodeType);
}
