package com.exmple.servlet;

import com.exmple.dao.UserDao;
import com.exmple.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置字节流编码
        request.setCharacterEncoding("utf-8");
        //2.获取 前端页面传输的请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

/*
        //测试前端连通性， 获取前端传输的请求参数，已连通
        System.out.println(username+ "\n" + password);
*/

        //传入 username 及 password 生成 user
        UserDao userDao = new UserDao();
        User user = userDao.creatUser(username, password);

        if(user != null) {
            //设置共享数据 request域
            request.setAttribute("username", username);
            request.getRequestDispatcher("/success").forward(request, response);
        } else {
            request.getRequestDispatcher("/fail").forward(request,response);
        }
//        //测试数据库连通性，已连通
//        if (user != null){
//            System.out.println("success....");
//        } else {
//            System.out.println("fail....");
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
