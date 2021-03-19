<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <%
        String path = request.getContextPath();
// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <base href="<%=basePath%>>">

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="updateUserServlet" method="post">
        <div style="display: none">
            <input type="text" name="id" value="${requestScope.user.id}">
        </div>
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name"  readonly="readonly" value="${requestScope.user.name}" />
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:choose>
                <c:when test="${'男'.equals(requestScope.user.gender)}">
                    <input type="radio" name="gender" value="男" checked="checked" />男
                    <input type="radio" name="gender" value="女"  />女
                </c:when>
                <c:when test="${'女'.equals(requestScope.user.gender)}">
                    <input type="radio" name="gender" value="男"  />男
                    <input type="radio" name="gender" value="女" checked="checked" />女
                </c:when>
                <c:otherwise>
                    <input type="radio" name="gender" value="男" checked="checked" />男
                    <input type="radio" name="gender" value="女"  />女
                </c:otherwise>
            </c:choose>
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${requestScope.user.age}" />
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="address">
                <option value="${requestScope.user.address}">${requestScope.user.address}</option>
                <option value="广东">广东</option>
                <option value="广西">广西</option>
                <option value="湖南">湖南</option>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" name="qq" id="qq" value="${requestScope.user.qq}"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" id="email" value="${requestScope.user.email}"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <a href="listServlet">
                <input class="btn btn-default" type="button" value="返回"/>
            </a>
        </div>
    </form>
</div>
</body>
</html>