package usercase.web.servlet;

import usercase.service.UserService;
import usercase.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelCheckedUserServlet", urlPatterns = "/delCheckedUserServlet")
public class DelCheckedUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //获取id值
        String[] ids = request.getParameterValues("id");

        //调用服务删除
        UserService userService = new UserServiceImpl();
        userService.delCheckedUser(ids);

        //重定向
        response.sendRedirect(request.getContextPath()+"/listServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
