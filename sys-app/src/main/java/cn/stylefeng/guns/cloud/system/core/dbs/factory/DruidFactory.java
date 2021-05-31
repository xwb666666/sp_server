package cn.stylefeng.guns.cloud.system.core.dbs.factory;

import cn.stylefeng.guns.cloud.libs.config.properties.DruidProperties;
import cn.stylefeng.guns.cloud.system.modular.dbs.entity.DatabaseInfo;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * Druid数据源创建
 *
 * @author fengshuonan
 * @date 2019-06-15-20:05
 */
public class DruidFactory {

    /**
     * 创建druid配置
     *
     * @author fengshuonan
     * @date 2019-06-15 20:05
     */
    public static DruidProperties createDruidProperties(DatabaseInfo databaseInfo) {
        DruidProperties druidProperties = new DruidProperties();
        druidProperties.setDriverClassName(databaseInfo.getJdbcDriver());
        druidProperties.setUsername(databaseInfo.getUserName());
        druidProperties.setPassword(databaseInfo.getPassword());
        druidProperties.setUrl(databaseInfo.getJdbcUrl());
        return druidProperties;
    }

    /**
     * 创建druid数据源
     *
     * @author fengshuonan
     * @date 2019-06-15 20:05
     */
    public static DruidDataSource createDruidDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

}
