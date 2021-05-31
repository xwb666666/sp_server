package cn.stylefeng.guns.cloud.system.modular.ent.mapper;

import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntDuty;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntDutyResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 职务管理 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntDutyMapper extends BaseMapper<EntDuty> {

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    Page<EntDutyResult> customPageList(@Param("page") Page page,
                                       @Param("dutyName") String dutyName,
                                       @Param("dutyCode") String dutyCode);

}
