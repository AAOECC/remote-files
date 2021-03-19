package usercase.web.servlet;

import usercase.domain.User;
import usercase.service.UserService;
import usercase.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FindUserServlet", value = "/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //取得数据
        String id = request.getParameter("id");
        User aUser = new User();
        aUser.setId(Integer.parseInt(id));
        //调用 service 返回 user 的完整信息
        UserService userService = new UserServiceImpl();
        User user = userService.findUser(aUser);
        //转发到 update.jsp 页面
        request.setAttribute("user",user);
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }
}
