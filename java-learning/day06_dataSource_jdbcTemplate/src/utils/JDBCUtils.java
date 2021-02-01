package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import dataSource.druid.DruidDemo1;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC 工具类
 * 使用Druid连接池，创建JDBC工具类
 */
public class JDBCUtils {

    private static DataSource ds;

    static{
        try {
            //1.获取加载配置文件
            Properties pro  = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //2.获取连接池
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 获取数据库连接池
     * @return DataSource
     */
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 关闭连接
     * @param objs
     */
    public static void Close(AutoCloseable ... objs){
        for(AutoCloseable obj : objs){
            if (obj != null)
                try {
                    obj.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

}
