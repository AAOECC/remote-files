package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private final RouteDao routeDao = new RouteDaoImpl();

    @Override
    public PageBean<Route> findByPage(int cid, int currentPage, int pageSize) {
        //计算查询开始
        int begin = (currentPage-1) * pageSize;

        //调用 dao 进行查询
        List<Route> routeList = routeDao.findByPage(cid, begin, pageSize);
        int totalCount = routeDao.totalCount(cid);
        //总页码数计算
        int totalPage = ((pageSize-(totalCount%pageSize))+totalCount)/pageSize;
        //封装 pageBean 对象
        PageBean<Route> routePageBean = new PageBean<>();

        routePageBean.setCurrentPage(currentPage);
        routePageBean.setPageSize(pageSize);
        routePageBean.setList(routeList);
        routePageBean.setTotalCount(totalCount);
        routePageBean.setTotalPage(totalPage);

        return routePageBean;
    }
}
