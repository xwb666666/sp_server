package cn.stylefeng.guns.cloud.auth.modular.sso.mapper;

import cn.stylefeng.guns.cloud.auth.modular.sso.entity.AuthClient;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.params.AuthClientParam;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.result.AuthClientResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 应用表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-09-26
 */
public interface AuthClientMapper extends BaseMapper<AuthClient> {

    /**
     * 获取列表
     *
     * @author stylefeng
     * @Date 2019-09-26
     */
    List<AuthClientResult> customList(@Param("paramCondition") AuthClientParam paramCondition);

    /**
     * 获取map列表
     *
     * @author stylefeng
     * @Date 2019-09-26
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") AuthClientParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-09-26
     */
    Page<AuthClientResult> customPageList(@Param("page") Page page, @Param("paramCondition") AuthClientParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author stylefeng
     * @Date 2019-09-26
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") AuthClientParam paramCondition);

    /**
     * 获取auth客户端列表
     *
     * @author fengshuonan
     * @Date 2019/12/4 21:03
     */
    List<Map<String, Object>> pageList(@Param("page") Page page, @Param("param") AuthClientParam param);

}
