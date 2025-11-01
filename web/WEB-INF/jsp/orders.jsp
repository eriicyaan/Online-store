<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Orders</title>
    </head>
    <body>
        <header>
            <fmt:setLocale value="en_US"/>
            <fmt:setBundle basename="translation"/>

            <%@ include file="logout.jsp"%>
            <%@ include file="back.jsp"%>
        </header>
        <main>
            <c:forEach var="order" items="${requestScope.orders}">
                <img src="${pageContext.request.contextPath}/image?path=${order.imagePath}" width="200" alt="User image">
                <p>${order.name}</p>
                <p>${order.cost}</p>
            </c:forEach>
        </main>
    </body>
</html>
