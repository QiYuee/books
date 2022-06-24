<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/8
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    Map<String,Object> map = new HashMap<>();
    map.put("key1","map1");
    map.put("key2","map2");
    map.put("key3","map3");
%>
<body>
<%--<c:set scope="page" var="key" value="qweqwe"/>--%>
<%--<jsp:forward page="/test/test4.jsp"></jsp:forward>--%>
<%
    pageContext.setAttribute("key",map);
%>
<c:forEach items="${pageScope.key}" var="entry">
    <h1>${entry.key}=${entry.value}</h1>
</c:forEach>

</body>
</html>
