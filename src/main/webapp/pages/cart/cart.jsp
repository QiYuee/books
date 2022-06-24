<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
</head>
<script type="text/javascript">
	$(function () {
	$(".deleteItem").click(function () {
		var bookName = $(this).attr("bookName");
		return confirm("您确定要将 ["+bookName+"] 移出购物车吗?");
	})
		$(".closeItems").click(function () {
			return confirm("您确定要清空购物车吗?");
		})
		$(".updateItem").change(function () {
			var bookName = $(this).parent().parent().find("td:first").text();
			var count = this.value
			var itemId = $(this).attr("bookId");
			if (confirm("您确定要修改 【"+bookName+"】 商品的数量修改为"+count+"吗?")) {
				location.href = "${basePath}cart/updateCartItem?id="+itemId+"&countItem="+count;
			}else this.value=this.defaultValue;
		})
	})
</script>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">

		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="item">
					<tr>
						<td>${item.value.gName}</td>
						<td>
							<input bookId="${item.value.gId}"  class="updateItem" style="width: 50px" type="text" value="${item.value.gNumber}"/>
						</td>
						<td>${item.value.gPrice}</td>
						<td>${item.value.gTotalPrice}</td>
						<td><a bookName="${item.value.gName}" class="deleteItem" href="cart/deletCartItem?id=${item.key}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty sessionScope.cart.items}">
				<td colspan="5"> <a>亲，当前购物车还没有商品，先去选购商品吧！ </a></td>
			</c:if>

		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.count!=null?sessionScope.cart.count:0}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice!=null?sessionScope.cart.totalPrice:0}</span>元</span>
				<span class="cart_span"><a class="closeItems" href="cart/clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="order/createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>