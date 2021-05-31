package cn.stylefeng.guns.cloud.system.core.dbs.exception;

import cn.stylefeng.guns.cloud.model.enums.exp.AbstractBaseException;
import cn.stylefeng.guns.cloud.model.exp.ServiceException;

/**
 * 数据源容器初始化失败异常
 *
 * @author fengshuonan
 * @date 2020/8/24
 */
public class DataSourceException extends ServiceException {

    public DataSourceException(AbstractBaseException exception) {
        super(exception);
    }

}
