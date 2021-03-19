package usercase.web.servlet;

import usercase.domain.User;
import usercase.service.UserService;
import usercase.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DelUserServlet", value = "/delUserServlet")
public class DelUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //接收查询参数
        String id = request.getParameter("id");
        //封装为 User 对象
        User delUser = new User();
        delUser.setId(Integer.parseInt(id));
        //调用 service 中的方法 删除数据
        UserService userService = new UserServiceImpl();
        boolean b = userService.delUser(delUser);
        //转发请求到 ListServlet
        if(b){
            response.sendRedirect(request.getContextPath()+"/listServlet");
        }
    }
}
