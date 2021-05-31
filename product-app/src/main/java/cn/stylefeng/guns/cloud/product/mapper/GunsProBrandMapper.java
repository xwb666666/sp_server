package cn.stylefeng.guns.cloud.product.mapper;

import cn.stylefeng.guns.cloud.api.product.entity.GunsProBrand;
import cn.stylefeng.guns.cloud.api.product.model.params.GunsProBrandParam;
import cn.stylefeng.guns.cloud.api.product.model.result.GunsProBrandResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 品牌表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2021-03-23
 */
public interface GunsProBrandMapper extends BaseMapper<GunsProBrand> {

    /**
     * 获取列表
     *
     * @author stylefeng
     * @Date 2021-03-23
     */
    List<GunsProBrandResult> customList(@Param("paramCondition") GunsProBrandParam paramCondition);

    /**
     * 获取map列表
     *
     * @author stylefeng
     * @Date 2021-03-23
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") GunsProBrandParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author stylefeng
     * @Date 2021-03-23
     */
    Page<GunsProBrandResult> customPageList(@Param("page") Page page, @Param("paramCondition") GunsProBrandParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author stylefeng
     * @Date 2021-03-23
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") GunsProBrandParam paramCondition);

}
