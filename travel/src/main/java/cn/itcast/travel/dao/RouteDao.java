package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    /**
     * 查询数据库中特定条目数据
     * @param begin
     * @param
     * @return
     */
    public List<Route> findByPage(int cid, int begin, int pageSize);

    /**
     * 查询表中总记录数
     * @return
     */
    public int totalCount(int cid);
}
