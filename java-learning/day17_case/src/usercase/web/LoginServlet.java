package usercase.web;

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verifyCode = request.getParameter("verifyCode");

        HttpSession session = request.getSession();
        //String rightCode = (String)session.getAttribute("right-code");
        String rightCode = "0000";
        if(!rightCode.equals(verifyCode)){
            request.setAttribute("errorMsg","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        } else if(username != "123" || password != "234"){
            request.setAttribute("errorMsg","账号密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }


    }
}
