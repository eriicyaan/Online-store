<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Basket</title>
    </head>

    <body>
        <header>
            <fmt:setLocale value="en_US"/>
            <fmt:setBundle basename="translation"/>

            <%@ include file="logout.jsp"%>
            <%@ include file="back.jsp"%>
        </header>
        <main>
            <c:if test="${not empty param.error}">
                <span style="color: darkred">Не хватает денег!</span><br>
            </c:if>

            <c:forEach var="good" items="${requestScope.userGoods}">
                <img src="${pageContext.request.contextPath}/image?path=${good.imagePath}" width="200" alt="User image">
                <p>${good.name}</p>
                <p>${good.cost}</p>
                <form action="${pageContext.request.contextPath}/deleteGoodFromBasket" method="post">
                    <input type="hidden" name="good_id" value="${good.id}">
                    <button type="submit">Удалить из корзины</button>
                </form>
                <form action="${pageContext.request.contextPath}/order" method="post">
                    <input type="hidden" name="good_id" value="${good.id}">
                    <button type="submit">Заказать</button>
                </form>
            </c:forEach>
        </main>
    </body>
</html>
