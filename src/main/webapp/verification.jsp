<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page import="am.basic.jdbc.util.JspUtils" %><%--
  Created by IntelliJ IDEA.
  User: ruben.manukyan
  Date: 3/2/2021
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<h1 style="color: red">${message}</h1>


<form method="post" action="/verify">
    Verification Code <input type="text" name="code"><br>
    <input type="hidden" name="username" value="${username}">
    <input type="submit" name="Verify" value="Verify">
</form>


<br>

<form method="post" action="/resend">
    <input type="hidden" name="username" value="${username}">
    <input type="submit" name="Resend" value="Resend">
</form>
</body>
</html>
