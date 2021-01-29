package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo1 {

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3",
                "dev", "123");
        Statement stmt = conn.createStatement();

        String sql = "UPDATE account SET balance = 1000 WHERE id=1";

        int count = stmt.executeUpdate(sql);

        System.out.println(count);

        stmt.close();
        conn.close();

    }

}
