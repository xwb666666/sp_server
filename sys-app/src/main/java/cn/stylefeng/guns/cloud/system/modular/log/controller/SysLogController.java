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
package cn.stylefeng.guns.cloud.system.modular.log.controller;

import cn.stylefeng.guns.cloud.libs.scanner.annotation.GetResource;
import cn.stylefeng.guns.cloud.libs.scanner.annotation.PostResource;
import cn.stylefeng.guns.cloud.libs.scanner.stereotype.ApiResource;
import cn.stylefeng.guns.cloud.model.web.response.ResponseData;
import cn.stylefeng.guns.cloud.model.web.response.SuccessResponseData;
import cn.stylefeng.guns.cloud.system.modular.log.param.SysOpLogParam;
import cn.stylefeng.guns.cloud.system.modular.log.service.SysOpLogService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 系统日志控制器
 *
 * @author xuyuxiang
 * @date 2020/3/19 21:14
 */
@RestController
@ApiResource(name = "日志管理")
public class SysLogController {

    @Resource
    private SysOpLogService sysOpLogService;

    /**
     * 查询操作日志
     *
     * @author xuyuxiang
     * @date 2020/3/20 18:52
     */
    @GetResource(name = "操作日志_查询", path = "/sysOpLog/page")
    public ResponseData opLogPage(SysOpLogParam sysOpLogParam) {
        return new SuccessResponseData(sysOpLogService.page(sysOpLogParam));
    }

    /**
     * 清空操作日志
     *
     * @author xuyuxiang
     * @date 2020/3/23 16:28
     */
    @PostResource(name = "操作日志_清空", path = "/sysOpLog/delete")
    public ResponseData opLogDelete() {
        sysOpLogService.delete();
        return new SuccessResponseData();
    }
}
