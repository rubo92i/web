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

<form action="/secure/change-password" method="post">
    Old password <input type="password" name="oldPassword"><br/>
    New Password <input type="password" name="newPassword">
    <input type="submit" name="Sign in" value="Change">
</form>

<a href="/secure/home.jsp">Home</a>

</body>
</html>
