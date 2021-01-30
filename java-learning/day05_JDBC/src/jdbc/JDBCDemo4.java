package jdbc;


import domain.Emp;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建findAll（）方法，便利emp表中数据
 */
public class JDBCDemo4 {

    public static void main(String[] args) {
        JDBCDemo4 j = new JDBCDemo4();
        List<Emp> list = j.findAll2();
        for(Emp e : list){
            System.out.println(e);
        }
        System.out.println(list.size());

    }

    //遍历数据库emp表
    public List<Emp> findAll(){
        List<Emp> empList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "dev", "123");
            //3.生成数据库sql执行对象
            stmt = conn.createStatement();
            //4.数据库执行sql语句
            String sql = "select * from emp";
            //5.执行sql语句
            rs = stmt.executeQuery(sql);
            //6.获取执行结果
            Emp aEmp;
            while(rs.next()){
                int id = rs.getInt("id");
                String eName = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joinDate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");

                aEmp = new Emp(id, eName, job_id, mgr, joinDate,salary, bonus, dept_id);
                empList.add(aEmp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //7.释放资源
            someClose(rs, stmt,conn);
        }

        return empList;
    }

    /**
     * 使用 JDBCUtils 工具类获取连接 Connection 和关闭连接释放资源
     * @return List<Emp> emp表集合
     */
    public List<Emp> findAll2(){
        List<Emp> empList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
//            //1.加载驱动
//            Class.forName("com.mysql.jdbc.Driver");
//            //2.获取数据库连接
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "dev", "123");
            //1.获取连接
            conn = JDBCUtils.getConn();
            //3.生成数据库sql执行对象
            stmt = conn.createStatement();
            //4.数据库执行sql语句
            String sql = "select * from emp";
            //5.执行sql语句
            rs = stmt.executeQuery(sql);
            //6.获取执行结果
            Emp aEmp;
            while(rs.next()){
                int id = rs.getInt("id");
                String eName = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joinDate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");

                aEmp = new Emp(id, eName, job_id, mgr, joinDate,salary, bonus, dept_id);
                empList.add(aEmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //7.释放资源
            JDBCUtils.close(rs, stmt, conn);
        }

        return empList;
    }


    /**
     * 资源释放通用类
     * @param object 需要释放的资源
     */
    public void someClose(AutoCloseable object){
        if(object != null){
            try {
                object.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void someClose(AutoCloseable ... objects){
        for (AutoCloseable obj : objects) {
            if(obj != null){
                try {
                    obj.close();
                    System.out.println(obj.getClass().getName() + "已关闭");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
