<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>

<%
    User user = (User) session.getAttribute("user");
%>

<html>
<head>
    <title>Change User Profile</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style/login.css">
</head>
<body>
<div class="form-container background-radial-gradient">
<h2>Change User Profile</h2>

<form action="user-edit-profile" method="post">
    <!-- Display current user information -->
    <img class="avatar" src="image/user.jpg" alt="Profile Image">
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="<%= user.getName() %>" required>
    </div>

    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="<%= user.getPassword() %>"  required>
    </div>

    <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="<%= user.getUsername() %>" required>
    </div>

    <div class="form-group">
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= user.getAddress() %>" required>
    </div>

    <button type="submit" class="submit-button">Submit</button>
</form>
</div>
</body>
</html>
