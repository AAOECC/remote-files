package servlet;

import dao.UserDao;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns="/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login...");
        request.setCharacterEncoding("utf-8");
        String contextPath = request.getContextPath();
        //取得验证码数据，进行比对
        HttpSession session = request.getSession();
        Object valiCode = session.getAttribute("valiCode");
        String get_code = request.getParameter("valiCode");
        System.out.println("right : "+valiCode);
        System.out.println("get : "+get_code);
        if (!valiCode.toString().equals(get_code)){
            session.setAttribute("statue","valiCode");
            //request.getRequestDispatcher("/login.jsp").forward(request,response);
            response.sendRedirect(contextPath+ "/login.jsp");
            return;
        }

        //取得数据，封装成 User
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        System.out.println("loginUser : "+loginUser);
        //数据库对比
        UserDao userDao = new UserDao();
        User user = userDao.getUser(loginUser);
        if (user != null){
            //用户名及密码正确
            session.setAttribute("user",user);
            response.sendRedirect(contextPath+"/success.jsp");
        } else {
            session.setAttribute("statue","user_fail");
            response.sendRedirect(contextPath+"/login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
