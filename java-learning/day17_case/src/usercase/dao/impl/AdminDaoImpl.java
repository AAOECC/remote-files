package usercase.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import usercase.dao.AdminDao;
import usercase.domain.Admin;
import usercase.util.JDBCUtil;

public class AdminDaoImpl implements AdminDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public Admin getAdmin(Admin loginAdmin) {

        String sql = "select * from admin where binary name=? and binary password=?";

        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Admin.class), loginAdmin.getName(), loginAdmin.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}
