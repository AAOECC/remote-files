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
    public List<Route> findByPage(int cid, int begin, int pageSize, String rname);

    /**
     * 查询表中总记录数
     * @return
     */
    public int totalCount(int cid, String rname);

    /**
     * 查询 rid 对应的一条 route 信息
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
