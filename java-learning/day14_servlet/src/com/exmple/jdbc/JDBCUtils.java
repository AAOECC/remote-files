package com.exmple.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用 druid 数据库连接池
 * 创建 工具类
 */
public class JDBCUtils {
    private static DataSource ds;

    static {

        try {
            //druid 配置文件加载
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("com/exmple/druid.properties"));
            //System.out.println(pro);
            //数据库连接池
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 获取数据库连接池
     * @return
     */
    public static DataSource getDs(){
        return ds;
    }

    /**
     *关闭数据库连接
     * @param objs
     */
    public void close(Closeable... objs){
        for (Closeable obj : objs) {
            if(obj != null){
                try {
                    obj.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
