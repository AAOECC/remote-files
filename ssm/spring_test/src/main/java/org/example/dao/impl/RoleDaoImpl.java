package org.example.dao.impl;

import org.example.dao.RoleDao;
import org.example.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roleList = null;
        String sql = "select * from sys_role";
        roleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class));
        return roleList;
    }

    @Override
    public void save(Role role) {
        String sql = "insert into sys_role values(?,?,?)";
        jdbcTemplate.update(sql, null, role.getRoleName(), role.getRoleDesc());
    }
}
