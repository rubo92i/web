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

<form action="/login" method="post">
    Username <input type="text" name="user-i-name"> <br/>
    Password <input type="password" name="user-i-pass"><br/>
    Remember <input type="checkbox" name="remember">
    <input type="submit" name="Sign in" value="Sign in">
</form>

<a href="/sign-up.jsp">Sign Up</a>

</body>
</html>
