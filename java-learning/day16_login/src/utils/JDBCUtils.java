package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用 druid 连接池技术
 * 实现jdbc工具类
 */
public class JDBCUtils {

    private static DataSource dataSource;

    //初始代码 块
    static {
        try {
            //加载配置文件
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //生成数据库连接池
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得 数据库连接
     * @return 数据库连接
     */
    public static Connection getConnect(){
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 取得 数据库连接池
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 数据库连接关闭
     * @param objs
     */
    public static void close(Closeable... objs){
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
