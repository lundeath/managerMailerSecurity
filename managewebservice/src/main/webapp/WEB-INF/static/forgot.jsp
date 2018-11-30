<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="/WEB-INF/tpls/headlinks.jsp" %>
<body>
<c:if test="${param.not_exists != null}">
    <div class="container">
        <p style="color: red">No user with such email exists!</p>
    </div>
</c:if>
<c:if test="${param.unavailable != null}">
    <div class="container">
        <p style="color: red">Sorry, service is unavailable.</p>
    </div>
</c:if>

<div style="width: 300px; height: 600px;" class="container mx-auto mt-5">
<form action="/update" method="get">
    <div class="form-group">
        <label for="email">Please, enter your email. We'll send you a temporary password.</label>
        <input type="email" required="required" class="form-control" id="email" name="email" aria-describedby="emailHelp"
               placeholder="Email">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</div>
</body>
</html>
