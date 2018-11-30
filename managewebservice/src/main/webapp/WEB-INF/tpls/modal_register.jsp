<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="width: 300px; height: 600px;" class="container mx-auto mt-5">
    <form action="/register" method="post">
        <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" required="required" class="form-control" id="email" name="email" aria-describedby="emailHelp"
                   placeholder="Email">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" required="required" class="form-control" id="password" name="password" placeholder="Password">
        </div>
        <div class="form-group">
            <label for="confirm_password">Confirm Password</label>
            <input type="password" required="required" class="form-control" id="confirm_password" name="confirm_password" placeholder="Confirm Password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<script>
    var password = document.getElementById("password")
        , confirm_password = document.getElementById("confirm_password");

    function validatePassword() {
        if (password.value !== confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>