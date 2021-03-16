package com.exmple.dao;

import com.exmple.domain.User;
import com.exmple.jdbc.JDBCUtils;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDs());


//    public static void main(String[] args) {
//        String username = "bob";
//        String password = "123";
//        User user = new UserDao().creatUser(username, password);
//        System.out.println(user);
//    }



    /*
    测试连接类
     */
    private void test(){
        String sql = "select * from user where id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, 1);

        System.out.println(map);

    }

    /**
     * 接收 username 及password 查找数据库
     * 若找到则返回 封装过的 User 对象
     * 未找到返回 null
     * @param username
     * @param password
     * @return
     */
    public User creatUser(String username, String password){
        //该语句查询时未区分大小写
        String sql = "select * from user where username = ? and password = ?";
        //区分大小写
        String sql_binary = "select * from user where binary username=? and binary password=?";

        //判断是否返回数据
        List<User> userList = jdbcTemplate.query(sql_binary, new BeanPropertyRowMapper<User>(User.class), username, password);
        if(userList.size() == 0){
            return null;
        } else {
            return userList.get(0);
        }
    }
}
