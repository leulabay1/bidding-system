<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/24/2024
  Time: 11:55 AM
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
    <h2>Create Item</h2>
    <form action="items" method="post" onsubmit="return validateItemsForm()">
        <div class="form-group">
            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate" required>
            <span id="endDateError" class="error"></span>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" required></textarea>
            <span id="descriptionError" class="error"></span>
        </div>
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>
            <span id="titleError" class="error"></span>
        </div>
        <div class="form-group">
            <label for="imageUrl">Image:</label>
            <input type="text" id="imageUrl" name="imageUrl" required>
            <span id="imageUrlError" class="error"></span>
        </div>
        <button type="submit">Create Item</button>
    </form>
</div>

<script>
    function validateItemsForm() {
        var endDateInput = document.getElementById("endDate");
        var descriptionInput = document.getElementById("description");
        var titleInput = document.getElementById("title");
        var imageUrlInput = document.getElementById("imageUrl");

        var endDateError = document.getElementById("endDateError");
        var descriptionError = document.getElementById("descriptionError");
        var titleError = document.getElementById("titleError");
        var imageUrlError = document.getElementById("imageUrlError");

        // Reset error messages
        endDateError.innerHTML = "";
        descriptionError.innerHTML = "";
        titleError.innerHTML = "";
        imageUrlError.innerHTML = "";

        // Perform validation
        var isValid = true;

        // Example validation for end date (you can customize as needed)
        if (!endDateInput.checkValidity()) {
            endDateError.innerHTML = "Please enter a valid end date";
            isValid = false;
        }

        // Example validation for description (you can customize as needed)
        if (descriptionInput.value.trim() === "") {
            descriptionError.innerHTML = "Description is required";
            isValid = false;
        }

        // Example validation for title (you can customize as needed)
        if (titleInput.value.trim() === "") {
            titleError.innerHTML = "Title is required";
            isValid = false;
        }

        // Example validation for image URL (you can customize as needed)
        if (imageUrlInput.value.trim() === "") {
            imageUrlError.innerHTML = "Image URL is required";
            isValid = false;
        }

        return isValid;
    }
</script>

</body>
</html>
