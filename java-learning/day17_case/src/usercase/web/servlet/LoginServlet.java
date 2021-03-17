package usercase.web.servlet;

import usercase.domain.Admin;
import usercase.service.LoginService;
import usercase.service.impl.LoginServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取接收到的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verifyCode = request.getParameter("verifyCode");
        //用于获取 验证码 值
        HttpSession session = request.getSession();

        String rightCode = (String)session.getAttribute("right-code");

        //调用 loginService 中的方法判断是否登录正确
        LoginService loginService = new LoginServiceImpl();
        //封装loginAdmin
        Admin loginAdmin = new Admin();
        loginAdmin.setName(username);
        loginAdmin.setPassword(password);

        if(!rightCode.equalsIgnoreCase(verifyCode) || verifyCode.isEmpty()){
            //验证码错误
            request.setAttribute("errorMsg","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        } else if(!loginService.isAdmin(loginAdmin)){
            //账号密码错误
            request.setAttribute("errorMsg","账号密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        } else {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }


    }
}
