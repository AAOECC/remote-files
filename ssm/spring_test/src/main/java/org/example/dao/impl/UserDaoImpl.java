package org.example.dao.impl;

import org.example.dao.UserDao;
import org.example.domain.Role;
import org.example.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = null;
        String sql = "select * from sys_user";
        userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public List<Role> findRoles(Long userId){
        List<Role> roleList = null;
        String sql = "select * from sys_role where id in (select roleId from sys_user_role where userid = ?)";
        roleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class), userId);
        return roleList;
    }
}
