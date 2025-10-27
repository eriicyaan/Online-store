<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form action="${pageContext.request.contextPath}/logout" method="post">
    <button type="submit"><fmt:message key="user.logout"/></button>
</form>