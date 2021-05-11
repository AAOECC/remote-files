package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {

    /**
     * 根据分页查找数据
     * @param cid 查询项目
     * @param currentPage 查询当前页面
     * @param pageSize 每页展示条数
     * @return pageBean 对象
     */
    public PageBean<Route> findByPage(int cid, int currentPage, int pageSize, String rname);


    /**
     * 根据 路线rid 查找详细信息
     * @param rid route:rid
     * @return all obj Route message
     */
    public Route findOne(int rid);
}