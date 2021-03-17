<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2021/3/16
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el_test</title>
</head>
<body>
<%
    request.setAttribute("name", "bob");
    session.setAttribute("age","11");
%>
    ${3 == 4}
    <img src="<%=request.getContextPath()%>" alt="">
    <%=request.getContextPath()%>
<h3>EL get scope value</h3>
    ${requestScope.name}
    ${sessionScope.age}
<h3>el 获取对象</h3>
    ${pageContext.request.contextPath}
</body>
</html>
