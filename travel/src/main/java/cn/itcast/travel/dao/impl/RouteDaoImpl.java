package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询数据库中特定条目数据
     * @param cid
     * @param begin
     * @param
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int begin, int pageSize) {
        String sql = "select * from tab_route where cid = ? limit ?, ?";
        List<Route> routeList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class), cid, begin, pageSize);
        return routeList;
    }

    /**
     * 查询表中总记录数
     * @return count
     */
    @Override
    public int totalCount(int cid) {
        String sql = "select count(*) from tab_route where cid=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, cid);
        return count;
    }
}
