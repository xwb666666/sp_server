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
package cn.stylefeng.guns.cloud.system.core.log.factory;

import cn.hutool.log.Log;
import cn.stylefeng.guns.cloud.libs.context.RequestNoContext;
import cn.stylefeng.guns.cloud.libs.context.SpringContext;
import cn.stylefeng.guns.cloud.system.core.log.anno.BusinessLog;
import cn.stylefeng.guns.cloud.system.modular.log.entity.SysOpLog;
import cn.stylefeng.guns.cloud.system.modular.log.service.SysOpLogService;
import org.aspectj.lang.JoinPoint;

import java.util.TimerTask;


/**
 * 日志操作任务创建工厂
 *
 * @author xuyuxiang
 * @date 2020/3/12 14:18
 */
public class LogTaskFactory {

    private static final Log log = Log.get();

    private static final SysOpLogService sysOpLogService = SpringContext.getBean(SysOpLogService.class);

    /**
     * 操作日志
     *
     * @author xuyuxiang
     * @date 2020/3/12 15:21
     */
    public static TimerTask operationLog(SysOpLog sysOpLog, String account, BusinessLog businessLog, JoinPoint joinPoint, String result) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    LogFactory.createSysOperationLog(sysOpLog, account, businessLog, joinPoint, result);
                    sysOpLogService.save(sysOpLog);
                } catch (Exception e) {
                    log.error(">>> 创建操作日志异常，请求号为：{}，具体信息为：{}", RequestNoContext.getRequestNoByHttpHeader(), e.getMessage());
                }
            }
        };
    }

    /**
     * 异常日志
     *
     * @author xuyuxiang
     * @date 2020/3/12 15:21
     */
    public static TimerTask exceptionLog(SysOpLog sysOpLog, String account, BusinessLog businessLog, JoinPoint joinPoint, Exception exception) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    LogFactory.createSysExceptionLog(sysOpLog, account, businessLog, joinPoint, exception);
                    sysOpLogService.save(sysOpLog);
                } catch (Exception e) {
                    log.error(">>> 创建异常日志异常，请求号为：{}，具体信息为：{}", RequestNoContext.getRequestNoByHttpHeader(), e.getMessage());
                }
            }
        };
    }

}
