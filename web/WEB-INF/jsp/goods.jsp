<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Goods</title>
    </head>
    <body>
        <header>
            <fmt:setLocale value="en_US"/>
            <fmt:setBundle basename="translation"/>

            <%@ include file="logout.jsp"%>
            <%@ include file="back.jsp" %>
        </header>
        <main>
            <c:forEach var="good" items="${requestScope.goods}">
                <img src="${pageContext.request.contextPath}/image?path=${good.imagePath}" width="200">
                <p>${good.name}</p>
                <p>${good.cost}</p>
                <c:if test="${sessionScope.role == 'USER'}">
                    <form action="${pageContext.request.contextPath}/basket" method="post">
                        <input type="hidden" name="good_id" value="${good.id}">
                        <button type="submit">Добавить в корзину</button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <form action="${pageContext.request.contextPath}/deleteGoodServlet" method="post">
                        <input type="hidden" name="good_id" value="${good.id}">
                        <input type="hidden" name="image_path" value="${good.imagePath}">
                        <button type="submit">Удалить товар</button>
                    </form>
                </c:if>
            </c:forEach>
        </main>
    </body>
</html>
