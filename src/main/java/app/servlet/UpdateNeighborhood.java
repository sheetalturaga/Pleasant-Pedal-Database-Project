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


@SuppressWarnings("serial")
@WebServlet("/updateneighborhood")
public class UpdateNeighborhood extends HttpServlet {
	
	protected NeighborhoodsDao neighborhoodsDao;
	
	@Override
	public void init() throws ServletException {
		neighborhoodsDao = NeighborhoodsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String name = req.getParameter("name");
        String newDist = req.getParameter("newDist");
        if (name == null || name.trim().isEmpty()) {
            messages.put("success", "Please enter a valid neighborhood name.");
        } else {
        	try {
        		
        		Neighborhoods neighborhood = neighborhoodsDao.getNeighborhoodFromName(name);
        		neighborhood = neighborhoodsDao.updateDistrict(neighborhood, newDist);
        		
        		if(neighborhood == null) {
        			messages.put("success", "neighborhood does not exist.");
        		}
        		req.setAttribute("neighborhood", neighborhood);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateNeighborhood.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String name = req.getParameter("name");
        String newDist = req.getParameter("newDist");
        if (name == null || name.trim().isEmpty()) {
            messages.put("success", "NAME" + name);
        } else {
        	try {
        		Neighborhoods neighborhood = neighborhoodsDao.getNeighborhoodFromName(name);
        		neighborhood = neighborhoodsDao.updateDistrict(neighborhood, newDist);
        		if(neighborhood == null) {
        			messages.put("success", "Neighborhood does not exist. No update to perform.");
        		} else {
        			String newDistName = req.getParameter("newDist");
        			if (newDistName == null || newDistName.trim().isEmpty()) {
        	            messages.put("success", newDistName);
        	        } else {
        	        	neighborhood = neighborhoodsDao.updateDistrict(neighborhood, newDistName);
        	        	messages.put("success", "Successfully updated " + neighborhood.getName() + ":" +
        	        			neighborhood.getDistrict());
        	        }
        		}
        		req.setAttribute("neighborhood", neighborhood);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateNeighborhood.jsp").forward(req, resp);
    }
}
