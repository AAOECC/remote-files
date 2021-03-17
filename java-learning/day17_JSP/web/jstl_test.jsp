<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="domain.User" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2021/3/17
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>案例：table+el+jstl</title>
</head>
<body>
    <%
        List list = new ArrayList();
        list.add(new User("张三", 23, new Date()));
        list.add(new User("李四", 24, new Date()));
        list.add(new User("王五", 25, new Date()));

        request.setAttribute("list", list);
    %>

    <table border="1 solid" align="center">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>生日</th>
        </tr>

        <c:forEach items="${list}" var="l" varStatus="s">

            <c:if test="${s.count%2 == 0}">
        <tr bgcolor="orange">
            </c:if>
            <c:if test="${s.count%2 == 1}">
                <tr bgcolor="#f5f5dc">
            </c:if>
            <td>${s.count}</td>
            <td>${l.name}</td>
            <td>${l.age}</td>
            <td>${l.birStr}</td>
        </tr>
        </c:forEach>

    </table>
</body>
</html>
