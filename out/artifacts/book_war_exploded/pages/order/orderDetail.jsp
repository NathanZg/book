<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%--静态包含base标签、css样式、jQuery文件	--%>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">订单详情</span>
    <%--静态包含 manager管理员菜单			--%>
    <%@include file="/pages/common/manager_menu.jsp"%>
</div>
<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>单品总价</td>
        </tr>
        <c:forEach items="${requestScope.orderDetails}" var="orderDetail">
            <tr>
                <td>${orderDetail.name}</td>
                <td>${orderDetail.count}</td>
                <td>${orderDetail.price}</td>
                <td>${orderDetail.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%--静态包含 页脚	--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
