package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static DataSource dataSource;

    static{
        InputStream is = JDBCUtils.class.getResourceAsStream("/druid.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回数据库连接池
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 返回连接
     * @return
     */
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭连接
     * @param objs
     */
    public void close(Closeable... objs){
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
