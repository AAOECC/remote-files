package usercase.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "SensitiveWordFilter", urlPatterns = "/*")
public class SensitiveWordFilter implements Filter {
    private final List<String>  wordList = new ArrayList<String>();

    public void init(FilterConfig config) throws ServletException {
        try {
            //加载敏感词组
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("WEB-INF/classes/敏感词汇.txt");

            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line;
            while ((line = br.readLine())!=null){
                wordList.add(line);
            }

            System.out.println(wordList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //动态代理
        ServletRequest proxy_request = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断方法名
                if (method.getName().equals("getParameter")) {
                    //增强返回值
                    String value = (String) method.invoke(request, args);
                    System.out.println("方法已增强...");
                    if (value != null) {
                        for (String word : wordList) {
                            if (value.contains(word)) {
                                value = value.replaceAll(word, "***");
                            }
                        }
                    }
                    return value;
                } else {
                    return method.invoke(request, args);
                }


            }
        });

        chain.doFilter(proxy_request, response);
    }
}
