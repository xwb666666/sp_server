package cn.stylefeng.guns.cloud.auth.modular.ssomgr.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 会话管理服务
 *
 * @author fengshuonan
 * @Date 2019/12/4 21:16
 */
public interface SessionManageService {

    /**
     * 会话列表集合（分页）
     *
     * @author fengshuonan
     * @Date 2019/12/4 21:16
     */
    Page pageList(String userName);

    /**
     * 强制用户下线（清楚会话）
     *
     * @author fengshuonan
     * @Date 2019/12/4 21:16
     */
    void forcedOffline(String ticket);
}
