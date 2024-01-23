package app.servlet;

import app.dal.*;
import app.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


  @WebServlet("/getNorthVersusSouthAtPeakTime")
  public class BikeGetNorthVersusSouthAtPeakTime extends HttpServlet {
    protected BikeCounterDAO bikeCounterDAO;

    @Override
    public void init() throws ServletException {
      bikeCounterDAO = BikeCounterDAO.getBikeCounterInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
      // Map for storing messages.
      Map<String, String> messages = new HashMap<String, String>();
      req.setAttribute("messages", messages);

      Map<String, Object> result = new HashMap<>();
      // Retrieve Trails, and store as a message.
        try {
          result = bikeCounterDAO.getNorthVersusSouthAtPeakTime();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
        messages.put("success", "Displaying results on North versus South Traffic");
        // Save the previous search term, so it can be used as the default
        // in the input box when rendering FindUsers.jsp.

      req.setAttribute("result", result);

      req.getRequestDispatcher("/GetNorthVersusSouthAtPeakTraffic.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
      // Map for storing messages.
      Map<String, String> messages = new HashMap<String, String>();
      req.setAttribute("messages", messages);

      Map<String, Object> result = new HashMap<>();


        try {
          result = bikeCounterDAO.getNorthVersusSouthAtPeakTime();
        } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
        }
        messages.put("success", "Displaying results for North Versus South bound traffic");
      req.setAttribute("result", result);

      req.getRequestDispatcher("/GetNorthVersusSouthAtPeakTraffic.jsp").forward(req, resp);
    }
  }




