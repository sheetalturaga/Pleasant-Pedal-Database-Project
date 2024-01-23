package app.dal;

import app.model.PedestrianCounter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PedestrianCounterDAO {
  protected ConnectionManager connectionManager;
  private static PedestrianCounterDAO pedestrianCounterInstance = null;

  protected PedestrianCounterDAO(){
    connectionManager = new ConnectionManager();
  }

  public static PedestrianCounterDAO getPedestrianCounterInstance(){
    if (pedestrianCounterInstance == null){
      pedestrianCounterInstance = new PedestrianCounterDAO();
    }
    return pedestrianCounterInstance;
  }

  public PedestrianCounter create(PedestrianCounter entry) throws SQLException {
    String insertQuery = "INSERT INTO PedestrianCounter(CountTime, TotalCount, NorthCount, SouthCount, EastCount, WestCount, Neighborhood, PedestrianTrailID) VALUES (?,?,?,?,?,?,?,?);";
    Connection connection =null;
    PreparedStatement insertStatement =null;
    ResultSet resultKeys = null;
    try{
      connection =connectionManager.getConnection();
      insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

      insertStatement.setDate(1, (Date) entry.getDate());
      insertStatement.setInt(2, entry.getTotalCount());
      insertStatement.setInt(3, entry.getNorthBoundCount());
      insertStatement.setInt(4, entry.getSouthBoundCount());
      insertStatement.setInt(5, entry.getEastBoundCount());
      insertStatement.setInt(6, entry.getWestBoundCount());
      insertStatement.setString(7, entry.getNeighborhood());
      insertStatement.setInt(8, entry.getTrailID());

      insertStatement.executeUpdate();
      resultKeys = insertStatement.getGeneratedKeys();
      int entryId = -1;
      if (resultKeys.next()){
        entryId = resultKeys.getInt(1);
      } else {
        throw new SQLException("Unable to retrive auto-generated keys");
      }
      entry.setEntryId(entryId);
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
      if(resultKeys!= null){
        resultKeys.close();
      }
    }
  }

  public PedestrianCounter delete(PedestrianCounter entry) throws SQLException{
    String deleteQuery = "DELETE FROM PedestrianCounter WHERE PedCountId = ?;";
    Connection connection = null;
    PreparedStatement deleteStatement = null;
    try{
      connection = connectionManager.getConnection();
      deleteStatement = connection.prepareStatement(deleteQuery);

      deleteStatement.setInt(1, entry.getEntryId());
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
   * getBusiestTrails() gives the ranking of the trails based on the total traffic observed in the
   * specified number of years
   * @param numberOfYears Number of Years
   * @return Map containing trail names with the total traffic observed during the specified duration
   * @throws SQLException
   */
  public Map<String, Object> getBusiestTrails(int numberOfYears) throws SQLException{
    Map<String,Object> displayResult = new HashMap<>();
    String selectQuery = "SELECT Trails.TrialName as trail_name, sum(TotalCount), as total_traffic"
        + "FROM PedestrianCounter INNER JOIN Trails"
        + "ON PedestrianTrailID = TrailId"
        + "WHERE YEAR(CountDate) = YEAR(DATE_SUB(CURDATE(), INTERVAL ? YEAR))"
        + "GROUP BY pedestrainTrailID"
        + "ORDER BY sum(TotalCount) DESC;";
    Connection connection = null;
    PreparedStatement selectStatement = null;
    ResultSet result = null;
    try{
      connection = connectionManager.getConnection();
      selectStatement = connection.prepareStatement(selectQuery);

      selectStatement.setInt(1, numberOfYears);
      result = selectStatement.executeQuery();
      String trailName;
      int totalTraffic;
      while (result.next()){
        trailName = result.getString(1);
        totalTraffic = result.getInt(2);
        displayResult.put("Trail Name", trailName);
        displayResult.put("Total traffic Observed", totalTraffic);
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
   * For a given trail name, getDailyAverageTrafficByTrailName() gives the average daily bike traffic
   * @param name name of the trail
   * @return Map containing name ofthe trail together with the daily average bike traffic
   * @throws SQLException
   */
  public Map<String, Object> getDailyAverageTrafficByTrailName(String name) throws SQLException{
    Map<String, Object> displayResult = new HashMap<>();
    String selectQuery = "SELECT Trails.TrailName, AVG(daily_average) FROM "
        + "(SELECT Trails.TrailName, AVG(TotalCount) as daily_average"
        + "FROM PedestrianCounter INNER JOIN Trails"
        + "ON PedestrianTrailID = TrialId"
        + "WHERE PedestrainTrialID = (SELECT TrailID FROM Trails WHERE TrailName = ?)"
        + "GROUP BY PedestrianTrailID, DATE(CountTime)) as daily"
        + "GROUP BY Trails.TrailName;";
    Connection connection = null;
    PreparedStatement selectStatement = null;
    ResultSet result = null;
    try{
      connection = connectionManager.getConnection();
      selectStatement = connection.prepareStatement(selectQuery);

      selectStatement.setString(1, name);
      result = selectStatement.executeQuery();
      String trailName;
      float average;
      while (result.next()){
        trailName = result.getString(1);
        average = result.getFloat(2);
        displayResult.put("Trail Name", trailName);
        displayResult.put("Average Foot Traffic", average);
      }
      return displayResult;
    } catch (SQLException e){
      e.printStackTrace();
      throw e;
    } finally{
      if (connection!=null){
        connection.close();
      }
      if(selectStatement!=null){
        selectStatement.close();
      }
      if(result!=null){
        result.close();
      }
    }
  }
  
 
}
