package usercase.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import usercase.domain.User;
import usercase.service.UserService;
import usercase.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "UpdateUserServlet", value = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //获取值
        Map<String, String[]> parameterMap = request.getParameterMap();
        User updateUser = new User();
        try {
            BeanUtils.populate(updateUser,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用 service 中的方法更改数据库
        UserService userService = new UserServiceImpl();
        boolean b = userService.updateUser(updateUser);
        if(b){
            response.sendRedirect(request.getContextPath()+"/listServlet");
        }
    }
}
