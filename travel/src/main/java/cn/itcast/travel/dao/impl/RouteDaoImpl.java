package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
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
    public List<Route> findByPage(int cid, int begin, int pageSize, String rname) {
//        String sql = "select * from tab_route where cid = ? limit ?, ?";
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        params.add(begin);
        params.add(pageSize);
        sb.append(" limit ?, ?");
        sql = sb.toString();
//        System.out.println(params+"\n"+sql);
        List<Route> routeList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class), params.toArray());
        return routeList;
    }

    /**
     * 查询表中总记录数
     * @return count
     */
    @Override
    public int totalCount(int cid, String rname) {
//        String sql = "select count(*) from tab_route where cid=?";
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();

        int count = jdbcTemplate.queryForObject(sql, Integer.class, params.toArray());
        return count;
    }

    /**
     * 查询 rid 对应的一条 route 信息
     * @param rid
     * @return
     */
    public Route findOne(int rid){
        Route route = null;
        String sql = "select * from tab_route where rid=?";
        try {
            route = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        } catch (Exception e) {
            System.out.println("database: tab_route findOne() sql query error!");
        }

        return route;
    }
}
