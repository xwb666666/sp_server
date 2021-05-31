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

import cn.stylefeng.guns.cloud.libs.mp.page.PageFieldNamesContainer;
import cn.stylefeng.guns.cloud.libs.web.error.DefaultExceptionHandler;
import cn.stylefeng.guns.cloud.libs.web.error.SystemErrorAttributes;
import cn.stylefeng.guns.cloud.libs.web.json.CustomFastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * 自定义web配置
 *
 * @author fengshuonan
 * @Date 2018/7/24 下午3:27
 */
@Configuration
public class CustomWebConfig implements WebMvcConfigurer {

    @Resource
    private CustomFastJsonHttpMessageConverter customFastJsonHttpMessageConverter;

    /**
     * 默认的全局异常拦截器
     *
     * @author fengshuonan
     * @Date 2020/5/20 6:18 下午
     */
    @Bean
    public DefaultExceptionHandler defaultControllerExceptionHandler() {
        return new DefaultExceptionHandler();
    }

    /**
     * 静态资源映射
     *
     * @author fengshuonan
     * @Date 2020/5/7 4:12 下午
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger的静态资源映射
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        //swagger增强的静态资源映射
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");

        //流程设计器
        registry.addResourceHandler("/activiti-editor/**").addResourceLocations("classpath:/activiti-editor/");
    }

    /**
     * json解析器改为fastjson
     *
     * @author fengshuonan
     * @Date 2020/5/7 4:13 下午
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        converters.add(stringHttpMessageConverter);
        converters.add(customFastJsonHttpMessageConverter);
    }

    /**
     * 错误信息提示重写
     *
     * @author fengshuonan
     * @Date 2019/5/31 21:27
     */
    @Bean
    public SystemErrorAttributes systemErrorAttributes() {
        return new SystemErrorAttributes();
    }

    /**
     * 项目的分页参数适配，可以支持更多分页参数字段名称
     * <p>
     * 例如我们系统的分页参数字段名是pageSize和pageNo
     * <p>
     * 同时我们又想支持字段名是page和size的，就可以在这里配置
     *
     * @author fengshuonan
     * @Date 2019/12/8 19:04
     */
    @Bean
    public Object pageFieldNameConfig() {

        // 第几页字段的名称
        HashSet<String> pageNoFields = new HashSet<>();
        pageNoFields.add("page");
        pageNoFields.add("current");

        // 每页数量的字段名称
        HashSet<String> pageSizeFields = new HashSet<>();
        pageSizeFields.add("size");

        PageFieldNamesContainer.getInstance().initPageNoFieldNames(pageNoFields);
        PageFieldNamesContainer.getInstance().initPageSizeFieldNames(pageSizeFields);

        return new Object();
    }

}
