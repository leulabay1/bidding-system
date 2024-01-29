<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/24/2024
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style/login.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;700&display=swap" rel="stylesheet">
</head>
<body>

<div class="form-container background-radial-gradient2">
    <h2>User Signup</h2>
    <form action="user-signup" method="post" onsubmit="return validateSignupForm()">
        <div class="form-group">
            <input type="text" id="name" name="name" required placeholder="Name">
            <span id="nameError" class="error"></span>
        </div>
        <div class="form-group">
            <input type="password" id="password" name="password" required placeholder="Password">
            <span id="passwordError" class="error"></span>
        </div>
        <div class="form-group">
            <input type="text" id="username" name="username" required placeholder="Username">
            <span id="usernameError" class="error"></span>
        </div>
        <div class="form-group">
            <input type="text" id="address" name="address" required placeholder="Address">
            <span id="addressError" class="error"></span>
        </div>
        <button type="submit">Signup</button>
    </form>

    <a href="user-login.jsp">
        <p>
            Already have an account?
        </p>
        <span class="link">
                Login
            </span>
    </a>
    <%
        // Get the value of the 'error' parameter from the request
        String errorParam = request.getParameter("error");

        // Check if the 'error' parameter is present and has the value 'true'
        if (errorParam != null) {
    %>
    <p style="color: red;">An error occurred. Please try again.</p>
    <%
        }
    %>
</div>

<script>
    function validateSignupForm() {
        var nameInput = document.getElementById("name");
        var passwordInput = document.getElementById("password");
        var usernameInput = document.getElementById("username");
        var addressInput = document.getElementById("address");

        var nameError = document.getElementById("nameError");
        var passwordError = document.getElementById("passwordError");
        var usernameError = document.getElementById("usernameError");
        var addressError = document.getElementById("addressError");

        // Reset error messages
        nameError.innerHTML = "";
        passwordError.innerHTML = "";
        usernameError.innerHTML = "";
        addressError.innerHTML = "";

        // Perform validation
        var isValid = true;

        // Example validation for name (you can customize as needed)
        if (nameInput.value.trim() === "") {
            nameError.innerHTML = "Name is required";
            isValid = false;
        }

        // Example validation for password (you can customize as needed)
        if (passwordInput.value.length < 4) {
            passwordError.innerHTML = "Password must be at least 6 characters long";
            isValid = false;
        }

        // Example validation for username (you can customize as needed)
        if (usernameInput.value.trim() === "") {
            usernameError.innerHTML = "Username is required";
            isValid = false;
        }

        // Example validation for address (you can customize as needed)
        if (addressInput.value.trim() === "") {
            addressError.innerHTML = "Address is required";
            isValid = false;
        }

        return isValid;
    }
</script>

</body>
</html>
