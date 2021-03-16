package com.example.web.request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;


/**
 * request 对象获取请求参数方法 测试
 */
@WebServlet(name = "RequestDemo2", value = "/requestDemo2")
public class RequestDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");


        Enumeration<String> names = request.getParameterNames();
        System.out.println(names);
        System.out.println("====================");
        //获取请求参数 Map
        Map<String, String[]> parameterMap = request.getParameterMap();
        //遍历请求参数 Map
        for (String name : parameterMap.keySet()) {
            System.out.println(name);
            String[] strings = parameterMap.get(name);

            for (String string : strings) {
                System.out.println(string);
            }
            System.out.println("----------------");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
