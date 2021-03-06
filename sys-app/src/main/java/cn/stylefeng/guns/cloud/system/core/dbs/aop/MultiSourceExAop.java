package cn.stylefeng.guns.cloud.system.core.dbs.aop;

import cn.stylefeng.guns.cloud.model.consts.AopSortConstants;
import cn.stylefeng.guns.cloud.system.core.dbs.annotation.DataSource;
import cn.stylefeng.guns.cloud.system.core.dbs.context.CurrentDataSourceContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

import static cn.stylefeng.guns.cloud.system.core.dbs.consts.DatabaseConstant.MASTER_DATASOURCE_NAME;

/**
 * 多数据源切换的aop
 *
 * @author fengshuonan
 * @date 2020/8/24
 */
@Aspect
@Slf4j
public class MultiSourceExAop implements Ordered {

    @Pointcut(value = "@annotation(cn.stylefeng.guns.cloud.system.core.dbs.annotation.DataSource)")
    private void cutService() {

    }

    @Around("cutService()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        // 获取被aop拦截的方法
        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        // 获取方法上的DataSource注解
        DataSource datasource = currentMethod.getAnnotation(DataSource.class);

        // 如果有DataSource注解，则设置DataSourceContextHolder为注解上的名称
        if (datasource != null) {
            CurrentDataSourceContext.setDataSourceType(datasource.name());
            log.debug(">>> 设置数据源为：" + datasource.name());
        } else {
            CurrentDataSourceContext.setDataSourceType(MASTER_DATASOURCE_NAME);
            log.debug(">>> 设置数据源为：dataSourceCurrent");
        }

        try {
            return point.proceed();
        } finally {
            log.debug(">>> 清空数据源信息！");
            CurrentDataSourceContext.clearDataSourceType();
        }
    }

    /**
     * aop的顺序要早于spring的事务
     *
     * @author fengshuonan
     * @date 2020/8/24
     */
    @Override
    public int getOrder() {
        return AopSortConstants.MULTI_DATA_SOURCE_AOP;
    }

}
