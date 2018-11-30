<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="/WEB-INF/tpls/headlinks.jsp" %>

<body>
<c:if test="${param.error != null}">
    <div class="container">
        <p style="color: red">Invalid email or password.</p>
    </div>
</c:if>
<c:if test="${param.logout != null}">
<div class="container">
    <p>You have been logged out.</p>
</div>
</c:if>
<div style="width: 300px; height: 600px;" class="container mx-auto mt-5">
    <form action="/login" method="post">
        <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" required="required" class="form-control" id="email" name="email" aria-describedby="emailHelp"
                   placeholder="Email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" required="required" class="form-control" id="password" name="password" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <a class="pl-5" href="/forgot">Forgot password?</a>
        <br>
        <div class="float-right pt-2">
        <a href="/register">Register</a>
        </div>
    </form>
</div>
</body>
</html>