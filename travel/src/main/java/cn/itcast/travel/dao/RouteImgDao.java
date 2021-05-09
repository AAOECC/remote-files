package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    /**
     * 根据 route rid 查询对应的图片集合
     * @param rid 路线 rid
     * @return List
     */
    public List<RouteImg> findByRid(int rid);
}
