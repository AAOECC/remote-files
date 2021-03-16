package cookie;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "LoginCookie", urlPatterns="/loginCookie")
public class LoginCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        1.取得浏览器 cookie ，判断是否幼 名字为 lastTime 的cookie
        2.取得时间，生成 lastTime cookie 相应
         */
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        //表示是否第一测登录
        boolean isFirst = true;
        //取得现在时间
        Date date = new Date();
        String dateStr = date.getTime()+"";
        System.out.println(dateStr);
        //发送 cookie
        Cookie lastTime = new Cookie("lastTime", dateStr);
        lastTime.setMaxAge(60*60*24);
        response.addCookie(lastTime);

        //判断是否是第一次登录
        PrintWriter writer = response.getWriter();

        if(cookies != null){
            for (Cookie cookie : cookies) {
                if("lastTime".equals(cookie.getName())) {
                    isFirst = false;
                    String time = cookie.getValue();
                    System.out.println(time);
                    Long aLong = null;
                    aLong = Long.parseLong(time);
                    System.out.println("get : "+ aLong);
                    Date lastDate = new Date(aLong);
                    writer.println("<h1>欢迎回来，你的上次访问时间是"+lastDate.toLocaleString());
                    break;
                }
            }
        }
        if (isFirst){
            writer.println("<h1>欢迎你，首次访问！</h1>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

}
