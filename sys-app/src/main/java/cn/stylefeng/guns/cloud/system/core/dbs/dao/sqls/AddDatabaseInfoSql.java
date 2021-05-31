package cn.stylefeng.guns.cloud.system.core.dbs.dao.sqls;

import lombok.Getter;

/**
 * 添加数据源sql
 *
 * @author fengshuonan
 * @date 2019-07-16-13:06
 */
@Getter
public class AddDatabaseInfoSql extends AbstractSql {

    @Override
    protected String mysql() {
        return "INSERT INTO `guns_sys_database_info`(`id`, `db_name`, `jdbc_driver`, `user_name`, `password`, `jdbc_url`, `remarks`, `create_time`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String sqlServer() {
        return "INSERT INTO [guns_sys_database_info] ([id], [db_name], [jdbc_driver], [user_name], [password], [jdbc_url], [remarks], [create_time]) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String pgSql() {
        return "INSERT INTO guns_sys_database_info(id, db_name, jdbc_driver, user_name, password, jdbc_url, remarks, create_time) VALUES (?, ?, ?, ?, ?, ?, ?, to_timestamp(?,'YYYY-MM-DD HH24:MI:SS'))";
    }

    @Override
    protected String oracle() {
        return "INSERT INTO guns_sys_database_info VALUES (?, ?, ?, ?, ?, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi:ss'))";
    }
}
