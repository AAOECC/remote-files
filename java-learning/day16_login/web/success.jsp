<%@ page import="domain.User" %><%--
  Created by IntelliJ IDEA.
  User: aaoec
  Date: 2021/3/14
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    String username = user.getUsername();
%>

    <h1>
        <%=username%>, 欢迎你！
    </h1>
</body>
</html>
