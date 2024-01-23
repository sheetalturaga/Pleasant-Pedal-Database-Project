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

@SuppressWarnings("serial")
@WebServlet("/getcrimes")
public class GetCrimesByNeighborhood extends HttpServlet {
	protected CrimeCounterDAO crimeCounterDAO;
	
	@Override
	public void init() throws ServletException {
		crimeCounterDAO = CrimeCounterDAO.getCrimeCounterInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Map<String, Integer> results = new HashMap<>();
        
        
//        String neighborhood = req.getParameter("neighborhood");
//        int numberOfYears = Integer.valueOf(req.getParameter("years"))+1;
//        if (neighborhood == null || neighborhood.isEmpty() || numberOfYears <=0) {
//            messages.put("success", "Please enter valid neighborhood and year interval");
//        } else {
//        	// Retrieve Trails, and store as a message.
//        	try {
//            	results = crimeCounterDAO.getCrimesByNeighborhood(neighborhood, numberOfYears);
//            } catch (SQLException e) {
//    			e.printStackTrace();
//    			throw new IOException(e);
//            }
//        	messages.put("success", "Displaying results for " + neighborhood+ "over "+numberOfYears+"years.");
//        }
//        req.setAttribute("results", results);
        
        req.getRequestDispatcher("/GetCrimesByNeighborhood.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Map<String, Integer> results = new HashMap<>();
        
        
        String neighborhood = req.getParameter("neighborhood");
        int numberOfYears = Integer.valueOf(req.getParameter("years"))+1;
        if (neighborhood == null || neighborhood.isEmpty() || numberOfYears <=0) {
            messages.put("success", "Please enter valid neighborhood and year interval");
        } else {
        	// Retrieve Trails, and store as a message.
        	try {
            	results = crimeCounterDAO.getCrimesByNeighborhood(neighborhood, numberOfYears);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + neighborhood+ "over "+numberOfYears+"years.");
        }
        req.setAttribute("results", results);
        
        req.getRequestDispatcher("/GetCrimesByNeighborhood.jsp").forward(req, resp);
    }
}
	


