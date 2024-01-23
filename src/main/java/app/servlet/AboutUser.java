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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/aboutuser")
public class AboutUser extends HttpServlet {
	protected UsersDao userdao;
	protected ReviewDAO reviewDAO;

	
	@Override
	public void init() throws ServletException {
		userdao = UsersDao.getInstance();
		reviewDAO = ReviewDAO.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Users result = null;
        String username = null;
        List<Reviews> reviews = new ArrayList<Reviews>();
        
        System.out.println("Username before Cookies @get: "+username);
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("user")){
                    username = cookie.getValue();
                }
            }
        }
        if(username==null){
        resp.sendRedirect("Login.jsp");
        } else {
    	System.out.println("Username @get: "+username);
    	
        if (username == null || username.isEmpty()) {
        	System.out.println("Null username");
            messages.put("success", "Please enter a valid username");
        } else {
        	try {
            	result = userdao.getUsersFromUserName(username);
            	reviews = reviewDAO.getReviewsByUserName(username);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for-- " + username);
        	req.setAttribute("firstName",result.getFirstName());

        }
        req.setAttribute("result", result);
        req.setAttribute("firstname", result.getFirstName());
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/AboutUser.jsp").forward(req, resp);
        }
	}
	
	@SuppressWarnings("unused")
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Users result = null;
        String username = null;
        List<Reviews> reviews = new ArrayList<Reviews>();
        System.out.println("Username before Cookies @post: "+username);
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("user")){
                    username = cookie.getValue();
                }
            }
        }
        if (username==null){
        resp.sendRedirect("Login.jsp");
        }
              
        //System.out.println("Username at post: "+username);
        if (username == null || username.isEmpty()) {
            messages.put("success", "Please click on valid value");
        } else {
        	try {
            	result = userdao.getUsersFromUserName(username);
            	reviews = reviewDAO.getReviewsByUserName(username);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for: " + username);
        }
    	System.out.println("Username @post: "+username);

        req.setAttribute("result", result);
        req.setAttribute("reviews", reviews);

        req.getRequestDispatcher("/AboutUser.jsp").forward(req, resp);
    }
}
	


