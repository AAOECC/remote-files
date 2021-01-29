package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * account表添加记录
 */
public class JDBCDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            conn = DriverManager.getConnection("jdbc:mysql:///db3", "dev", "123");
            //3.需要执行的SQL语句
            String sql = "insert into account values (null, 'wangwu', 1500)";
            //4.获取数据库的执行对象
            stmt = conn.createStatement();
            //5.执行SQL
            int count = stmt.executeUpdate(sql);
            //6.输出结果
            System.out.println(count);
            if (count > 0){
                System.out.println("添加成功！");
            }else{
                System.out.println("添加失败！");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //7.释放资源
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
