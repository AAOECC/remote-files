<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2021/3/17
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>jstl</title>
</head>
<body>
<%
    int number = 4;
    request.setAttribute("number", number);

    List list = new ArrayList();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");

    request.setAttribute("list",list);

%>
    <h2>if 标签</h2>
    <c:if test="${number == 3}">
        ${requestScope.number}为奇数
    </c:if>

    <h2>choose 标签</h2>
    <c:choose>
        <c:when test="${number %2 ==1}">
            ${number}为奇数
        </c:when>
        <c:when test="${number%2 == 0}">
            ${number}为偶数
        </c:when>
        <c:otherwise>
            ${number}有误
        </c:otherwise>
    </c:choose>

    <h2>foreach 标签</h2>

    <h3>遍历数组</h3>
    <c:forEach begin="0" end="10" var="i" step="1" varStatus="s">
        ${i} &nbsp; ${s.index} &nbsp;  ${s.count}<br>
    </c:forEach>

    <h3>遍历容器</h3>
    <c:forEach items="${list}" var="aList" varStatus="s">
        ${aList} &nbsp;&nbsp; ${s.index} &nbsp;&nbsp; ${s.count} <br>
    </c:forEach>

</body>
</html>
