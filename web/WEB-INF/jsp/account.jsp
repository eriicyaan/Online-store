<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Account</title>
    </head>
    <body>
        <header>
            <fmt:setLocale value="en_US"/>
            <fmt:setBundle basename="translation"/>

            <%@ include file="logout.jsp"%>
        </header>
        <main>
            <c:if test="${sessionScope.role == 'ADMIN'}">
                <a href="${pageContext.request.contextPath}/admin"><button type="button">Админ панель</button></a>
            </c:if>

            <c:if test="${sessionScope.role == 'USER'}">
                <a href="${pageContext.request.contextPath}/basket?user_id=${sessionScope.user.id}"><button>Корзина</button></a>
            </c:if>

            <a href="${pageContext.request.contextPath}/goods"><button type="button">Список товаров</button></a>
            <c:if test="${not empty requestScope.errors}">
                <div style="color: darkred">
                    <c:forEach var="error" items="${param.errors}">
                        <span>${error.description}</span>
                    </c:forEach>
                </div>
            </c:if>
        </main>
    </body>
</html>
