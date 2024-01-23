package app.dal;

import app.model.CrimeCounter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CrimeCounterDAO {
    protected ConnectionManager connectionManager;
    private static CrimeCounterDAO crimeCounterInstance = null;

    protected CrimeCounterDAO(){
      connectionManager = new ConnectionManager();
    }

    public static CrimeCounterDAO getCrimeCounterInstance(){
      if (crimeCounterInstance == null){
        crimeCounterInstance = new CrimeCounterDAO();
      }
      return crimeCounterInstance;
    }

    @SuppressWarnings({ "null", "unused" })
	public CrimeCounter create(CrimeCounter entry) throws SQLException {
      String insertQuery = "INSERT INTO Crimes(OffenseID, ReportNumber,OffenseParentGroup, OffenseGroup, ReportTime, Neighborhood, LAT, LONGITUDE) VALUES (?,?,?,?,?,?,?,?);";
      Connection connection =null;
      PreparedStatement insertStatement =null;
      try{
        connection =connectionManager.getConnection();

        insertStatement.setString(1, entry.getOffenseID());
        insertStatement.setString(2, entry.getReportNumber());
        insertStatement.setObject(3, entry.getOffense());
        insertStatement.setString(4, entry.getOffense());
        insertStatement.setDate(5, (Date) entry.getReportDate());
        insertStatement.setString(6, entry.getNeighborhood());
        insertStatement.setFloat(7, entry.getLatitude());
        insertStatement.setFloat(8, entry.getLongitude());

        insertStatement.executeUpdate();
        return entry;
      } catch (SQLException e){
        e.printStackTrace();
        throw e;
      } finally {
        if(connection!=null){
          connection.close();
        }
        if(insertStatement!=null){
          insertStatement.close();
        }
      }
    }

    public CrimeCounter delete(CrimeCounter entry) throws SQLException{
      String deleteQuery = "DELETE FROM CrimeCounter WHERE OffenseId = ?;";
      Connection connection = null;
      PreparedStatement deleteStatement = null;
      try{
        connection = connectionManager.getConnection();
        deleteStatement = connection.prepareStatement(deleteQuery);

        deleteStatement.setString(1, entry.getOffenseID());
        deleteStatement.executeUpdate();

        return null;
      } catch (SQLException e){
        e.printStackTrace();
        throw e;
      } finally {
        if (connection != null) {
          connection.close();
        }
        if (deleteStatement != null) {
          deleteStatement.close();
        }
      }
    }
    
    /**
     * getCrimesByNeighborhood() gives the ranking of the most reported crime categories trails based on data of the specified number of years
     * @param neighborhood 
     * @param numberOfYears
     * @return Map containing Trail name and the total number of crimes reported in the specified duration
     * @throws SQLException
     */
    public Map<String, Integer> getCrimesByNeighborhood(String neighborhood, int numberOfYears) throws SQLException{
    	
        Map<String,Integer> displayResult = new HashMap<>();
        String selectQuery = "select neighborhood, Count(OffenseParentGroup) as count, OffenseParentGroup as Category\r\n"
        		+ "	from crimes where neighborhood =? and year(ReportTime) = Year(Date_sub(curdate(), Interval ? Year))\r\n"
        		+ "	group by neighborhood, OffenseParentGroup order by count(OffenseParentGroup) desc limit 5;";
        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet result = null;
        try{
          connection = connectionManager.getConnection();
          selectStatement = connection.prepareStatement(selectQuery);

          selectStatement.setString(1, neighborhood);
          selectStatement.setInt(2, numberOfYears);
          result = selectStatement.executeQuery();
          String trailName;
          int totalCrimes;
          while (result.next()){
            String category = result.getString("Category");
            int count = result.getInt("Count");
            displayResult.put(category, count);
          }
          return displayResult;
        } catch(SQLException e){
          e.printStackTrace();
          throw e;
        } finally {
          if (connection!=null){
            connection.close();
          }
          if(selectStatement!=null){
            connection.close();
          }
          if(result!=null){
            connection.close();
          }
        }
      }


/**
   * getTrailsCrimeRateByYear() gives the ranking of the most dangerous trails based on data of the specified number of years
   * @param numberOfYears the number years since the current date for which crime data is being analysed
   * @return Map containing Trail name and the total number of crimes reported in the specified duration
   * @throws SQLException
   */
  public Map<String, Object> getTrailsCrimeRateByYear(int numberOfYears) throws SQLException{
      Map<String,Object> displayResult = new HashMap<>();
      String selectQuery = "SELECT Trails.TrailName, COUNT(Crimes.ReportNumber)"
          + "FROM Crimes INNER JOIN TrailNeighborhoods"
          + "ON Crimes.Neighborhood = Trailneighborhoods.Neighborhood"
          + "INNER JOIN Trails"
          + "ON TrailNeighborhoods.TrailId = Trails.TrailId"
          + "WHERE YEAR(ReportTime) = YEAR(DAYE_SUB(CURDATE(), INTERVAL ? YEAR))"
          + "GROUP BY TrailNeighborhoods.TrailID"
          + "ORDER BY COUNT(ReportNumber) DESC;";
      Connection connection = null;
      PreparedStatement selectStatement = null;
      ResultSet result = null;
      try{
        connection = connectionManager.getConnection();
        selectStatement = connection.prepareStatement(selectQuery);

        selectStatement.setInt(1, numberOfYears);
        result = selectStatement.executeQuery();
        String trailName;
        int totalCrimes;
        while (result.next()){
          trailName = result.getString(1);
          totalCrimes = result.getInt(2);
          displayResult.put("Trail Name", trailName);
          displayResult.put("Crimes Reported", totalCrimes);
        }
        return displayResult;
      } catch(SQLException e){
        e.printStackTrace();
        throw e;
      } finally {
        if (connection!=null){
          connection.close();
        }
        if(selectStatement!=null){
          connection.close();
        }
        if(result!=null){
          connection.close();
        }
      }
    }

  /**
   * For a given Trail, getCrimeCategoryRankingByTrail() gives the ranking of the crime categories
   * based on the number of occurrences in the specified duration
   * @param name Name of the Trail
   * @param years number of years
   * @return Map containing crime categories together with the count of crimes that occurred in these
   * categories
   * @throws SQLException
   */
  public Map<String, Object> getCrimeCategoryRankingByTrail(String name, int years) throws SQLException{
    Map<String,Object> displayResult = new HashMap<>();
    String selectQuery = "SELECT Crimes.OffenseParentGroup, COUNT(OffenseId)"
        + "FROM Crimes"
        + "WHERE Neighborhood IN (SELECT Neighborhood FROM Trails INNER JOIN TrailNeighborhoods ON "
        + "Trails.TrailId = TrailNeighborhoods.TrailID WHERE TrailName = ?) AND "
        + "DATE(ReportTime) = YEAR(DATE_SUB(CURDATE(), INTERVAL ? YEAR)));";
    Connection connection = null;
    PreparedStatement selectStatement = null;
    ResultSet result = null;
    try{
      connection = connectionManager.getConnection();
      selectStatement = connection.prepareStatement(selectQuery);

      selectStatement.setString(1, name);
      selectStatement.setInt(2, years);
      result = selectStatement.executeQuery();
      String trailName;
      int totalCrimes;
      while (result.next()){
        trailName = result.getString(1);
        totalCrimes = result.getInt(2);
        displayResult.put("Crime Category", trailName);
        displayResult.put("Crimes Reported", totalCrimes);
      }
      return displayResult;
    } catch(SQLException e){
      e.printStackTrace();
      throw e;
    } finally {
      if (connection!=null){
        connection.close();
      }
      if(selectStatement!=null){
        connection.close();
      }
      if(result!=null){
        connection.close();
      }
    }
  }

  }
