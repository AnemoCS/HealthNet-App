<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Recipient Account Creation</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header><h1>Create Recipient Account</h1></header>
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

        <form action="recipientPortal" method="post">
            <input type="hidden" name="action" value="register">
            <input type="text" placeholder="First Name" name = "firstName"
            value="${firstName != null ? firstName : ''}"  required>

            <input type="text" placeholder="Middle Name" name = "middleName"
            value="${middleName != null ? middleName : ''}" >

            <input type="text" placeholder="Last Name" name = "lastName"
             value="${lastName != null ? lastName : ''}"  required>

            <input type="text" placeholder="Name Prefix" name = "namePrefix"
            value="${namePrefix != null ? namePrefix : ''}" >

            <input type="date" placeholder="Date of Birth" name = "DOB"
            value="${DOB != null ? DOB : ''}" required>

            <input type="text" placeholder="Social Security Number" name = "SSN"
            value="${SSN != null ? SSN : ''}" required>

            <input type="text" placeholder="Street Address" name = "streetAddress"
             value="${streetAddress != null ? streetAddress : ''}" required>

            <input type="text" placeholder="Apartment Number" name = "aptNum"
            value="${aptNum != null ? aptNum : ''}" >

            <input type="text" placeholder="City" name = "city"
             value="${city != null ? city : ''}" required>

            <input type="text" placeholder="State" value="Missouri" readonly>

            <input type="text" placeholder="Zip Code" name = "zipCode"
             value="${zipCode != null ? zipCode : ''}" required>

            <input type="text" placeholder="Phone Number" name = "phoneNumber"
             value="${phoneNumber != null ? phoneNumber : ''}" required>

            <button type="submit">Submit</button>
            <a href="recipient-login.jsp"><button type="button">Back</button></a>
        </form>
    </div>
</body>
</html>
