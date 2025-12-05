package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AboutServlet
 */
@WebServlet(name = "/AboutServlet" , urlPatterns = {"/about"})
public class AboutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AboutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("YOU HAVE CALLED A GET METHOD FROM HEALTHNET_ABOUT");
		RequestDispatcher dispatcher = request.getRequestDispatcher("about.html");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("YOU HAVE CALLED A POST METHOD FROM HEALTHNET_ABOUT");
		RequestDispatcher dispatcher = request.getRequestDispatcher("about.html");
		dispatcher.forward(request, response);
	}

}
