package cn.itcast.travel.web.servlet;


import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet{

    /**
     * 分页查询
     * @param request
     * @param response
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收数据
        String currentPage_str = request.getParameter("currentPage");//当前页码
        String pageSize_str = request.getParameter("pageSize");//每页显示条目数
        String cid_str = request.getParameter("cid");//请求项目

        //处理转化数据
        //默认值
        int currentPage = 1;
        int pageSize = 5;
        int cid = 1;
        //取得实际值
        if (null != cid_str && cid_str.length() > 0) {
            cid = Integer.parseInt(cid_str);
        }
        if (null != pageSize_str && pageSize_str.length() > 0) {
            pageSize = Integer.parseInt(pageSize_str);
        }
        if (null != currentPage_str && currentPage_str.length() > 0) {
            currentPage = Integer.parseInt(currentPage_str);
        }

        //调用service 去的 pageBean 返回对象
        RouteService routeService = new RouteServiceImpl();
        PageBean<Route> pageBean = routeService.findByPage(cid, currentPage, pageSize);
        writeValueByJson(pageBean,response);
    }
}
