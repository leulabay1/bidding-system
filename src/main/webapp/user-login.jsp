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
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style/login.css">
</head>
<body>
    <div class="form-container background-radial-gradient2">
        <h2>User Login</h2>
        <form action="user-login" method="post" onsubmit="return validateLoginForm()">
            <div class="form-group">
                <input type="text" id="username" name="username" required>
                <span id="usernameError" class="error"></span>
            </div>
            <div class="form-group">
                <input type="password" id="password" name="password" required>
                <span id="passwordError" class="error"></span>
            </div>
            <button type="submit">Login</button>
        </form>

        <a href="user-signup.jsp">
            <p>
                Don't have an account?
            </p>
            <span class="link">
                sign up
            </span>
        </a>
        <%
            // Get the value of the 'error' parameter from the request
            String errorParam = request.getParameter("error");

            // Check if the 'error' parameter is present and has the value 'true'
            if (errorParam != null) {
        %>
        <p style="color: red;">Password or username doesn't match. Please try again.</p>
        <%
            }
        %>
    </div>

    <script>
        function validateLoginForm() {
            var usernameInput = document.getElementById("username");
            var passwordInput = document.getElementById("password");

            var usernameError = document.getElementById("usernameError");
            var passwordError = document.getElementById("passwordError");

            // Reset error messages
            usernameError.innerHTML = "";
            passwordError.innerHTML = "";

            // Perform validation
            var isValid = true;

            // Example validation for username (you can customize as needed)
            if (usernameInput.value.length < 3) {
                usernameError.innerHTML = "Username must be at least 3 characters long";
                isValid = false;
            }

            // Example validation for password (you can customize as needed)
            if (passwordInput.value.length < 4) {
                passwordError.innerHTML = "Password must be at least 6 characters long";
                isValid = false;
            }

            return isValid;
        }
    </script>
</body>
</html>
