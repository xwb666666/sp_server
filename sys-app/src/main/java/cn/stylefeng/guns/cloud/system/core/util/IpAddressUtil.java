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
package cn.stylefeng.guns.cloud.system.core.util;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.log.Log;

import javax.servlet.http.HttpServletRequest;

/**
 * 根据ip地址定位工具类，使用阿里云定位api
 *
 * @author xuyuxiang
 * @date 2020/3/16 11:25
 */
public class IpAddressUtil {

    private static final Log log = Log.get();

    private static final String LOCAL_IP = "127.0.0.1";

    private static final String LOCAL_REMOTE_HOST = "0:0:0:0:0:0:0:1";

    /**
     * 获取客户端ip
     *
     * @author xuyuxiang
     * @date 2020/3/19 9:32
     */
    public static String getIp(HttpServletRequest request) {
        if (ObjectUtil.isEmpty(request)) {
            return "-";
        } else {
            String remoteHost = ServletUtil.getClientIP(request);
            return LOCAL_REMOTE_HOST.equals(remoteHost) ? LOCAL_IP : remoteHost;
        }
    }

    /**
     * 根据ip地址定位
     *
     * @author xuyuxiang
     * @date 2020/3/16 15:17
     */
    public static String getAddress(HttpServletRequest request) {
        String resultJson = "-";

        String ip = getIp(request);

        //如果是本地ip或局域网ip，则直接不查询
        if (ObjectUtil.isEmpty(ip) || NetUtil.isInnerIP(ip)) {
            return resultJson;
        } else {
            return ip;
        }
    }

}
