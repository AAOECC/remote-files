package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private final RouteDao routeDao = new RouteDaoImpl();
    private final RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private final SellerDao sellerDao = new SellerDaoImpl();

    @Override
    public PageBean<Route> findByPage(int cid, int currentPage, int pageSize, String rname) {
        //计算查询开始
        int begin = (currentPage-1) * pageSize;

        //调用 dao 进行查询
        List<Route> routeList = routeDao.findByPage(cid, begin, pageSize, rname);
        int totalCount = routeDao.totalCount(cid, rname);
        //总页码数计算
        int totalPage = (totalCount%pageSize == 0 ? 0 : 1) + (totalCount/pageSize);
        //封装 pageBean 对象
        PageBean<Route> routePageBean = new PageBean<>();

        routePageBean.setCurrentPage(currentPage);
        routePageBean.setPageSize(pageSize);
        routePageBean.setList(routeList);
        routePageBean.setTotalCount(totalCount);
        routePageBean.setTotalPage(totalPage);

        return routePageBean;
    }

    /**
     * 根据 路线rid 查找详细信息
     * @param rid route:rid
     * @return all obj Route message
     */
    @Override
    public Route findOne(int rid) {
        //查找 route 表
        Route route = routeDao.findOne(rid);
        if (route == null){
            return null;
        }
        //image List
        List<RouteImg> routeImgs = routeImgDao.findByRid(rid);
        route.setRouteImgList(routeImgs);

        //seller
        Seller seller = sellerDao.findBySid(route.getSid());
        route.setSeller(seller);

        return route;
    }
}
