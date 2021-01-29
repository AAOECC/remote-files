package jdbc;

import java.sql.*;

/**
 * 删除account表数据
 */
public class JDBCDemo3 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //1.加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.链接数据库
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "dev", "123");
            //3.创建sql数据库执行对象
            stmt = conn.createStatement();
            //4.SQL执行语句
            String sql = "delete from account where id = 3";
            String querySql = "select * from account";
            //5.执行sql语句
            int count = stmt.executeUpdate(sql);
            //6.输出执行结果
            ResultSet rs = stmt.executeQuery(querySql);
            System.out.println(count);
            if(count > 0){
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //7.释放资源
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
