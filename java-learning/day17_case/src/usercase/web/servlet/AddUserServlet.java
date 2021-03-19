package usercase.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import usercase.domain.User;
import usercase.service.UserService;
import usercase.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name="AddUserServlet", urlPatterns="/addUserServlet")
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //取得输入的数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装成User对象
        User addUser = new User();
        try {
            BeanUtils.populate(addUser,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service进行增加user
        UserService userService = new UserServiceImpl();
        boolean b = userService.addUser(addUser);
        //请求转发到到list.jsp页面
        response.sendRedirect(request.getContextPath()+"/listServlet");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
