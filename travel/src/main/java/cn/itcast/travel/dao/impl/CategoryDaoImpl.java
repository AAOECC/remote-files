package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class CategoryDaoImpl implements CategoryDao {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查找数据库中所有的值
     */
    @Override
    public List<Category> findAll() {
        String sql = "select  * from tab_category order by cid";
        List<Category> CategoryList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return CategoryList;
    }
}
