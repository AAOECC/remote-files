package utils;

import jdbc.JDBCDemo4;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * jdbc 工具类
 */
public class JDBCUtils {

    private static Connection conn;

    /**
     * 类加载时执行，加载驱动，获取数据库连接
     */
    static{
        try {
            //1.生成配置文件操作对象
            Properties pro = new Properties();
            //2.获取配置文件字节流
            ClassLoader classLoader = JDBCDemo4.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream("jdbc.properties");
            pro.load(is);
            //3.取得字符串
            String url = pro.getProperty("url");
            //System.out.println(url);
            String user = pro.getProperty("user");
            String password = pro.getProperty("password");
            String driver = pro.getProperty("driver");
            //System.out.println(driver);
            //4.加载驱动
            Class.forName(driver);
            //5.获取连接
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得数据库连接
     * @return 数据库连接Connection
     */
    public static Connection getConn() throws SQLException {
        return conn;
    }


    /**
     * 依次关闭对象，释放资源
     */
    public static void close(AutoCloseable ... objs){
        for(AutoCloseable obj : objs){
            try {
                obj.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
