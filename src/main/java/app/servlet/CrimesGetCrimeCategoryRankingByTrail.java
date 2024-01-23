package app.servlet;

import app.dal.*;
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
@WebServlet("/getCrimeCategoryRankingByTrail")
public class CrimesGetCrimeCategoryRankingByTrail extends HttpServlet {
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
    String trailName = req.getParameter("trailName");
    if ( numberOfYears<=0 || trailName == null ) {
      messages.put("success", "Please enter a valid parameters");
    } else {
      try {
        result = crimeCounterDAO.getCrimeCategoryRankingByTrail(trailName, numberOfYears);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + numberOfYears + " years, at Trail: "+trailName);
      messages.put("previousNumberOfYears",  String.valueOf(numberOfYears));
      messages.put("previousTrailName",  trailName);
    }
    req.setAttribute("result", result);

    req.getRequestDispatcher("/GetCrimeCategoryRankingByTrail.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    Map<String, Object> result = new HashMap<>();
    int numberOfYears = req.getIntHeader("numberOfYears");
    String trailName = req.getParameter("trailName");
    if ( numberOfYears<=0 || trailName == null ) {
      messages.put("success", "Please enter a valid paraemters");
    } else {
      try {
        result = crimeCounterDAO.getCrimeCategoryRankingByTrail(trailName, numberOfYears);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + numberOfYears + " years, at Trail: "+trailName);
    }
    req.setAttribute("result", result);

    req.getRequestDispatcher("/GetCrimeCategoryRankingByTrail.jsp").forward(req, resp);
  }
}



