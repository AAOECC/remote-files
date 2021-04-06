package usercase.web.servlet;

import usercase.domain.PageBean;
import usercase.domain.User;
import usercase.service.UserService;
import usercase.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 主列表显示，
 * 接收 page
 * 转发 userList , pageBean
 */
@WebServlet(name = "ListServlet", urlPatterns = "/listServlet")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        UserService userService = new UserServiceImpl();
        //获取请求 page ，count
        String page = request.getParameter("page");
        String count = request.getParameter("count");

        //获取查询参数
        Map<String, String[]> condition = request.getParameterMap();

        //调用 service 进行数据库查询
        page = page==null?"1":page;
        int pageNow = Integer.parseInt(page);
        count = count==null?"5":count;
        int countNeed = Integer.parseInt(count);
        List<User> userList = userService.findByPage(pageNow, countNeed,condition);

        //查询数据库中的所有数据总数
        PageBean pageBean = userService.getPageBean(countNeed,condition);
        pageBean.setPage(pageNow);
        request.setAttribute("pageBean",pageBean);

        //封装数据，转发请求
        request.setAttribute("condition", condition);
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
