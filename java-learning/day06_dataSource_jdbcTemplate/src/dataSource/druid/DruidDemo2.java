package dataSource.druid;

import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用JDBC工具类
 */
public class DruidDemo2 {

    public static void main(String[] args) {
        DruidDemo2.addRecord();
    }

    /**
     * 在db3数据库account表添加一条数据，使用基于Druid 的工具类
     */
    public static void addRecord(){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.生成需要执行的sql语句
            String sql = "insert into account values (null, ?, ?)";
            //3.创建preparedStatement 数据库sql执行对象
            pstmt = conn.prepareStatement(sql);
            //4.补全sql语句
            pstmt.setString(1, "wangwu");
            pstmt.setDouble(2, 3000);
            //5.执行语句
            int count = pstmt.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.Close(pstmt, conn);
        }


    }
}
