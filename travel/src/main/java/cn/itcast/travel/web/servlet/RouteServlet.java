package cn.itcast.travel.web.servlet;


import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet{
    private final RouteService routeService = new RouteServiceImpl();
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

        String rname_str = request.getParameter("rname");
        //处理转化数据
        //默认值
        int currentPage = 1;
        int pageSize = 5;
        int cid = 0;
        String rname = null;
        //取得实际值
        if (rname_str!=null && rname_str.length() > 0 && !"null".equals(rname_str)){
            rname = new String(rname_str.getBytes("iso-8859-1"),"utf-8");
        }
        if (null != cid_str && cid_str.length() > 0 && !"null".equals(cid_str)) {
            cid = Integer.parseInt(cid_str);
        }
        if (null != pageSize_str && pageSize_str.length() > 0) {
            pageSize = Integer.parseInt(pageSize_str);
        }
        if (null != currentPage_str && currentPage_str.length() > 0) {
            currentPage = Integer.parseInt(currentPage_str);
        }

        //调用service 去的 pageBean 返回对象

        PageBean<Route> pageBean = routeService.findByPage(cid, currentPage, pageSize, rname);
        writeValueByJson(pageBean,response);
    }

    /**
     * 线路详情展示
     * @param request
     * @param response
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid_str = request.getParameter("rid");

        int rid = 0;

        if (rid_str != null && rid_str.length() > 0 && !"null".equals(rid_str)) {
            rid = Integer.parseInt(rid_str);
        }

        Route route = routeService.findOne(rid);
        ResultInfo info = new ResultInfo();
        if (route != null) {
            info.setFlag(true);
            info.setData(route);
        } else {
            info.setFlag(false);
            info.setErrorMsg("未查找到该线路!");
        }

        writeValueByJson(info,response);
    }
}
