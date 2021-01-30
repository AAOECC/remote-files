package jdbc;


import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 1.键盘输入登录名和密码，
 * 2.判断是否登录成功
 * 3.测试sql注入
 */
public class JDBCDemo5 {

    public static void main(String[] args) {
        //1.取得输入用户名和密码
        Scanner sc = new Scanner(System.in);
        System.out.println("username: ");
        String username = sc.nextLine();
        System.out.println("password: ");
        String password = sc.nextLine();
        System.out.println(username+ " / "+password);

        //2.判断是否登录成功，显示不同输出
        if(new JDBCDemo5().login(username, password)){
            System.out.println("登录成功！");
        } else {
            System.out.println("用户名或密码错误！");
        }

    }


    /**
     * 登录判断方法
     * @param username 用户名
     * @param password 密码
     * @return boolean值，是否成功
     */
    public boolean login(String username, String password){
        //若为空，返回失败
        if (username ==null || password ==null){
            return false;
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //1.获取连接
            conn = JDBCUtils.getConn();
            //2.sql语句执行对象
            stmt = conn.createStatement();
            //3.生成sql语句
            String sql = "select * from user where username = '"+username+"' and password = '"+password+"'";
            //3.取得结果集
            rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }

        return false;
    }
}
