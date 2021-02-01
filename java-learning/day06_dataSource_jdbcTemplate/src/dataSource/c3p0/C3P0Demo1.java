package dataSource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * C3P0连接池测试
 */
public class C3P0Demo1 {

    public static void main(String[] args) {
        try {
            new C3P0Demo1().c3p0Test();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印Connection 对象
     * @throws SQLException
     */
    public void c3p0Test() throws SQLException {
        DataSource ds = new ComboPooledDataSource();

        Connection connection = ds.getConnection();

        System.out.println(connection);
    }

}
