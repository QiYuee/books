<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/8
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${1==1}或${1 eq 1}

${6/3}或${6 div 3}<br/>
${6%3}或${6 mod 3}<br/><br/><br/>

<%
request.setAttribute("key",null);
    request.setAttribute("key1","");
    request.setAttribute("key2",new Object[]{"123"});
    request.setAttribute("key3",(new ArrayList<>()).add("add"));
%>
<%--empty判断${}值是否为空}--%>
<%--1、字符串为null或""--%>
key值 = null:${ empty key}<br/>
key值 = "":${empty key1}<br/>
<%--数组没有数据--%>
key值 = null:${empty key2}<br/>
<%--集合没有数据--%>
key值 = "":${empty key3}<br
    ${header}
<h1>123124343</h1>
</body>
</html>
