<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/8
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
文件上传和下载
<form method="post" enctype="multipart/form-data" action="/books/upload" >
用户名:<input type="text" name="username"/><br/>
    头像:<input type="file" name="photo"/><br/>
    <input type="submit" value="上传"/>
</form>

</body>
</html>
