<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/head.jsp"%>
</head>
<script type="text/javascript">
	$(function () {
		$("a.deleteClass").click(function () {
			return confirm("你确定要删除["+$(this).parent().parent().find("td:first").text()+"]吗？")
		})

		$("#but").click(function () {
			var page = $("#pn_input").val();
			if (page<=${requestScope.Page.pageTotal}){
				location.href = "${pageScope.basePath}manager/queryBooks?pageNo="+page;
			}
		})
	})
</script>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/pages/common/book_manager.jsp"%>
	</div>
<%--	${pageContext.request.setAttribute("pageLast",requestScope.Page.pageTotal)}--%>
<%--	${pageContext.request.setAttribute("pageNo",requestScope.Page.pageNo)}--%>
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.Page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>

					<td><a href="manager/getBook?id=${book.id}&pageNo=${requestScope.Page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/removeBook?id=${book.id}&pageNo=${requestScope.Page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>

			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=addBook&pageLast=${requestScope.Page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>
	</div>
<%--	<div id="page_nav">--%>
<%--		<a href="manager/bookController?pageNo=1&action=queryBooks">首页</a>--%>
<%--		<c:if test="${requestScope.Page.pageNo>1}">--%>
<%--			<a href="manager/bookController?pageNo=${requestScope.Page.pageNo-1}&action=queryBooks">上一页</a>--%>
<%--		</c:if>--%>

<%--		<a href="manager/bookController?pageNo=${requestScope.Page.pageNo-1}&action=queryBooks">${requestScope.Page.pageNo>1?requestScope.Page.pageNo-1:""}</a>--%>
<%--		【${requestScope.Page.pageNo}】--%>
<%--		<a href="manager/bookController?pageNo=${requestScope.Page.pageNo+1}&action=queryBooks">${requestScope.Page.pageNo<requestScope.Page.pageTotal?requestScope.Page.pageNo+1:""}</a>--%>

<%--		<c:if test="${requestScope.Page.pageNo<requestScope.Page.pageTotal}">--%>
<%--			<a href="manager/bookController?pageNo=${requestScope.Page.pageNo+1}&action=queryBooks">下一页</a>--%>
<%--		</c:if>--%>
<%--		<a href="manager/bookController?pageNo=${requestScope.Page.pageTotal}&action=queryBooks">末页</a>--%>
<%--		共${requestScope.Page.pageTotal}页，${requestScope.Page.pageCount}条记录 到第<input value="${requestScope.Page.pageNo}" name="pn" id="pn_input"/>页--%>
<%--		<input id="but" type="button" value="确定">--%>
<%--	</div>--%>
	<%@include file="/pages/common/page.jsp"%>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>