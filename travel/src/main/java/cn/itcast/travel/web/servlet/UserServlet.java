package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends BaseServlet {

    private final ObjectMapper mapper = new ObjectMapper();
    private final UserService userService = new UserServiceImpl();

    /**
     * 用户登录
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
        1. 取得验证码
        2. 取得用户名，密码，封装数据
        3. 调用 UserService 查询数据库
        4. 响应消息
         */
        //响应消息对象
        ResultInfo info = new ResultInfo();
        //jackson json操作对象
        //ObjectMapper mapper = new ObjectMapper();
        //验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        if (!checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            mapper.writeValue(response.getWriter(), info);
            return;
        }
        //验证码正确,调用 UserService 校验
        //UserService userService = new UserServiceImpl();
        //获取数据
        User loginUser = new User();
        loginUser.setUsername(request.getParameter("username"));
        loginUser.setPassword(request.getParameter("password"));

        User db_user = userService.login(loginUser);
        //判断是否登陆成功
        if (db_user == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        } else if ("N".equals(db_user.getStatus())) {
            info.setFlag(false);
            info.setErrorMsg("尚未激活，请激活");
        } else {
            info.setFlag(true);
            //封装 user 信息到 session 中
            request.getSession().setAttribute("user", db_user);
        }
        //返回json
        mapper.writeValue(response.getOutputStream(), info);

    }

    /**
     * 注册用户
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        //创建返回对象函数
        ResultInfo info = new ResultInfo();
        //创建 json 操纵对象
        //ObjectMapper mapper = new ObjectMapper();

        //校验验证码
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("CHECKCODE_SERVER");
        String regist_checkCode = request.getParameter("check");
        if (!checkCode.equalsIgnoreCase(regist_checkCode)) {
            //验证码错误
            info.setFlag(false);
            info.setErrorMsg("验证码错误，请重试!");
            //封装对象返回
            mapper.writeValue(response.getWriter(), info);
            return;
        }
        //验证码正确,进行注册 ：
        //取得数据
        Map<String, String[]> userMap = request.getParameterMap();
        User registUser = new User();
        try {
            BeanUtils.populate(registUser, userMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


        //调用service服务进行注册
        //UserService userService = new UserServiceImpl();
        boolean flag = userService.registUser(registUser);
        if (!flag) {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败");
            mapper.writeValue(response.getWriter(), info);
        } else {
            //注册成功
            info.setFlag(true);
            mapper.writeValue(response.getWriter(), info);

        }
    }

    /**
     * 查找用户是否登陆
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void findUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        //ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), user);
    }

    /**
     * 激活用户
     *
     * @param request
     * @param response
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        //返回消息
        String msg = null;
        //判断激活码是否为空
        if (code != null) {
            //调用 service 方法进行激活
            //UserService userService = new UserServiceImpl();
            int flag = userService.active(code);
            if (flag == 1) {
                msg = "激活成功，点击<a href='http://localhost/travel/login.html'>登陆</a>";
            } else if (flag == 0) {
                msg = "激活失败，请重新点击邮件链接";
            }
        } else {
            //激活码为空
            msg = "请点击邮件链接进行激活";
        }
        response.getWriter().write(msg);
    }

    /**
     * 退出登陆
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index.html");
    }
}

