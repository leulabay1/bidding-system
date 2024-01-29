<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Client" %>

<%
    Client client = (Client) session.getAttribute("user");
%>

<html>
<head>
    <title>Change Client Profile</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style/login.css">
</head>
<body>
<div class="form-container background-radial-gradient2">
<h2>Change Client Profile</h2>

<form action="client-edit-profile" method="post">
    <!-- Display current client information -->
    <img class="avatar" src="image/Client.jpg" alt="Profile Image">
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="<%= client.getName() %>" required>
    </div>

    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>

    <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="<%= client.getUsername() %>" required>
    </div>

    <div class="form-group">
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= client.getAddress() %>" required>
    </div>

    <div class="form-group">
        <label for="bankNum">Bank Number:</label>
        <input type="text" id="bankNum" name="bankNum" value="<%= client.getBankNum() %>" required>
    </div>

    <button type="submit" class="submit-button">Submit</button>
</form>
</div>
</body>
</html>
