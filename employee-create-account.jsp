<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Employee Account - HealthNet</title>
    <link rel="stylesheet" href="css/styles.css">
</head>

<body>

    <!-- Page Header -->
    <header>
        <h1>Create Employee Account</h1>
    </header>

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

        <!-- Display success message if applicable -->
        <%
            String success = (String) request.getAttribute("success");
            if (success != null) {
        %>
            <div class="success"><%= success %></div>
        <%
            }
        %>

        <!-- Employee Form -->
        <form action="employeePortal" method="post">
            <input type="hidden" name="action" value="register">

            <input type="text" name="firstName" placeholder="First Name" 
       			value="${firstName != null ? firstName : ''}" required>

			<input type="text" name="lastName" placeholder="Last Name" 
       			value="${lastName != null ? lastName : ''}" required>

			<input type="text" name="SSN" placeholder="Social Security Number" 
       			value="${SSN != null ? SSN : ''}" required>
       			
            <select name="jobTitle">
    			<option value="Intern" ${jobTitle == 'Intern' ? 'selected' : ''}>Intern</option>
    			<option value="Associate" ${jobTitle == 'Associate' ? 'selected' : ''}>Associate</option>
    			<option value="Manager" ${jobTitle == 'Manager' ? 'selected' : ''}>Manager</option>
			</select>


            <button type="submit">Submit</button>
            <a href="employee-login.jsp"><button type="button">Back</button></a>
        </form>

    </div>

</body>
</html>
