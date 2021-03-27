<%@ page import="am.basic.jdbc.util.JspUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h1 style="color: red">
    <%=JspUtils.getAttribute(request, response, "message")%>
</h1>

<form action="/sign-up" method="post">
    Name     <input type="text" name="name"> <br/>
    Surname  <input type="text" name="surname"> <br/>
    Username <input type="text" name="username"> <br/>
    Password <input type="password" name="password"><br/>
    <input type="submit" name="Sign in">
</form>

</body>
</html>
