package cn.stylefeng.guns.cloud.system.modular.ent.service;

import cn.stylefeng.guns.cloud.model.enums.StatusEnum;
import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntDuty;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntDutyParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntDutyResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职务管理 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntDutyService extends IService<EntDuty> {

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void add(EntDutyParam param);

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void delete(EntDutyParam param);

    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void update(EntDutyParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    PageResult<EntDutyResult> findPageBySpec(String dutyName, String dutyCode);

    /**
     * 职务启用禁用
     *
     * @author stylefeng
     * @date 2019/10/13
     */
    void changeStatus(Long dutyId, StatusEnum toEnum);

    /**
     * 职务不分页列表
     *
     * @author stylefeng
     * @date 2019/10/14
     */
    List<Map<String, Object>> queryList();
}
