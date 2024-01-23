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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@WebServlet("/getuserreviews")
public class GetReviewsByUserName extends HttpServlet {
	protected ReviewDAO reviewDAO;
	
	@Override
	public void init() throws ServletException {
		reviewDAO = ReviewDAO.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Reviews> reviews = new ArrayList<Reviews>();
        
        // Retrieve and validate TrailName.
        // TrailName is retrieved from the URL query string.
        String userName = req.getParameter("username");
        if (userName == null || userName.isEmpty()) {
            messages.put("success", "Please enter valid username");
        } else {
        	// Retrieve Trails, and store as a message.
        	try {
            	reviews = reviewDAO.getReviewsByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	//messages.put("previousDifficulty", difficulty);
        }
        req.setAttribute("reviews", reviews);
        
        req.getRequestDispatcher("/GetReviewsByUserName.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Reviews> reviews = new ArrayList<Reviews>();

        // Retrieve and validate TrailName.
        // TrailName is retrieved from the URL query string.
        String userName = req.getParameter("username");
        if (userName == null || userName.isEmpty()) {
            messages.put("success", "Please enter valid username");
        } else {
            // Retrieve Trails, and store as a message.
            try {
                reviews = reviewDAO.getReviewsByUserName(userName);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + userName);
            // Save the previous search term, so it can be used as the default
            // in the input box when rendering FindUsers.jsp.
            //messages.put("previousDifficulty", difficulty);
        }
        req.setAttribute("reviews", reviews);

        req.getRequestDispatcher("/GetReviewsByUserName.jsp").forward(req, resp);
    }
}
	


