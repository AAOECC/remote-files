package cn.itcast.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设置请求响应字符编码
 */
@WebFilter(urlPatterns = "/*")
public class CharchaterFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //转换为子类对象
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //获得请求方法
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            req.setCharacterEncoding("utf-8");
        }
        resp.setContentType("text/html;charset=utf-8");

        chain.doFilter(req, resp);
    }
}
