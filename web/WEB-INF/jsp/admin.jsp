<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Admin Panel</title>
    </head>

    <header>
        <fmt:setLocale value="en_US"/>
        <fmt:setBundle basename="translation"/>
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit"><fmt:message key="user.logout"/></button>
        </form>
        <a href="${pageContext.request.contextPath}/account"><button><fmt:message key="user.back"/></button></a>
    </header>
    <body>
        <h1>Админ панель</h1>
        <form action="${pageContext.request.contextPath}/admin" method="post" enctype="multipart/form-data">
            <label>Name:
                <input type="text" name="name" required>
            </label><br>
            <label>Cost:
                <input type="text" name="cost" required>
            </label><br>
            <label>Image:
                <input type="file" name="image" required>
            </label><br>
            <button type="submit">Добавить товар</button>
        </form>
        <div style="color: darkred">
            <c:if test="${not empty requestScope.errors}">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${error.description}</span>
                </c:forEach>
            </c:if>
        </div>
    </body>
</html>
