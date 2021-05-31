package cn.stylefeng.guns.cloud.system.modular.ent.service;

import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntCompApp;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntCompAppParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntCompAppResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 企业应用配置 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntCompAppService extends IService<EntCompApp> {

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void add(EntCompAppParam param);

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void delete(EntCompAppParam param);

    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void update(EntCompAppParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    EntCompAppResult findBySpec(EntCompAppParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    List<EntCompAppResult> findListBySpec(EntCompAppParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    PageResult<EntCompAppResult> findPageBySpec(EntCompAppParam param);

}
