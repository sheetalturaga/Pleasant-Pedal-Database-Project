package app.servlet;

import app.dal.*;
import app.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/abouttrail")
public class AboutTrail extends HttpServlet {
	protected TrailsDao trailsDao;

	
	@Override
	public void init() throws ServletException {
		trailsDao = TrailsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Trails result = null;
//        String name = "Lake Union Loop";
        String name = req.getParameter("name");
        Map<String, Integer> peakTimes = new HashMap<String, Integer>();
        
        System.out.println("Trail Name before Cookies @get: "+ name);
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("TrailName")){
                    name = cookie.getValue();
                }
            }
        }
        if(name==null){
        	// change to Marcela's page
        resp.sendRedirect("displaytrails");
        } else {
    	System.out.println("Trail Name @get: "+name);
    	
        if (name == null || name.isEmpty()) {
        	System.out.println("Null Trail");
            messages.put("success", "Please select a valid Trail Name");
        } else {
        	try {
            	result = trailsDao.getTrailsFromName(name);
            	int id = result.getTrailId();
            	peakTimes = trailsDao.getPeakTraffic(id);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for-- " + name);

        }
        req.setAttribute("result", result);
        req.setAttribute("TrailName", result.getTrailName());
        req.setAttribute("TrailId", result.getTrailId());
        req.setAttribute("TrailDistance", result.getDistance());
        req.setAttribute("TrailDescription", result.getDescription());
        req.setAttribute("TrailPicture", result.getPicture());
        req.setAttribute("TrailDifficulty", result.getDifficulty());
        req.setAttribute("TrailTop5Peaks", peakTimes);
        req.getRequestDispatcher("/AboutTrail.jsp").forward(req, resp);
        }
	}
	
	@SuppressWarnings("unused")
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Trails result = null;
        String name = "Lake Union Loop";
        Map<String, Integer> peakTimes = new HashMap<String,Integer>();
        System.out.println("Trail Name before Cookies @post: "+name);
        Cookie[] cookies = req.getCookies();
        if (cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("user")){
                    name = cookie.getValue();
                }
            }
        }
        if (name==null){
        resp.sendRedirect("Login.jsp");
        }
        if (name == null || name.isEmpty()) {
            messages.put("success", "Please click on valid TrailId");
        } else {
        	try {
            	result = trailsDao.getTrailFromTrailId(Integer.parseInt(name));
            	int id = result.getTrailId();
            	peakTimes = trailsDao.getPeakTraffic(id);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for: " + name);
        }
    	System.out.println("TrailId @post: "+ name);

        req.setAttribute("result", result);
        req.setAttribute("TrailName", result.getTrailName());
        req.setAttribute("TrailId", result.getTrailId());
        req.setAttribute("TrailDistance", result.getDistance());
        req.setAttribute("TrailDescription", result.getDescription());
        req.setAttribute("TrailPicture", result.getPicture());
        req.setAttribute("TrailDifficulty", result.getDifficulty());
        req.setAttribute("TrailTop5Peaks", peakTimes);
        req.getRequestDispatcher("/AboutTrail.jsp").forward(req, resp);
    }
}
	


