package com.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.Base64;

import com.dao.EmployeeDAO;
import com.dao.PasswordUtils;
import com.dao.RecipientDAO;
import com.objects.Employee;
import com.objects.EmployeeController;
import com.objects.Recipient;
import com.objects.RecipientController;

@WebServlet(name = "/RecipientPortalServlet", urlPatterns = {"/recipientPortal"})
public class RecipientPortalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("employeePortal.jsp");
        dispatcher.forward(request, response);
        System.out.println("YOU HAVE CALLED A GET METHOD IN Recipient PORTAL");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("YOU HAVE CALLED A POST METHOD IN RecipientPORTAL");
        String action = request.getParameter("action");

        if ("register".equalsIgnoreCase(action)) {
            try {
                registerRecipient(request, response);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException | ServletException | IOException e) {
                e.printStackTrace();
            }
        } else if ("registerlogin".equalsIgnoreCase(action)) {
            try {
                registerRecipientCredentials(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        } else if ("login".equalsIgnoreCase(action)) {
            try {
                loginRecipient(request, response);
            } catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
        	try {
                loginRecipient(request, response);
            } catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void registerRecipient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("Inside register employee");
        //Get parameters from form
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName"); //Not required
        String lastName = request.getParameter("lastName");
        String namePrefix = request.getParameter("namePrefix"); //Not required
        String DOB_String = request.getParameter("DOB");  //TODO: Mabye cast into a Date object
        String SSN = request.getParameter("SSN"); //Unique variable
        String streetAddress = request.getParameter("streetAddress");
        String aptNum = request.getParameter("aptNum"); //Not required
        String city = request.getParameter("city");
        String zipCode = request.getParameter("zipCode");
        String phoneNumber = request.getParameter("phoneNumber");//Unique variable

        if (isBlank(firstName) || isBlank(lastName) || isBlank(DOB_String) || isBlank(SSN) || isBlank(streetAddress)
           || isBlank(city) || isBlank(zipCode) || isBlank(phoneNumber)) {

        	returnRecipientValues(firstName, middleName, lastName, namePrefix, SSN,
                    streetAddress, aptNum, city, zipCode, phoneNumber, request);
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("recipient-create-account.jsp").forward(request, response);
            return;

        }  else if ( !SSN.matches("\\d{9}")) {

        	returnRecipientValues(firstName, middleName, lastName, namePrefix, SSN,
                    streetAddress, aptNum, city, zipCode, phoneNumber, request);
            request.setAttribute("error", "Enter a valid SSN: 9 digits, numbers only.");
            request.getRequestDispatcher("recipient-create-account.jsp").forward(request, response);
            return;

        } else if ( !zipCode.matches("\\d{5}")) {

        	returnRecipientValues(firstName, middleName, lastName, namePrefix, SSN,
                    streetAddress, aptNum, city, zipCode, phoneNumber, request);
            request.setAttribute("error", "Enter a valid Zip Code: 5 digits, numbers only.");
            request.getRequestDispatcher("recipient-create-account.jsp").forward(request, response);
            return;

        } else if ( !phoneNumber.matches("\\d{10}")) {

        	returnRecipientValues(firstName, middleName, lastName, namePrefix, SSN,
                    streetAddress, aptNum, city, zipCode, phoneNumber, request);
            request.setAttribute("error", "Enter a valid Phone Number: 10 digits, numbers only.");
            request.getRequestDispatcher("recipient-create-account.jsp").forward(request, response);
            return;

        }

        LocalDate DOB = LocalDate.parse(DOB_String);

        RecipientDAO recipientDAO = new RecipientDAO();
        boolean isValid = recipientDAO.validateSSN(SSN);
        if (!isValid) {

            returnRecipientValues(firstName, middleName, lastName, namePrefix, SSN,
                    streetAddress, aptNum, city, zipCode, phoneNumber, request);
            request.setAttribute("error", "A user already has that SSN");
            request.getRequestDispatcher("recipient-create-account.jsp").forward(request, response);
            return;
        }
        isValid = recipientDAO.validatePhoneNumber(phoneNumber);
        if (!isValid) {

        	returnRecipientValues(firstName, middleName, lastName, namePrefix, SSN,
                    streetAddress, aptNum, city, zipCode, phoneNumber, request);
            request.setAttribute("error", "A user already has that phone number");
            request.getRequestDispatcher("recipient-create-account.jsp").forward(request, response);
            return;
        }

        Recipient recipient = new Recipient( firstName, lastName, DOB,
                 streetAddress, city, "Missouri",
                zipCode, SSN, phoneNumber);

        if (!isBlank(middleName)) {
            recipient.setMiddleName(middleName);
        }
        if (!isBlank(aptNum)) {
            recipient.setAddressAptNum(aptNum);
        }
        if (!isBlank(namePrefix)) {
            recipient.setNamePrefix(namePrefix);
        }
        
        
        recipientDAO.addRecipient(recipient);
        int recipientID = recipientDAO.getRecipientID(recipient); 
        
        HttpSession session = request.getSession();
        session.setAttribute("recipientID", recipientID);
        response.sendRedirect("recipient-create-login.jsp");
        

    }

    private void registerRecipientCredentials(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        //Gathers form data
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmationPassword = request.getParameter("confirmationPassword");           
        RecipientDAO recipientDAO = new RecipientDAO();

        if ( isBlank(email) || isBlank(password) || isBlank(confirmationPassword)) {
            returnRecipientCredentials( email, password, confirmationPassword, request);
            request.setAttribute("error", "All feilds must be filled");
            request.getRequestDispatcher("recipient-create-login.jsp").forward(request, response);
            return;
        } else if (!recipientDAO.validateEmail( email)) {
            returnRecipientCredentials( email, password, confirmationPassword, request);
            request.setAttribute("error", "That email is already taken.");
            request.getRequestDispatcher("recipient-create-login.jsp").forward(request, response);
            return;
        }  else if (password.length() < 15) {
            returnRecipientCredentials( email, password, confirmationPassword, request);
            request.setAttribute("error", "Password must be 16 character long");
            request.getRequestDispatcher("recipient-create-login.jsp").forward(request, response);
            return;
        } else if (!(password.equals(confirmationPassword))) {
            returnRecipientCredentials( email, password, confirmationPassword, request);
            request.setAttribute("error", "The two passwords must match");
            request.getRequestDispatcher("recipient-create-login.jsp").forward(request, response);
            return;
        }


        HttpSession session = request.getSession(false);
        // Call your DAO
        int recipientID = (int) session.getAttribute("recipientID");
        System.out.print(recipientID);

        //Generate salt and hash password
        byte[] saltBytes = new byte[16];
        new SecureRandom().nextBytes(saltBytes);
        String salt = Base64.getEncoder().encodeToString(saltBytes);
        String hashedPassword = PasswordUtils.hashPassword(password, salt);

        RecipientController controller = new RecipientController( recipientID,  email, hashedPassword, salt);
        recipientDAO.addRecipientCredentials(controller);

        request.setAttribute("error", "Sucess! Your account has been created, " + email);
        request.getRequestDispatcher("recipient-dashboard.jsp").forward(request, response);

    }


    private void loginRecipient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Gathers form data
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if ( isBlank(email) || isBlank(password)) {
            request.setAttribute("error", "Email and password are required.");
            request.getRequestDispatcher("recipient-login.jsp").forward(request, response);
            return;
        } else if (password.length() < 15) {
            request.setAttribute("error", "Password must be 16 character long");
            request.getRequestDispatcher("recipient-login.jsp").forward(request, response);
            return;
        }

        // Call your DAO
        RecipientDAO recipientDAO = new RecipientDAO();
        boolean isValid = recipientDAO.validateLogin(email, password);

        if (isValid) {
            request.setAttribute("error", "Welcome back, " + email);
        } else {
            request.setAttribute("error", "Invalid email or password.");
        }

        request.getRequestDispatcher("recipient-dashboard.jsp").forward(request, response);
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public void returnRecipientValues(String firstName, String middleName, String lastName, String namePrefix,
                                      String SSN, String streetAddress, String aptNum, String city,
                                      String zipCode, String phoneNumber, HttpServletRequest request) {

        // Return the partially-correct data
        request.setAttribute("firstName", firstName);
        request.setAttribute("middleName", middleName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("namePrefix", namePrefix);        
        request.setAttribute("SSN", SSN);
        request.setAttribute("streetAddress", streetAddress);
        request.setAttribute("aptNum", aptNum);
        request.setAttribute("city", city);
        request.setAttribute("zipCode", zipCode);
        request.setAttribute("phoneNumber", phoneNumber);

    }

    public void returnRecipientCredentials(String email, String password, String confirmationPassword, HttpServletRequest request) {
        // Return the partially-correct data
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("confirmationPassword", confirmationPassword);
    }






}