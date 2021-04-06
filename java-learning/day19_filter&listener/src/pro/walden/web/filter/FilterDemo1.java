package pro.walden.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "FilterDemo1", urlPatterns="/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class FilterDemo1 implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("FilterInit....");
    }

    public void destroy() {
        System.out.println("FilterDestroy....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        System.out.println("FilterDemo1....");
        //放行
        chain.doFilter(request, response);


    }
}
