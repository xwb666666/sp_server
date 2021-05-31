package cn.stylefeng.guns.cloud.system.core.dbs.annotation;

import java.lang.annotation.*;

/**
 * 多数据源标识
 *
 * @author fengshuonan
 * @date 2020/8/24
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSource {

    /**
     * 数据源的名称
     */
    String name() default "";

}
