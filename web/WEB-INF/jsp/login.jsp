<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <fmt:setLocale value="en_US"/>
        <fmt:setBundle basename="translation"/>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <label><fmt:message key="user.registration.email"/>:
                <input type="text" name="email" required>
            </label><br>
            <label><fmt:message key="user.registration.password"/>:
                <input type="password" name="password" required>
            </label><br>
            <button type="submit"><fmt:message key="user.button.login"/></button>
            <a href="${pageContext.request.contextPath}/registration"><button type="button"><fmt:message key="user.button.registration"/></button></a><br>
            <span style="color: darkred">
                <c:if test="${not empty param.error}">
                    <fmt:message key="user.login.error"/>
                </c:if>
            </span>
        </form>
    </body>
</html>
