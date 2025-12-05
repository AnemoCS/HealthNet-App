<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HealthNet Employee Portal</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header><h1>Employee Login</h1></header>
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
    
        <form action="login" method="post">
            <input type="email" placeholder="Email" name = "email" required>
            <input type="password" placeholder="Password" name = "password" required>
            <button type="submit">Login</button>
        </form>
        <p><a href="employee-create-account.jsp">Create Account</a></p>
        <p><a href="index.html">Back</a></p>
    </div>
</body>
</html>
