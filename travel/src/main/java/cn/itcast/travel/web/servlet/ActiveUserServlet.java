package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ActiveUserServlet", urlPatterns = "/activeUser")
public class ActiveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        //返回消息
        String msg = null;
        //判断激活码是否为空
        if (code != null) {
            //调用 service 方法进行激活
            UserService userService = new UserServiceImpl();
            int flag = userService.active(code);
            if (flag == 1) {
                msg = "激活成功，点击<a href='http://localhost/travel/login.html'>登陆</a>";
            } else if (flag == 0){
                msg = "激活失败，请重新点击邮件链接";
            }
        } else {
            //激活码为空
            msg = "请点击邮件链接进行激活";
        }
        response.getWriter().write(msg);
    }
}
