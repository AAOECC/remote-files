package com.example.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用 druid 数据库连接池技术
 * 创建 JDBC 工具类
 */
public class JDBCUtils {
    private static DataSource ds;//数据库连接池
//    public static void main(String[] args) {
//        try {
//            //1.获取配置文件字节流并加载
//            Properties pro = new Properties();
//            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
//            System.out.println(pro);
//            //2.创建数据库连接池
//            ds = DruidDataSourceFactory.createDataSource(pro);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }配置文件已获取到



    static{
        try {
            //1.获取配置文件字节流并加载
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //System.out.println(pro);
            //2.创建数据库连接池
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从数据库连接池中获取连接
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 获取数据库连接池
     * @return DataSoure
     */
    public static DataSource getDs(){
        return ds;
    }

    /**
     * 关闭连接
     * @param objs
     */
    public static void Close(Closeable... objs){
        for (Closeable obj : objs) {
            if (obj != null){
                try {
                    obj.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
