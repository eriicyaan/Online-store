<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
        <fmt:setLocale value="en_US"/>
        <fmt:setBundle basename="translation"/>

        <form action="${pageContext.request.contextPath}/registration" method="post">
            <label><fmt:message key="user.registration.email"/>:
                <input type="text" name="email" required>
            </label><br>
            <label><fmt:message key="user.registration.password"/>:
                <input type="password" name="password" required>
            </label><br>
            <label><fmt:message key="user.registration.username"/>:
                <input type="text" name="username" required>
            </label><br>
            <select name="gender" required>
                <option value="MALE">Male</option><br>
                <option value="FEMALE">Female</option>
            </select><br>
            <label >
                <input type="radio" name="role" value="ADMIN" required>Admin<br>
                <input type="radio" name="role" value="USER" required>User
            </label><br>
            <button type="submit"><fmt:message key="user.button.registration"/></button><br>
            <c:if test="${not empty requestScope.errors}">
                <div style="color: darkred">
                    <c:forEach var="error" items="${param.errors}">
                        <span>${error.description}</span>
                    </c:forEach>
                </div>
            </c:if>
        </form>
    </body>
</html>