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
package cn.stylefeng.guns.cloud.libs.config;

import cn.stylefeng.guns.cloud.libs.web.RequestDataAop;
import cn.stylefeng.guns.cloud.libs.web.json.CustomFastJsonHttpMessageConverter;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * fastjson配置类
 *
 * @author fengshuonan
 * @date 2017-05-23 22:56
 */
@Configuration
public class CustomFastJsonConfig {

    /**
     * 自定义的fastjson转化器，为了在转化过程中临时存储一下json消息体
     *
     * @author fengshuonan
     * @Date 2020/5/20 6:34 下午
     */
    @Bean
    public CustomFastJsonHttpMessageConverter customFastJsonHttpMessageConverter() {
        CustomFastJsonHttpMessageConverter converter = new CustomFastJsonHttpMessageConverter();
        converter.setFastJsonConfig(fastjsonConfig());
        converter.setSupportedMediaTypes(getSupportedMediaType());
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        return converter;
    }

    /**
     * 请求body的临时保存，结合CustomFastJsonHttpMessageConverter一起使用
     *
     * @author fengshuonan
     * @date 2020/8/8 18:35
     */
    @Bean
    public RequestDataAop requestDataAop() {
        return new RequestDataAop();
    }

    /**
     * fastjson的配置，开启了一些策略，包括对long类型数据的字符串输出这个配置
     * <p>
     * 还有日期格式化的配置
     *
     * @author fengshuonan
     * @Date 2020/5/20 6:34 下午
     */
    public FastJsonConfig fastjsonConfig() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.BrowserCompatible
        );
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        initOtherValueFilters(fastJsonConfig);
        return fastJsonConfig;
    }

    /**
     * 支持的mediaType类型
     *
     * @author fengshuonan
     * @Date 2020/5/20 6:35 下午
     */
    public List<MediaType> getSupportedMediaType() {
        ArrayList<MediaType> mediaTypes = new ArrayList<>();

        mediaTypes.add(MediaType.APPLICATION_JSON);

        //增加解析spring boot actuator结果的解析
        mediaTypes.add(MediaType.valueOf("application/vnd.spring-boot.actuator.v2+json"));

        return mediaTypes;
    }

    /**
     * 初始化value过滤器
     * <p>
     * 默认的valueFilter是把空的字段转化为空串
     *
     * @author fengshuonan
     * @Date 2020/5/20 6:35 下午
     */
    protected void initOtherValueFilters(FastJsonConfig fastJsonConfig) {

        //为空的值转化为空串
        ValueFilter nullValueFilter = (object, name, value) -> {
            if (null == value) {
                return "";
            } else {
                return value;
            }
        };

        fastJsonConfig.setSerializeFilters(nullValueFilter);
    }

}
