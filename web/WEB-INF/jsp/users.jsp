<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <title>All users</title>
    </head>
    <body>
        <header>
            <fmt:setLocale value="en_US"/>
            <fmt:setBundle basename="translation"/>

            <%@ include file="logout.jsp"%>
            <%@ include file="back.jsp"%>
        </header>

        <main>
            <table>
                <thead>
                    <tr>
                        <th>Имя пользователя</th>
                        <th>Пароль</th>
                        <th>Почта</th>
                        <th>Роль</th>
                        <th>Пол</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${requestScope.users}">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.password}</td>
                            <td>${user.email}</td>
                            <td>${user.role}</td>
                            <td>${user.gender}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
    </body>
</html>
