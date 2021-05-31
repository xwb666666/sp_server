package cn.stylefeng.guns.cloud.system.modular.ent.mapper;

import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntUserDept;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntUserDeptParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntUserDeptResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户部门关联表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntUserDeptMapper extends BaseMapper<EntUserDept> {

    /**
     * 获取列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    List<EntUserDeptResult> customList(@Param("paramCondition") EntUserDeptParam paramCondition);

    /**
     * 获取map列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") EntUserDeptParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    Page<EntUserDeptResult> customPageList(@Param("page") Page page, @Param("paramCondition") EntUserDeptParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") EntUserDeptParam paramCondition);

}
