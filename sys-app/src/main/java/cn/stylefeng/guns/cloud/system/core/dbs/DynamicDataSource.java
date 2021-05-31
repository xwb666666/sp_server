package cn.stylefeng.guns.cloud.system.core.dbs;


import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.cloud.system.core.dbs.consts.DatabaseConstant;
import cn.stylefeng.guns.cloud.system.core.dbs.context.CurrentDataSourceContext;
import cn.stylefeng.guns.cloud.system.core.dbs.context.DataSourceContext;

import javax.sql.DataSource;
import java.util.Map;


/**
 * 动态数据源
 *
 * @author fengshuonan
 * @date 2017年3月5日 上午9:11:49
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 决断当前执行的server或dao用哪个数据源
     *
     * @author fengshuonan
     * @date 2020/8/27
     */
    @Override
    protected DataSource determineDataSource() {

        // 获取当前Context存储的数据源名称
        String dataSourceType = CurrentDataSourceContext.getDataSourceType();

        // 如果当前Context没有值，就用主数据源
        if (StrUtil.isEmpty(dataSourceType)) {
            dataSourceType = DatabaseConstant.MASTER_DATASOURCE_NAME;
        }

        // 从数据源容器中获取对应的数据源
        Map<String, DataSource> dataSources = DataSourceContext.getDataSources();
        return dataSources.get(dataSourceType);
    }

}
