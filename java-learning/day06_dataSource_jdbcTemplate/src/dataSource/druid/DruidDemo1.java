package dataSource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo1 {

    public static void main(String[] args) throws Exception {

        //1.加载配置文件
        Properties pro = new Properties();
        //2.获取配置文件字节流
        InputStream is = DruidDemo1.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);

        DataSource ds = DruidDataSourceFactory.createDataSource(pro);

        Connection conn = ds.getConnection();

        System.out.println(conn);

    }
}
