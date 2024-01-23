package app.dal;

import app.model.BikeCounter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class BikeCounterDAO {
  protected ConnectionManager connectionManager;
  private static BikeCounterDAO bikeCounterInstance = null;

  protected BikeCounterDAO(){
    connectionManager = new ConnectionManager();
  }

  public static BikeCounterDAO getBikeCounterInstance(){
    if (bikeCounterInstance == null){
      bikeCounterInstance = new BikeCounterDAO();
    }
    return bikeCounterInstance;
  }

  public BikeCounter create(BikeCounter entry) throws SQLException {
    String insertQuery = "INSERT INTO BikeCounter(CountTime, TotalCount, NorthCount, SouthCount, EastCount, WestCount, Neighborhood, BikerTrailID) VALUES (?,?,?,?,?,?,?,?);";
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
      entry.setEntryID(entryId);
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

  public BikeCounter delete(BikeCounter entry) throws SQLException{
    String deleteQuery = "DELETE FROM BikeCounter WHERE BikeCountId = ?;";
    Connection connection = null;
    PreparedStatement deleteStatement = null;
    try{
      connection = connectionManager.getConnection();
      deleteStatement = connection.prepareStatement(deleteQuery);

      deleteStatement.setInt(1, entry.getEntryID());
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

  public Map<String, Object> getNorthVersusSouthAtPeakTime() throws SQLException{
    Map<String, Object>resultMap = new HashMap<>();
    String selectQuery = "SELECT CountTime, TotalCount, (NorthCount / TotalCount)*100 as PercentNorth, \n"
        + "(SouthCount/TotalCount) * 100 as PercentSouth\n"
        + "FROM PedestrianCounter\n"
        + "ORDER BY TotalCount DESC\n"
        + "LIMIT 1;\n";
    Connection connection = null;
    PreparedStatement selectStatement = null;
    ResultSet result = null;
    try {
      connection = connectionManager.getConnection();
      selectStatement = connection.prepareStatement(selectQuery);

      result = selectStatement.executeQuery();

      if (result.next()) {
        String date = new String(result.getString(1));
        int totalCount = result.getInt(2);
        float northPecentage = result.getFloat(3);
        float southPercentage = result.getFloat(4);
        resultMap.put("Date", date);
        resultMap.put("Total Count", totalCount);
        resultMap.put("% of North bound traffic", northPecentage);
        resultMap.put("% of South bound traffic", southPercentage);
      }
      return resultMap;

    } catch (SQLException e){
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStatement != null) {
        selectStatement.close();
      }
      if (result != null) {
        result.close();
      }
    }
  }
  
  public Map<String, Object> getNorthVersusSouthAtPeakTime() throws SQLException {
    Map<String, Object> resultMap = new HashMap<>();
    String selectQuery =
        "SELECT CountTime, TotalCount, (NorthCount / TotalCount)*100 as PercentNorth, \n"
            + "(SouthCount/TotalCount) * 100 as PercentSouth\n"
            + "FROM PedestrianCounter\n"
            + "ORDER BY TotalCount DESC\n"
            + "LIMIT 1;\n";
    Connection connection = null;
    PreparedStatement selectStatement = null;
    ResultSet result = null;
    try {
      connection = connectionManager.getConnection();
      selectStatement = connection.prepareStatement(selectQuery);

      result = selectStatement.executeQuery();

      if (result.next()) {
        String date = new String(result.getString(1));
        int totalCount = result.getInt(2);
        float northPecentage = result.getFloat(3);
        float southPercentage = result.getFloat(4);
        resultMap.put("Date", date);
        resultMap.put("Total Count", totalCount);
        resultMap.put("% of North bound traffic", northPecentage);
        resultMap.put("% of South bound traffic", southPercentage);
      }
      return resultMap;

    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStatement != null) {
        selectStatement.close();
      }
      if (result != null) {
        result.close();
      }
    }
  }

    public Map<String, Object> getBusiestTrails(int numberOfYears) throws SQLException{
    Map<String,Object> displayResult = new HashMap<>();
    String selectQuery = "SELECT Trails.TrialName as trail_name, sum(TotalCount), as total_traffic"
        + "FROM BikeCounter INNER JOIN Trails"
        + "ON BikeTrailID = TrailId"
        + "WHERE YEAR(CountDate) = YEAR(DATE_SUB(CURDATE(), INTERVAL ? YEAR))"
        + "GROUP BY Bike"
        + "TrailID"
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
  
  




}
