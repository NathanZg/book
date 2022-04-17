<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含base标签、css样式、jQuery文件	--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			//删除绑定单击事件
			$("a.deleteItem").click(function (){
				return confirm("您确定要删除["+$(this).parent().parent().find("td:first").text()+"]吗？");
			});
			//清空购物车绑定单击事件
			$("#clearCart").click(function (){
				return confirm("您确定要清空购物车吗？");
			});
			//给输入框绑定改变事件
			$(".updateCount").change(function (){
				var name = $(this).parent().parent().find("td:first").text();
				var newCount = this.value;
				var id = $(this).attr("bookId");
				if(newCount==0){
					if(confirm("是否要删除["+name+"]?")){
						location.href="${basePath}cartServlet?action=deleteItem&id="+id;
					}else{
						this.value = this.defaultValue;
					}
				}else if(newCount < 0){
					this.value = this.defaultValue;
				}else{
					location.href="${basePath}cartServlet?action=updateCount&count="+newCount+"&id="+id;
				}
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<div>
			<%--用户未登陆--%>
			<c:if test="${empty sessionScope.user}">
				<a href="pages/user/login.jsp">登录</a> |
				<a href="pages/user/regist.jsp">注册</a> |
				<a href="index.jsp">返回</a>
			</c:if>
			<%--已经登陆--%>
			<c:if test="${not empty sessionScope.user}">
				<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
				<a href="pages/order/order.jsp">我的订单</a>
				<a href="userServlet?action=logout">注销</a>&nbsp;
			</c:if>
		</div>
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
			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input class="updateCount" bookId="${entry.value.id}" style="width: 30px; text-align:center" type="text" value="${entry.value.count}">
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totoalPrice}</td>
					<td><a class="deleteItem"href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲,当前购物车为空，赶紧去浏览购买宝贝吧！</a></td>
				</tr>
			</c:if>
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span id="clearCart" class="cart_span"><a href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>

	<%--静态包含 页脚	--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>