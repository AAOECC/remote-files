package usercase.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    private static DataSource dataSource;

    static {
        try {
            //加载配置文件
            Properties pro = new Properties();
            pro.load(JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            //生成连接池
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Closeable... objs){
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
