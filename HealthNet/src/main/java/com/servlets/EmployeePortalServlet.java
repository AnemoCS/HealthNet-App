package com.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.Base64;

import com.dao.EmployeeDAO;
import com.dao.PasswordUtils;
import com.objects.Employee;
import com.objects.EmployeeController;

@WebServlet(name = "/EmployeePortalServlet", urlPatterns = {"/employeePortal", "/login", "/register"})
public class EmployeePortalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("employeePortal.jsp");
        dispatcher.forward(request, response);
        System.out.println("YOU HAVE CALLED A GET METHOD IN EMPLOYEEPORTAL");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("YOU HAVE CALLED A POST METHOD IN EMPLOYEEPORTAL");
        String action = request.getParameter("action");
        
        if ("register".equalsIgnoreCase(action)) {
            try {           	
				registerEmployee(request, response);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if ("registerlogin".equalsIgnoreCase(action)) {
            try {           	
            	registerEmployeeCredentials(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        } else if ("login".equalsIgnoreCase(action)) {
            try {           	
				loginEmployee(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
        	try {           	
				loginEmployee(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    private void registerEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    	System.out.println("Inside register employee");
    	String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String jobTitle = request.getParameter("jobTitle");
        String SSN = request.getParameter("SSN");

        if (isBlank(firstName) || isBlank(lastName) || isBlank(jobTitle) || isBlank(SSN)) {

        	returnDefaultValues(firstName, lastName, jobTitle, SSN, request);
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("employee-create-account.jsp").forward(request, response);
            return;
            
        }  else if ( !SSN.matches("\\d{9}")) {
        	
        	returnDefaultValues(firstName, lastName, jobTitle, SSN, request);
            request.setAttribute("error", "SSN must be exactly 9 digits and contain only numbers.");
            request.getRequestDispatcher("employee-create-account.jsp").forward(request, response);
            return;
            
        }
        
        EmployeeDAO employeeDAO = new EmployeeDAO();
        boolean isValid = employeeDAO.validateSSN(SSN);
        
        if (!isValid) {
        	
        	returnDefaultValues(firstName, lastName, jobTitle, SSN, request);
            request.setAttribute("error", "A user already has that SSN");            
            request.getRequestDispatcher("employee-create-account.jsp").forward(request, response);
            return;
        }
                 
        Employee employee = new Employee( firstName, lastName, jobTitle, SSN);
        employeeDAO.addEmployee(employee);   
        int employeeID = employeeDAO.getEmployeeID(employee); 
        
        HttpSession session = request.getSession();
        session.setAttribute("employeeID", employeeID);
        response.sendRedirect("employee-create-login.jsp");
        

        
        
        
        
    }
    
    private void registerEmployeeCredentials(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    	
    	//Gathers form data
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmationPassword = request.getParameter("confirmationPassword");
        EmployeeDAO employeeDAO = new EmployeeDAO();

        if ( isBlank(email) || isBlank(password) || isBlank(confirmationPassword)) {
        	returnEmployeeCredentials( email, password, confirmationPassword, request);
            request.setAttribute("error", "All feilds must be filled");
            request.getRequestDispatcher("employee-create-login.jsp").forward(request, response);
            return;
        } else if (!employeeDAO.validateEmail( email)) {
        	returnEmployeeCredentials( email, password, confirmationPassword, request);
        	request.setAttribute("error", "That email is already taken.");
            request.getRequestDispatcher("employee-create-login.jsp").forward(request, response);
            return;
        }  else if (password.length() < 15) {
        	returnEmployeeCredentials( email, password, confirmationPassword, request);
        	request.setAttribute("error", "Password must be 16 character long");
            request.getRequestDispatcher("employee-create-login.jsp").forward(request, response);
            return;
        } else if (!(password.equals(confirmationPassword))) {
        	returnEmployeeCredentials( email, password, confirmationPassword, request);
        	request.setAttribute("error", "The two passwords must match");
            request.getRequestDispatcher("employee-create-login.jsp").forward(request, response);
            return;
        }

        
        
        HttpSession session = request.getSession(false);
        // Call your DAO
        int employeeID = (int) session.getAttribute("employeeID");
        System.out.print(employeeID);
        
        
        //Generate salt and hash password
        byte[] saltBytes = new byte[16];
        new SecureRandom().nextBytes(saltBytes);
        String salt = Base64.getEncoder().encodeToString(saltBytes);
        String hashedPassword = PasswordUtils.hashPassword(password, salt);
        
        EmployeeController controller = new EmployeeController( employeeID, hashedPassword,  salt,  email);
        employeeDAO.addEmployeeCredentials(controller);
        
        request.setAttribute("error", "Sucess! Your account has been created, " + email);
        request.getRequestDispatcher("employee-dashboard.jsp").forward(request, response);
        
    }
 
    
    private void loginEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//Gathers form data
        String email = request.getParameter("login_email");
        String password = request.getParameter("login_password");

        if (isBlank(email) || isBlank(password)) {
            request.setAttribute("error", "Email and password are required.");
            request.getRequestDispatcher("employee-login.jsp").forward(request, response);
            return;
        } else if (password.length() < 15) {
        	request.setAttribute("error", "Password must be 16 character long");
            request.getRequestDispatcher("employee-login.jsp").forward(request, response);
            return;
        }

        // Call your DAO
        EmployeeDAO employeeDAO = new EmployeeDAO();
        boolean isValid = employeeDAO.validateLogin(email, password);

        if (isValid) {
            request.setAttribute("confirmation", "Welcome back, " + email);
        } else {
            request.setAttribute("error", "Invalid email or password.");
        }

        request.getRequestDispatcher("employee-dashboard.jsp").forward(request, response);
    }
    
    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
    
    public void returnDefaultValues(String firstName, String lastName, String jobTitle, String SSN, HttpServletRequest request) {
    	// Return the partially-correct data
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("jobTitle", jobTitle);
        request.setAttribute("SSN", SSN);
    }
    
    public void returnEmployeeCredentials(String email, String password, String confirmationPassword, HttpServletRequest request) {
    	// Return the partially-correct data
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("confirmationPassword", confirmationPassword);     
    }
    


    
    
    
}