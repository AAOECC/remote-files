package usercase.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns="/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //0.类型强转为 HttpServlet
        HttpServletRequest req = (HttpServletRequest) request;

        //1.查询请求资源
        String requestURI = req.getRequestURI();
        if(requestURI.contains("Login.jsp") || requestURI.contains("loginServlet") || requestURI.contains("verifyCodeServlet")) {
            chain.doFilter(request,response);
        } else {
            //2.查询 Session 中的 isLogin 值
            HttpSession session = req.getSession();
            Object isLogin = session.getAttribute("isLogin");


            if (isLogin != null && (boolean)isLogin) {
                //已经登陆
                chain.doFilter(request,response);
            } else {
                //尚未登陆
                request.setAttribute("errorMsg","您尚未登陆，请先登陆");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }


        //chain.doFilter(request, response);
    }
}
