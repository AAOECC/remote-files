<%--
  Created by IntelliJ IDEA.
  User: aaoec
  Date: 2021/3/14
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <script>
        window.onload = function () {
            var vali_img = document.getElementById('vali_img');
            vali_img.onclick = function () {
                var date = new Date();
                var time = date.getTime();
                vali_img.src = "${pageContext.request.contextPath}/valiCode?"+time;
            }

        }

    </script>

</head>
<body>

<%
    Object statue = session.getAttribute("statue");
    System.out.println("status : "+statue);
%>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="user-name-label">username : </label>
        <input type="text" id="user-name-label" name="username"> <br>
        <label for="password-label">password : </label>
        <input type="text" id="password-label" name="password"> <br>
        <img src="${pageContext.request.contextPath}/valiCode" alt="" id="vali_img">
        <br>
        <label for="valiCode"></label>
        <input type="text" id="valiCode" name="valiCode"> <br>
        <%
            statue = session.getAttribute("statue");
            System.out.println("status : "+statue);
            if("valiCode".equals(statue)){
        %>
        <p style="color: red">验证码错误</p>
        <%
            }else if("user_fail".equals(statue)){
        %>
        <p style="color: red">用户名或密码错误</p>
        <%
            }
        %>
        <input type="submit" value="提交">

    </form>



</body>
</html>
