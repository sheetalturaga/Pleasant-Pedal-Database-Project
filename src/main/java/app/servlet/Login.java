package app.servlet;

import app.dal.*;
import app.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * FindUsers is the primary entry point into the application.
 *
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 *
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */
@SuppressWarnings("serial")
public class Login extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private PersonDao personDao;

        public void init() {
            personDao = PersonDao.getInstance();
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        	/*
            Cookie[] cookies = request.getCookies();
            if (cookies!=null){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("user")){
                        Cookie user = cookie;
                        if(user.getMaxAge()==0) {
                        	response.sendRedirect("logout");
                        	
                        }
                    }
                }
            }
            */
        	String action = request.getParameter("nextstep");
        	System.out.println(action);
        	if (action.equalsIgnoreCase("login")) {
	        	Map<String, String>messages = new HashMap();
	        	request.setAttribute("messages", messages);
	        	
	        	boolean success = false;
	
	            String username = request.getParameter("username");
	            String password = request.getParameter("password");
	            Person person = new Person(username,password);
	            System.out.println(username +" "+ password);
	            if (username == null || username.isEmpty()) {
	                messages.put("success", "Please enter valid username");
	            } else {
	            	// Retrieve Trails, and store as a message.
	            	try {
	                	success = personDao.validate(person);
	                } catch (SQLException | ClassNotFoundException e) {
	        			e.printStackTrace();
	        			throw new IOException(e);
	                }
	            	//messages.put("success", "Authentication completed " + success);
	            	// Save the previous search term, so it can be used as the default
	            	// in the input box when rendering FindUsers.jsp.
	            	//messages.put("previousDifficulty", difficulty);
	            }
	            request.setAttribute("success", success);
	            if (success) {
	                Cookie cookie = new Cookie("user",username);
	                cookie.setMaxAge(60*60);
	                response.addCookie(cookie);
	            	//request.getSession().setAttribute("username", request.getParameter("username"));
	            	messages.put("success", "Authentication completed ");
	            	success = false;
	            	//request.getRequestDispatcher("/AboutUser.jsp").forward(request, response);
	            	response.sendRedirect("aboutuser");
	            } else {
	            	messages.put("success", "Authentication failed " );
	            	request.getRequestDispatcher("/Login.jsp").forward(request, response);
	            }
	            messages.remove("success");
	    	} else if (action.equalsIgnoreCase("create")) {
	    		System.out.println("code block entered");
	    		response.sendRedirect("createuser");
	    	}
        }
            

        
        public void doGet(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
            /**Cookie[] cookies = request.getCookies();
            if (cookies!=null){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("user")){
                        Cookie user = cookie;
                        if(user.getMaxAge()==0) {
                        	response.sendRedirect("logout");
                        }
                    }
                }
            }
            */
        	Map<String, String>messages = new HashMap();
        	request.setAttribute("messages", messages);
        	
        	boolean success = false;

            String username = request.getParameter("UserName");
            String password = request.getParameter("PassWord");
            Person person = new Person(username,password);
            if (username == null || username.isEmpty()) {
                messages.put("success", "Please enter valid username");
            } else {
            	// Retrieve Trails, and store as a message.
            	try {
                	success = personDao.validate(person);
                } catch (SQLException | ClassNotFoundException e) {
        			e.printStackTrace();
        			throw new IOException(e);
                }
            	messages.put("success", "Authentication completed " + success);
            	// Save the previous search term, so it can be used as the default
            	// in the input box when rendering FindUsers.jsp.
            	//messages.put("previousDifficulty", difficulty);
            }
            request.setAttribute("success", success);
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
    	}
    }


