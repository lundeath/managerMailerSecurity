<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">

<%@include file="/WEB-INF/tpls/headlinks.jsp" %>

<body>
<div class="container mt-3">
    <form class="float-left pr-5" action="/logout" method="post">
        <button type="submit" class="btn btn-primary">Sign out</button>
    </form>
    <h5 class="h4">Total users: ${total}</h5>
</div>

<div class="container mt-5 pt-2 text-center">
    <c:forEach var="user" items="${users}">
        <div class="py-3">
            <h5 class="display-4 text-center">${user.username}</h5>
        </div>
    </c:forEach>
</div>
</body>
</html>