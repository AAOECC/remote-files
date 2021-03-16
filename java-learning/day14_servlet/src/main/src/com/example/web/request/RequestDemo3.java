package com.example.web.request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * request 对象请求转发 及
 *          共享域
 *          servlet 1
 */
@WebServlet(name = "RequestDemo3", value = "/RequestDemo3")
public class RequestDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo3 is visited....");
        //转发前设置共享数据 request域
        request.setAttribute("msg", "hahaha....");

        //设置request 转发
        request.getRequestDispatcher("RequestDemo4").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
