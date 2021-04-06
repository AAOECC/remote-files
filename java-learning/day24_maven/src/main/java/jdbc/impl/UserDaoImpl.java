package jdbc.impl;

import domain.User;
import jdbc.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        Connection connection=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
        //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取 Connection 对象
            connection = DriverManager.getConnection("jdbc:mysql:///day17","dev","123");
            //获取操作数据库的对象
            pst = connection.prepareCall("select * from user");
            //执行查询
            rs = pst.executeQuery();
            //将结果封装为List
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                userList.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null){
                try {
                    rs.close();
                    pst.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return userList;
    }
}
