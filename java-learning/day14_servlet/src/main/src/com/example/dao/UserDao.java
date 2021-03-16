package com.example.dao;

import com.example.domain.User;
import com.example.jdbc.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDs());

    /**
     * 获取 loginUser 进行登录校验，
     *
     * @param loginUser 只包含username , password
     * @return 完整的 User 对象
     */
    public User login(User loginUser) {
        String sql_binary = "select * from user where binary username=? and binary password=?";
        //返回完整的User对象
        try {
            User user = jdbcTemplate.queryForObject(sql_binary,
                    new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());

            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


}
