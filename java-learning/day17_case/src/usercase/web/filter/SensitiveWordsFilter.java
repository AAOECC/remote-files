package usercase.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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

/**
 * 敏感词过滤
 */
@WebFilter(filterName = "SensitiveWordsFilter", urlPatterns = {"/addUserServlet","/updateUserServlet","/*"})
public class SensitiveWordsFilter implements Filter {
    private List<String> wordList = new ArrayList<String>();//存放敏感词

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //动态代理
        ServletRequest porxy_request = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //筛选执行的方法
                if (method.getName().equals("getParameter")) {
                    String str = (String) method.invoke(req, args);
                    //方法返回值增强
                    for (String word : wordList) {
                        if (str.contains(word)) {
                            str = str.replaceAll(word, "***");
                        }
                    }

                    return str;
                } else {
                    Object obj = method.invoke(req, args);
                    return obj;
                }
            }
        });

        chain.doFilter(porxy_request, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        //敏感次加载
        try {
            //1.找到文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //2.加载文件进内存
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //3。转换为list
            String str;
            while ((str = br.readLine())!= null) {
                wordList.add(str);
            }
            br.close();
            System.out.println(wordList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
