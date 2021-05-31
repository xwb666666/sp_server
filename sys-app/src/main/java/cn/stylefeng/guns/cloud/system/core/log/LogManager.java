/*
Copyright [2020] [https://www.stylefeng.cn]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Guns采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：

1.请不要删除和修改根目录下的LICENSE文件。
2.请不要删除和修改Guns源码头部的版权声明。
3.请保留源码和相关描述文件的项目出处，作者声明等。
4.分发源码时候，请注明软件出处 https://gitee.com/stylefeng/guns-separation
5.在修改包名，模块名称，项目代码等时，请注明软件出处 https://gitee.com/stylefeng/guns-separation
6.若您的项目无法满足以上几点，可申请商业授权，获取Guns商业授权许可，请在官网购买授权，地址为 https://www.stylefeng.cn
 */
package cn.stylefeng.guns.cloud.system.core.log;

import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.cloud.libs.context.HttpContext;
import cn.stylefeng.guns.cloud.model.exp.RequestEmptyException;
import cn.stylefeng.guns.cloud.system.core.log.anno.BusinessLog;
import cn.stylefeng.guns.cloud.system.core.log.factory.LogFactory;
import cn.stylefeng.guns.cloud.system.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.cloud.system.core.util.IpAddressUtil;
import cn.stylefeng.guns.cloud.system.core.util.UaUtil;
import cn.stylefeng.guns.cloud.system.modular.log.entity.SysOpLog;
import org.aspectj.lang.JoinPoint;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

import javax.servlet.http.HttpServletRequest;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 日志管理器
 *
 * @author xuyuxiang
 * @date 2020/3/12 14:13
 */
public class LogManager {

    /**
     * 异步操作记录日志的线程池
     */
    private static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(10, new ScheduledExecutorFactoryBean());

    private LogManager() {
    }

    private static final LogManager LOG_MANAGER = new LogManager();

    public static LogManager me() {
        return LOG_MANAGER;
    }

    /**
     * 异步执行日志的方法
     *
     * @author xuyuxiang
     * @date 2020/4/8 19:19
     */
    private void executeLog(TimerTask task) {

        //日志记录操作延时
        int operateDelayTime = 10;
        EXECUTOR.schedule(task, operateDelayTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 操作日志
     *
     * @author xuyuxiang
     * @date 2020/3/18 20:01
     */
    public void executeOperationLog(BusinessLog businessLog, final String account, JoinPoint joinPoint, final String result) {
        SysOpLog sysOpLog = this.genBaseSysOpLog();
        TimerTask timerTask = LogTaskFactory.operationLog(sysOpLog, account, businessLog, joinPoint, result);
        executeLog(timerTask);
    }

    /**
     * 异常日志
     *
     * @author xuyuxiang
     * @date 2020/3/18 20:01
     */
    public void executeExceptionLog(BusinessLog businessLog, final String account, JoinPoint joinPoint, Exception exception) {
        SysOpLog sysOpLog = this.genBaseSysOpLog();
        TimerTask timerTask = LogTaskFactory.exceptionLog(sysOpLog, account, businessLog, joinPoint, exception);
        executeLog(timerTask);
    }

    /**
     * 构建基础操作日志
     *
     * @author xuyuxiang
     * @date 2020/3/19 14:44
     */
    private SysOpLog genBaseSysOpLog() {
        HttpServletRequest request = HttpContext.getRequest();
        if (ObjectUtil.isNotNull(request)) {
            String ip = IpAddressUtil.getIp(request);
            String address = IpAddressUtil.getAddress(request);
            String browser = UaUtil.getBrowser(request);
            String os = UaUtil.getOs(request);
            String url = request.getRequestURI();
            String method = request.getMethod();
            return LogFactory.genBaseSysOpLog(ip, address, browser, os, url, method);
        } else {
            throw new RequestEmptyException();
        }
    }

}
