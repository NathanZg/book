<%--
  Created by IntelliJ IDEA.
  User: sanmu
  Date: 2022/1/31
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNum > 1}">
        <a href="${requestScope.page.url}&pageNum=1">首页</a>
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageNum-1}">上一页</a>
    </c:if>
    <%--页码输出--%>
    <c:choose>
        <%--总页码数小于五 页码是1-总页码--%>
        <c:when test="${ requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--总页码数大于5--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--当前页码是前三页--%>
                <c:when test="${requestScope.page.pageNum <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--当前页码最后三页--%>
                <c:when test="${requestScope.page.pageNum > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--当前页码不是前三页，不是后三页--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNum-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNum+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <%--输出页码--%>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNum}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNum}">
            <a href="${requestScope.page.url}&pageNum=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <c:if test="${requestScope.page.pageNum < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageNum+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNum}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function (){
            $("#searchPageBtn").click(function (){
                var pageNum = $("#pn_input").val();
                var pageTotal = ${requestScope.page.pageTotal};
                //JavaScript提供一个location地址栏对象
                //它有个一属性href可以获取浏览器地址栏的地址
                //可读可写
                if(pageNum >=1 && pageNum <= pageTotal)
                    location.href="${pageContext.getAttribute("basePath")}${requestScope.page.url}&pageNum="+pageNum;
            })
        })
    </script>
</div>
