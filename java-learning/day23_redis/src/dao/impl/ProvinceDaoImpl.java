package dao.impl;

import dao.ProvinceDao;
import domain.Province;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        String sql = "select * from province";

        List<Province> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Province.class));

        return list;
    }
}
