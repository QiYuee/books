<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.erdon.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/8
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        table{
            border: 1px black solid;
            width: 600px;
            border-collapse: collapse;
        }
        td,tr{
            border: 1px black solid;
        }
    </style>
</head>
<body>
<%
    List<User> userList = new ArrayList<User>();
    for (int i=0;i<10;i++)
    {
    userList.add(new User(i+1,"name"+(i+1),"pwd"+(i+1),"email"+(i+1)));
  }
    request.setAttribute("key",userList);
%>

<%--<table align="center">--%>
<%--    <%for (User user:userList) {%>--%>
<%--    <tr>--%>
<%--        <td><%=user.getId()%></td>--%>
<%--        <td><%=user.getUsername()%></td>--%>
<%--        <td><%=user.getPassword()%></td>--%>
<%--        <td><%=user.getEmail()%></td>--%>
<%--    </tr>--%>
<%--    <%}%>--%>
<table>
<c:forEach items="${requestScope.key}" var="user">
   <tr>  <td>id=${user.id}<br/></td>
    <td>name=${user.username}</td>
    <td>pwd=${user.password}</td>
    <td>email=${user.email}</td>
   </tr>
</c:forEach>
</table>
</body>
</html>
