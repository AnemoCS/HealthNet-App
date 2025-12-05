<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Recipient Login Creation</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header><h1>Create Recipient Login</h1></header>
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

        <form action = "recipientPortal" method = "post">
            <input type="hidden" name = "action" value = "registerLogin">
            <input type="hidden" name = "recipient" value = "${recipient != null ? recipient : ''}">
            <input type="email" placeholder="Email" name = "email"
             value="${email != null ? email : ''}" required>
            <input type="password" placeholder="Password"  name = "password"
             value="${password != null ? password : ''}"  required>
            <input type="password" placeholder="Confirm Password"  name = "confirmationPassword"
             value="${confirmationPassword != null ? confirmationPassword : ''}" required>
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>