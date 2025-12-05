package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApplicationServlet
 */
@WebServlet(name = "/ApplicationServlet", urlPatterns = {"/homepage"})
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //Gets called on when the URL Pattern is met
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("YOU HAVE CALLED A GET METHOD FROM HEALTHNET_HOMEPAGE");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.html");
		dispatcher.forward(request, response);
	}

	//Gets called on when a form is submitted
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("YOU HAVE CALLED A POST METHOD FROM HEALTHNET_HOMEPAGE");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage.html");
		dispatcher.forward(request, response);
	}

}
