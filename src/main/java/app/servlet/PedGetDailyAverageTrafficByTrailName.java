package app.servlet;

import app.dal.CrimeCounterDAO;
import app.dal.PedestrianCounterDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

  @WebServlet("/getDailyAverageTrafficByTrailName")
  public class PedGetDailyAverageTrafficByTrailName extends HttpServlet {
    protected PedestrianCounterDAO pedestrianCounterDAO;

    @Override
    public void init() throws ServletException {
      pedestrianCounterDAO = PedestrianCounterDAO.getPedestrianCounterInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
      // Map for storing messages.
      Map<String, String> messages = new HashMap<String, String>();
      req.setAttribute("messages", messages);

      Map<String, Object> result = new HashMap<>();


      String trailName = req.getParameter("trailName");
      if ( trailName == null || trailName.trim().isEmpty() ) {
        messages.put("success", "Please enter a valid trail name");
      } else {
        try {
          result = pedestrianCounterDAO.getDailyAverageTrafficByTrailName(trailName);
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
        messages.put("success", "Displaying results for Trail: "+trailName);
        messages.put("previousTrailName",  trailName);
      }
      req.setAttribute("result", result);

      req.getRequestDispatcher("/GetDailyAverageTrafficByTrailName.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
      // Map for storing messages.
      Map<String, String> messages = new HashMap<String, String>();
      req.setAttribute("messages", messages);

      Map<String, Object> result = new HashMap<>();
      String trailName = req.getParameter("trailName");
      if ( trailName == null|| trailName.trim().isEmpty() ) {
        messages.put("success", "Please enter a valid number of years");
      } else {
        try {
          result = pedestrianCounterDAO.getDailyAverageTrafficByTrailName(trailName);
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
        messages.put("success", "Displaying results for Trail: "+trailName);
      }
      req.setAttribute("result", result);

      req.getRequestDispatcher("/GetDailyAverageTrafficByTrailName.jsp").forward(req, resp);
    }
  }




