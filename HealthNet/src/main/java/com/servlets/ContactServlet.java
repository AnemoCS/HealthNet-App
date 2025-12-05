package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet(name = "/ContactServlet", urlPatterns = "/contact")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ContactServlet() {
        super();
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to the contact form JSP
    	System.out.println("YOU HAVE CALLED A GET METHOD FROM HEALTHNET_CONTACT");
        RequestDispatcher dispatcher = request.getRequestDispatcher("contact.html");
        dispatcher.forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	/*
        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        // Simple server-side validation
        if (name == null || email == null || subject == null || message == null ||
            name.isEmpty() || email.isEmpty() || subject.isEmpty() || message.isEmpty()) {
            
            request.setAttribute("error", "All fields are required. Please fill out the form completely.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("missouriMedicaidContact.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // For testing — print submitted info to the server console
        System.out.println("Contact Form Submission:");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);

        // Set attributes for confirmation display in JSP
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("subject", subject);
        request.setAttribute("message", message);
        request.setAttribute("confirmation",
                "Thank you, " + name + ". Your message has been received. We’ll reach out to you at " + email + " soon.");

        // Forward the request back to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("missouriMedicaidContact.jsp");
        dispatcher.forward(request, response);
        
       */
    	System.out.println("YOU HAVE CALLED A POST METHOD FROM HEALTHNET_CONTACT");
        RequestDispatcher dispatcher = request.getRequestDispatcher("contact.html");
        dispatcher.forward(request, response);
    }
}
