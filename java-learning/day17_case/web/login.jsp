<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2021/3/17
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>管理员登陆</title>
<%--    导入样式及js--%>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
<%--    javascript代码段--%>
    <script type="text/javascript">
        window.onload = function () {
            var img = document.getElementById("vcode");
            img.onclick = function () {
                var date = new Date();
                var time = date.getTime();
                img.src = "${pageContext.request.contextPath}/verifyCodeServlet?"+time;
            }
        }
    </script>

    <style>
        .out-div{
            width: 400px;
        }
    </style>
</head>
<body>
    <div class="container out-div" >
        <h3 class="text-center">管理员登陆</h3>
        <div>
            <form action="${pageContext.request.contextPath}/loginServlet" method="post">
                <div class="form-group">
                    <label for="user-name-label">用户名:</label>
                    <input type="text" name="username" id="user-name-label" class="form-control" placeholder="请输入用户名">
                </div>
                <div class="form-group">
                    <label for="password-label">密码:</label>
                    <input type="password" name="password" id="password-label" class="form-control" placeholder="请输入密码">
                </div>
                <div class="form-inline">
                    <label for="verifyCode">验证码:</label>
                    <input type="text" name="verifyCode" id="verifyCode" class="form-control" placeholder="请输入验证码" style="width: 120px">
                    <img src="${pageContext.request.contextPath}/verifyCodeServlet" alt="看不清，点击刷新" id="vcode">
                </div>
                <hr>
                <div class="form-group text-center" >
                    <input type="submit" value="登录" class="btn btn-primary">
                </div>

<%--                出错信息显示框--%>
                <c:if test="${not empty requestScope.errorMsg}">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert">
                            <span>&times;</span>
                        </button>
                        <strong>${requestScope.errorMsg}</strong>
                    </div>
                </c:if>


            </form>
        </div>
    </div>

</body>
</html>
