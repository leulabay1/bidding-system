<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/27/2024
  Time: 9:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Item" %>
<%! Item item; %>

<%
    // Retrieve the items from the request attribute
    item = (Item) request.getAttribute("item");
%>

<html>
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style/login.css">
</head>
<body>
<div class="form-container background-radial-gradient2">
    <h2>Bid Information</h2>
    <form action="bid" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="date">Date:</label>
            <input type="date" id="date" name="date" placeholder="YYYY-MM-DD" required>
            <span id="dateError" class="error"></span>
        </div>
        <div class="form-group">
            <label for="amount">Amount:</label>
            <input type="text" id="amount" name="amount" required>
            <span id="amountError" class="error"></span>
        </div>
        <div class="form-group">
            <input type="hidden" id="item_id" name="item_id" value="<%= item.getId()%>" required>
        </div>
        <div class="form-group">
            <label for="statement">Statement:</label>
            <textarea id="statement" name="statement" rows="4" required></textarea>
            <span id="statementError" class="error"></span>
        </div>
        <button type="submit">Submit</button>
    </form>

    <script>
        function validateForm() {
            var dateInput = document.getElementById("date");
            var amountInput = document.getElementById("amount");
            var statementInput = document.getElementById("statement");

            var dateError = document.getElementById("dateError");
            var amountError = document.getElementById("amountError");
            var statementError = document.getElementById("statementError");

            // Reset error messages
            dateError.innerHTML = "";
            amountError.innerHTML = "";
            statementError.innerHTML = "";

            // Perform validation
            var isValid = true;

            if (!dateInput.checkValidity()) {
                dateError.innerHTML = "Please enter a valid date (YYYY-MM-DD)";
                isValid = false;
            }

            if (!amountInput.checkValidity()) {
                amountError.innerHTML = "Please enter a valid amount";
                isValid = false;
            }

            if (!statementInput.checkValidity()) {
                statementError.innerHTML = "Please enter a valid statement";
                isValid = false;
            }

            // Add more validation checks for other fields if needed

            return isValid;
        }
    </script>
</div>

</body>
</html>
