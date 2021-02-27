<%@ page import="am.basic.jdbc.model.User" %>
<%@ page import="am.basic.jdbc.util.JspUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>

<%
    if (request.getAttribute("user") == null){
        request.setAttribute("message","Please sign in for first");
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
%>

        Hello dear <%=JspUtils.getAttribute(request, "user", User.class)%>;
</body>
</html>
