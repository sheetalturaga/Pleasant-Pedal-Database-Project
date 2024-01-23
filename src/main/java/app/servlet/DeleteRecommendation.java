package app.servlet;

	import app.dal.*;
	import app.model.*;
	import java.io.IOException;
	import java.sql.SQLException;
	import java.util.HashMap;

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
//@WebServlet("/deleterecommendation")
public class DeleteRecommendation extends HttpServlet {
	protected RecommendationsDao recommendationsDao;
	
	@Override
	public void init() throws ServletException {
		recommendationsDao = RecommendationsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Recommendation");        
        req.getRequestDispatcher("/DeleteRecommendation.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        Integer recommendationId = Integer.valueOf(req.getParameter("recommendationId"));
        if (recommendationId == null) {
            messages.put("title", "Invalid recommendation ID");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Recommendations recommendation = new Recommendations(recommendationId);
	        try {
	        	recommendation = recommendationsDao.delete(recommendation);
	        	// Update the message.
		        if (recommendation == null) {
		            messages.put("title", "Successfully deleted " + recommendationId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + recommendationId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
	        	if (e.getMessage().equals("No records available to delete for Recommendation="+recommendationId)) {
	        		messages.put("title", "Failed to delete " + recommendationId);
		        	messages.put("disableSubmit", "false");
	        	}
	        	else {
					e.printStackTrace();
					throw new IOException(e);
	        	}
	        }
        }

        req.getRequestDispatcher("/DeleteRecommendation.jsp").forward(req, resp);
    }




}
