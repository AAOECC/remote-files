package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        1. 取得验证码
        2. 取得用户名，密码，封装数据
        3. 调用 UserService 查询数据库
        4. 响应消息
         */
        //响应消息对象
        ResultInfo info = new ResultInfo();
        //jackson json操作对象
        ObjectMapper mapper = new ObjectMapper();
        //验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String  checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        if (!checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            mapper.writeValue(response.getWriter(),info);
            return;
        }
        //验证码正确,调用 UserService 校验
        UserService userService = new UserServiceImpl();
        //获取数据
        User loginUser = new User();
        loginUser.setUsername(request.getParameter("username"));
        loginUser.setPassword(request.getParameter("password"));

        User db_user = userService.login(loginUser);
        //判断是否登陆成功
        if (db_user == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }else if ("N".equals(db_user.getStatus())) {
            info.setFlag(false);
            info.setErrorMsg("尚未激活，请激活");
        } else {
            info.setFlag(true);
        }
        //返回json
        mapper.writeValue(response.getOutputStream(),info);

    }
}
