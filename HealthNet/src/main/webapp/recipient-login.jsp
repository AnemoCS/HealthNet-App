<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Recipient Login</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header><h1>Recipient Login</h1></header>
    <div class="container">

        <!-- Display error message if sent by servlet -->
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                    <div class="error"><%= error %></div>
                <%
                    }
                %>

        <form action="recipientPortal" method = "post">
            <input type="hidden" name = "action" value = "login">
            <input type="email" placeholder="Email" name = "email" required>
            <input type="password" placeholder="Password"  name = "password" required>
            <button type="submit">Login</button>
        </form>
        <p><a href="recipient-create-account.jsp">Create Account</a></p>
        <p><a href="index.html">Back</a></p>
    </div>
</body>
</html>