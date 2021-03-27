<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
                                                        <a style="margin-right: auto" href="/logout">Logout</a>


        Hello dear <%=session.getAttribute("user")%>;<br><br>

<a href="/secure/change-password.jsp">Change Password</a><br>


<a href="/secure/comments">Comments</a>

</body>
</html>
