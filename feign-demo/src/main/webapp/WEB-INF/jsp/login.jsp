<%--
  Created by IntelliJ IDEA.
  User: wjj
  Date: 2018/3/13
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>欢迎登录!</h1>
    错误信息：<h4 th:text="${msg}"></h4>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        <input type="text" name="username"><br>
        <input type="password" name="password"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
