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

@WebServlet("/getTrailsCrimeRateByYear")
public class CrimesGetTrailsCrimeRateByYear extends HttpServlet {
  protected CrimeCounterDAO crimeCounterDAO;

  @Override
  public void init() throws ServletException {
    crimeCounterDAO = CrimeCounterDAO.getCrimeCounterInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    Map<String, Object> result = new HashMap<>();


    int numberOfYears = req.getIntHeader("numberOfYears");
    if ( numberOfYears<=0) {
      messages.put("success", "Please enter a valid value for years");
    } else {
      try {
        result = crimeCounterDAO.getTrailsCrimeRateByYear(numberOfYears);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + numberOfYears);
      messages.put("previousNumberOfYears",  String.valueOf(numberOfYears));
    }
    req.setAttribute("result", result);

    req.getRequestDispatcher("/GetTrailsCrimeRateByYear.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    Map<String, Object> result = new HashMap<>();
    int numberOfYears = req.getIntHeader("numberOfYears");
    if (numberOfYears <= 0) {
      messages.put("success", "Please enter a valid number of years");
    } else {
      try {
        result = crimeCounterDAO.getTrailsCrimeRateByYear(numberOfYears);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + numberOfYears);
    }
    req.setAttribute("result", result);

    req.getRequestDispatcher("/GetTrailsCrimeRateByYear.jsp").forward(req, resp);
  }
}



