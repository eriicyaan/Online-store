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
            <c:forEach var="good" items="${requestScope.userGoods}">
                <img src="${pageContext.request.contextPath}/image?path=${good.imagePath}" width="200" alt="User image">
                <p>${good.name}</p>
                <p>${good.cost}</p>
                <form action="${pageContext.request.contextPath}/deleteBasket" method="post">
                    <input type="hidden" name="good_id" value="${good.id}">
                    <button type="submit">Удалить из корзины</button>
                </form>
            </c:forEach>
        </main>
    </body>
</html>
