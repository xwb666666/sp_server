/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.cloud.libs.web;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 目前仅仅用来暂时存放RequestBody的内容
 *
 * @author fengshuonan
 * @Date 2020/5/20 6:21 下午
 */
@Data
public class RequestData implements Serializable {

    private static final long serialVersionUID = 9081406366569775542L;

    /**
     * 封装前端请求的json数据
     */
    private JSONObject data;

    /**
     * 客户端请求的ip
     */
    private String ip;

    /**
     * 客户端请求的地址
     */
    private String url;

    /**
     * 获取指定key对应的值
     */
    public Object get(String key) {
        return this.data.get(key);
    }

}
