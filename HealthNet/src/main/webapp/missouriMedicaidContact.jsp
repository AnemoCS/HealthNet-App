<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.Year" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <title>Health Net</title>
	
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400..700&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="HealthNet_Homepage.css">
    
    <style>
        main {
            max-width: 800px;
            margin: 40px auto;
            background-color: white;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            color: #003366;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-top: 5px;
            font-size: 1rem;
        }

        textarea {
            resize: vertical;
        }

        button {
            background-color: #003366;
            color: white;
            border: none;
            padding: 10px 20px;
            margin-top: 20px;
            font-size: 1rem;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #00509e;
        }

        .confirmation {
            background-color: #d1ffd1;
            border-left: 4px solid #007a00;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
        }

        .contact-info {
            background-color: #e8f0fe;
            border-left: 4px solid #003366;
            padding: 15px;
            margin-top: 20px;
            border-radius: 5px;
        }

        footer {
            text-align: center;
            color: #666;
            font-size: 0.9em;
            padding: 20px;
        }
    </style>
</head>
<body>

    <!-- MAIN HEADER -->
    <header class="mainHeader">
        <div class="header">
            <div class="logo">HealthNet</div>
            <nav>
                <ul>
                    <li><a href="Homepage">Home</a></li>
                    <li><a href="#">Log In</a></li>
                    <li><a href="HealthNet_About.html">About</a></li>
                    <li><a href="missouriMedicaidContact">Contact</a></li>
                    <li><a href="#">Help</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <!-- SECOND HEADER -->
    <div class="header2">
        <a href="HealthNet_Form.jsp">Fill out form here</a>
        <a href="#">FAQ</a>
    </div>
    
    <main>
        <h2>Contact Missouri Medicaid</h2>

        <% 
            String confirmation = (String) request.getAttribute("confirmation");
            String error = (String) request.getAttribute("error");
            String name = (String) request.getAttribute("name");
            String email = (String) request.getAttribute("email");
            String subject = (String) request.getAttribute("subject");
            String message = (String) request.getAttribute("message");

            if (error != null) {
        %>
            <div class="error"><%= error %></div>
        <%
            }

            if (confirmation != null) {
        %>
            <div class="confirmation">
                <strong><%= confirmation %></strong><br><br>
                <p><strong>Subject:</strong> <%= subject %></p>
                <p><strong>Your Message:</strong> <%= message %></p>
            </div>
            <a href="missouriMedicaidContact.jsp"><button>Submit Another Message</button></a>
        <%
            } else {
        %>
            <p>If you have questions or need assistance with your Missouri Medicaid benefits, please fill out the form below or use the contact information provided.</p>

            <form method="post" action="ContactServlet">
                <label for="name">Full Name:</label>
                <input type="text" name="name" id="name" required>

                <label for="email">Email Address:</label>
                <input type="email" name="email" id="email" required>

                <label for="subject">Subject:</label>
                <input type="text" name="subject" id="subject" required>

                <label for="message">Message:</label>
                <textarea name="message" id="message" rows="5" required></textarea>

                <button type="submit">Submit</button>
            </form>
        <%
            }
        %>

        <div class="contact-info">
            <h3>Missouri Medicaid Contact Information</h3>
            <p><strong>Phone:</strong> 1-800-392-2161</p>
            <p><strong>Office Hours:</strong> Monday - Friday, 8:00 a.m. - 5:00 p.m.</p>
            <p><strong>Mailing Address:</strong><br>
                MO HealthNet Division<br>
                P.O. Box 6500<br>
                Jefferson City, MO 65102
            </p>
            <p><strong>Website:</strong> 
                <a href="https://mydss.mo.gov/healthcare/mo-healthnet-for-people-with-disabilities" target="_blank">
                    Visit Missouri Medicaid Website
                </a>
            </p>
        </div>
    </main>

    <footer>
        &copy; <%= Year.now() %> Missouri Department of Social Services - MO HealthNet Division
    </footer>
</body>
</html>
    
	