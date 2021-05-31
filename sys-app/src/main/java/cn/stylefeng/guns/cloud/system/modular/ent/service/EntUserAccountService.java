package cn.stylefeng.guns.cloud.system.modular.ent.service;

import cn.stylefeng.guns.cloud.model.page.PageResult;
import cn.stylefeng.guns.cloud.system.modular.ent.entity.EntUserAccount;
import cn.stylefeng.guns.cloud.system.modular.ent.model.params.EntUserAccountParam;
import cn.stylefeng.guns.cloud.system.modular.ent.model.result.EntUserAccountResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 登录账号 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-10-10
 */
public interface EntUserAccountService extends IService<EntUserAccount> {

    /**
     * 新增
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void add(EntUserAccountParam param);

    /**
     * 删除
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void delete(EntUserAccountParam param);

    /**
     * 更新
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    void update(EntUserAccountParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    EntUserAccountResult findBySpec(EntUserAccountParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    List<EntUserAccountResult> findListBySpec(EntUserAccountParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author stylefeng
     * @Date 2019-10-10
     */
    PageResult<EntUserAccountResult> findPageBySpec(EntUserAccountParam param);

    /**
     * 通过账号获取用户账号信息
     *
     * @author fengshuonan
     * @Date 2019/10/11 20:17
     */
    EntUserAccount getAccountInfoByAccount(String account);
}
