package cn.stylefeng.guns.cloud.auth.modular.sso.mapper;

import cn.stylefeng.guns.cloud.auth.modular.sso.entity.AuthCertAccount;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.params.AuthCertAccountParam;
import cn.stylefeng.guns.cloud.auth.modular.sso.model.result.AuthCertAccountResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 账号二次认证表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-09-26
 */
public interface AuthCertAccountMapper extends BaseMapper<AuthCertAccount> {

    /**
     * 获取列表
     *
     * @author stylefeng
     * @Date 2019-09-26
     */
    List<AuthCertAccountResult> customList(@Param("paramCondition") AuthCertAccountParam paramCondition);

    /**
     * 获取map列表
     *
     * @author stylefeng
     * @Date 2019-09-26
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") AuthCertAccountParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-09-26
     */
    Page<AuthCertAccountResult> customPageList(@Param("page") Page page, @Param("paramCondition") AuthCertAccountParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author stylefeng
     * @Date 2019-09-26
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") AuthCertAccountParam paramCondition);

}
