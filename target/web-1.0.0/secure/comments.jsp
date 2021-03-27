<%@ page import="am.basic.jdbc.model.Comment" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ruben.manukyan
  Date: 3/6/2021
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


 <form action="/secure/comments/add" method="post">
     Title   <input type="text" name="title">
     Content <input type="text" name="content">
     <input type="submit" value="add">
 </form>

 <table style="margin : auto">

        <% List<Comment> commentList = (List<Comment>) request.getAttribute("comments");

            for (Comment comment : commentList){
        %>
            <tr>
                <td><%=comment.getId()%></td>
                <td><%=comment.getTitle()%></td>
                <td><%=comment.getContent()%></td>
                <td><form action="/secure/comments/delete" method="post"><input type="hidden" name="id" value="<%=comment.getId()%>"> <input type="submit" value="DELETE"></form> </td>
            </tr>
        <% } %>

 </table>


</body>
</html>
