package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "RegisterUserServlet", urlPatterns = "/registerUser")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        //创建返回对象函数
        ResultInfo info = new ResultInfo();
        //创建 json 操纵对象
        ObjectMapper mapper = new ObjectMapper();

        //校验验证码
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("CHECKCODE_SERVER");
        String regist_checkCode = request.getParameter("check");
        if (!checkCode.equalsIgnoreCase(regist_checkCode)) {
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误，请重试!");
            //封装对象返回
            mapper.writeValue(response.getWriter(),info);
            return;
        }
        //验证码正确,进行注册 ：
        //取得数据
        Map<String, String[]> userMap = request.getParameterMap();
        User registUser = new User();
        try {
            BeanUtils.populate(registUser,userMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


        //调用service服务进行注册
        UserService userService = new UserServiceImpl();
        boolean flag = userService.registUser(registUser);
        if (!flag) {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败");
            mapper.writeValue(response.getWriter(), info);
        } else {
            //注册成功

        }

    }
}
