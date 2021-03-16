<%@ page import="java.util.Date" %>
<%@ page import="javax.swing.text.DateFormatter" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: aaoec
  Date: 2021/3/14
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>lastTime</title>
</head>
<body>

    <%
        //获取时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String str_date = simpleDateFormat.format(date);
        //进行URL 编码
        str_date = URLEncoder.encode(str_date,"utf-8");
        //发送 cookie lastTime
        Cookie lastTime = new Cookie("lastTime", str_date);
        lastTime.setMaxAge(60*60*24*10);
        response.addCookie(lastTime);
    %>
    <%
        //判断是否是第一次登录
        boolean isFirst = true;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if("lastTime".equals(cookie.getName())){
                    isFirst = false;
                    String value = cookie.getValue();
                    String str_value = URLDecoder.decode(value, "utf-8");
                    %>
    <h1>欢迎回来！ 上次登录时间是<%=str_value%></h1>
    <%
                }
            }
        }
        if(isFirst){
            %>
    <h1>欢迎你，首次登陆</h1>
    <%
        }
    %>
</body>
</html>
