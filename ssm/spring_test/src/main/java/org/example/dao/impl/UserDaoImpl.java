package org.example.dao.impl;

import org.example.dao.UserDao;
import org.example.domain.Role;
import org.example.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<Role> findRoles(Long userId) {
        List<Role> roleList = null;
        String sql = "select * from sys_role where id in (select roleId from sys_user_role where userid = ?)";
        roleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class), userId);
        return roleList;
    }

    @Override
    public Long saveUserMsg(User user) {
        Long userId = null;
        String sql = "insert into sys_user values(?,?,?,?,?)";
        //创建 PreparedStatementCreator
//        PreparedStatementCreator psc = new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//                preparedStatement.setObject(1, null);
//                preparedStatement.setString(2, user.getUsername());
//                preparedStatement.setString(3, user.getEmail());
//                preparedStatement.setString(4, user.getPassword());
//                preparedStatement.setString(5, user.getPhoneNum());
//
//                return preparedStatement;
//            }
//        };
        //创建 keyHolder
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPhoneNum());
            return preparedStatement;
        }, keyHolder);
        userId = Objects.requireNonNull(keyHolder.getKey()).longValue();
//        jdbcTemplate.update(sql, null, user.getUsername(), user.getEmail(), user.getPassword(), user.getPhoneNum());
//        userId = jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
        return userId;
    }

    @Override
    public void saveU_RMsg(Long userId, Long[] roleIds) {
        StringBuilder sql_sb = new StringBuilder();
        sql_sb.append("insert into sys_user_role value ");
        ArrayList<Object> args = new ArrayList<>();
        for (Long roleId : roleIds) {
            sql_sb.append(" (?,?),");
            args.add(userId);
            args.add(roleId);
        }

        sql_sb.deleteCharAt(sql_sb.length() - 1);

        String sql_str = sql_sb.toString();
        System.out.println(sql_str);
        System.out.println(args);
        jdbcTemplate.update(sql_str, args.toArray());
    }

    @Override
    public void delFromU_R(long userId) {
        String sql = "delete from sys_user_role where userId = ?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public void delFromUser(long userId) {
        String sql = "delete from sys_user where id = ?";
        jdbcTemplate.update(sql, userId);
    }
}
