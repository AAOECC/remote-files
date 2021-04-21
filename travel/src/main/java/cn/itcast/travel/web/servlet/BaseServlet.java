package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    /**
     * 进行 方法分发
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
          1.获取请求uri
          2.获取方法名称
          3.获取方法执行体
          4.分发方法执行
         */
        //1
        String requestURI = req.getRequestURI();
        //2
        String method_name = requestURI.substring(requestURI.lastIndexOf('/') + 1);
        try {
            //3.
            Method method = this.getClass().getMethod(method_name, HttpServletRequest.class, HttpServletResponse.class);
            //4
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 obj 以 json 方式返回
     * @param obj
     * @param response
     * @throws IOException
     */
    public void writeValueByJson(Object obj, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), obj);
    }

    /**
     * 生成 obj 对象的 json 字符串
     * @param obj
     * @return
     * @throws IOException
     */
    public String writeValueAsString(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
