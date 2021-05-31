package cn.stylefeng.guns.cloud.system.core.tenant.util;

import cn.stylefeng.guns.cloud.libs.config.properties.DruidProperties;
import cn.stylefeng.guns.cloud.system.core.dbs.context.DataSourceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * sql文件执行
 *
 * @author fengshuonan
 * @date 2019-06-18 17:10
 */
@Slf4j
public class SqlRunUtil {

    /**
     * 执行sql脚本文件，使用Spring工具类
     */
    public static void runClassPathSql(String classpathFileName, String dbName) {

        DruidProperties druidProperties = DataSourceContext.getDataSourcesConfs().get(dbName);

        try {
            Class.forName(druidProperties.getDriverClassName());
            Connection conn = DriverManager.getConnection(druidProperties.getUrl(), druidProperties.getUsername(), druidProperties.getPassword());

            ClassPathResource classPathResource = new ClassPathResource(classpathFileName);
            EncodedResource encodedResource = new EncodedResource(classPathResource, "utf-8");
            ScriptUtils.executeSqlScript(conn, encodedResource);
        } catch (Exception e) {
            log.error(">>> 执行sql错误！", e);
            throw new RuntimeException("执行sql错误！");
        }

    }

}
