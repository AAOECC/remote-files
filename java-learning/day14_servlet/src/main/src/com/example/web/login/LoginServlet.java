package com.example.web.login;

import com.example.dao.UserDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.example.domain.User;

@WebServlet(name = "LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置输入流字符集
        request.setCharacterEncoding("utf-8");
        //获取请求参数中的 username 及 password
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        /*
        System.out.println(username+ "\n" + password);
        已获取到数据
         */
        //获取 提交的 请求参数
        Map<String, String[]> map = request.getParameterMap();
        User loginUser = new User();
        //beanutils方式生成 loginUser
        try {
            BeanUtils.populate(loginUser, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //进行数据库通信，判断是否登录成功
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        if (user != null){
            //封装共享数据 request域
            request.setAttribute("user", user);
            //转发到success
            request.getRequestDispatcher("/successServlet").forward(request, response);
        } else {
            request.getRequestDispatcher("/failServlet").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
