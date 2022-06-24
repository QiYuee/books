<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/15
  Time: 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <a href="${requestScope.Page.url}?pageNo=1">首页</a>
    <c:if test="${requestScope.Page.pageNo>1}">
        <a href="${requestScope.Page.url}?pageNo=${requestScope.Page.pageNo-1}">上一页</a>
    </c:if>

    <a href="${requestScope.Page.url}?pageNo=${requestScope.Page.pageNo-1}">${requestScope.Page.pageNo>1?requestScope.Page.pageNo-1:""}</a>
    【${requestScope.Page.pageNo}】
    <a href="${requestScope.Page.url}?pageNo=${requestScope.Page.pageNo+1}">${requestScope.Page.pageNo<requestScope.Page.pageTotal?requestScope.Page.pageNo+1:""}</a>

    <c:if test="${requestScope.Page.pageNo<requestScope.Page.pageTotal}">
        <a href="${requestScope.Page.url}?pageNo=${requestScope.Page.pageNo+1}">下一页</a>
    </c:if>
    <a href="${requestScope.Page.url}?pageNo=${requestScope.Page.pageTotal}">末页</a>
    共${requestScope.Page.pageTotal}页，${requestScope.Page.pageCount}条记录 到第<input value="${requestScope.Page.pageNo}"
                                                                                name="pn" id="pn_input"/>页
    <input id="but" type="button" value="确定">
</div>
