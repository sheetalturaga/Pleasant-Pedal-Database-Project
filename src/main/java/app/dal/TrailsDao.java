package app.dal;
import app.model.*;
import app.model.Trails.Difficulty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TrailsDao{
	protected ConnectionManager connectionManager;
	private static TrailsDao instance = null;
	protected TrailsDao() {
		connectionManager = new ConnectionManager();
	}
	public static TrailsDao getInstance() {
		if(instance == null) {
			instance = new TrailsDao();
		}
		return instance;
	}

	
	public Trails create(Trails trail) throws SQLException {
		String insertTrail = "INSERT INTO Trails(TrailName,Picture,Description,Distance,Difficulty) "
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTrail);
			insertStmt.setString(1, trail.getTrailName());
			insertStmt.setNull(2, Types.BLOB);
			insertStmt.setString(3, trail.getDescription());
			insertStmt.setDouble(4, trail.getDistance());
			insertStmt.setString(5, trail.getDifficulty().name());
			insertStmt.executeUpdate();
			return trail;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Update the Difficulty of the Trails instance.
	 * This runs a UPDATE statement.
	 */
	public Trails updateDifficulty(Trails trail, String newDifficulty) throws SQLException {
		String updateDifficulty = "UPDATE Trails SET Difficulty=? WHERE TrailId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDifficulty);
			updateStmt.setString(1, newDifficulty);
			updateStmt.setString(2, trail.getDifficulty().name());
			updateStmt.executeUpdate();
			trail.getDifficulty();
			trail.setDifficulty(Difficulty.valueOf(newDifficulty));
			return trail;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Trails updatePicture(Trails trail, String newPicture) throws SQLException {
		String updatePicture = "UPDATE Trails SET Picture=? WHERE TrailId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePicture);
			updateStmt.setString(1, newPicture);
			updateStmt.setString(2, trail.getPicture());
			updateStmt.executeUpdate();
			trail.getPicture();
			trail.setPicture(updatePicture);
			return trail;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Trails updateDescription(Trails trail, String newDescription) throws SQLException {
		String updateDescription = "UPDATE Trails SET Description=? WHERE TrailId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDescription);
			updateStmt.setString(1, newDescription);
			updateStmt.setString(2, trail.getDescription());
			updateStmt.executeUpdate();
			trail.getDescription();
			trail.setDescription(updateDescription);
			return trail;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	/**
	 * Get the Trails record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Trails instance.
	 */
	public Trails getTrailFromTrailId(int trailId) throws SQLException {
		String selectTrail = "SELECT * FROM Trails WHERE TrailId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrail);
			selectStmt.setInt(1, trailId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultTrailId= results.getInt("TrailId");
				String trailName = results.getString("TrailName");
				String picture = results.getString("Picture");
				String description = results.getString("Description");
				Double distance  = results.getDouble("Distance");
				Trails.Difficulty difficulty = Trails.Difficulty.valueOf(results.getString("Difficulty"));
				Trails trail = new Trails(resultTrailId, trailName, picture, description, distance, difficulty);
				
				return trail;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	

	
	/**
	 * Delete the Trails instance.
	 * This runs a DELETE statement.
	 */
	public Trails delete(Trails trail) throws SQLException {
		String deleteTrail = "DELETE FROM Trails WHERE TrailId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTrail);
			deleteStmt.setInt(1, trail.getTrailId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for TrailId=" + trail.getTrailId());
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	

	/**
	 * Get the matching Trails records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Trails.
	 */
	public List<Trails> getTrailsFromDifficulty(String difficulty) throws SQLException {
		List<Trails> trails = new ArrayList<Trails>();
		String selectTrails =
			"SELECT * FROM Trails WHERE Difficulty=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrails);
			selectStmt.setString(1, difficulty);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int trailId = results.getInt("TrailId");
				String trailName = results.getString("TrailName");
				String picture = results.getString("Picture");
				String description = results.getString("Description");
				Double distance  = results.getDouble("Distance");
				Trails.Difficulty Resultdifficulty = Trails.Difficulty.valueOf(results.getString("Difficulty"));
				Trails trail = new Trails(trailId, trailName, picture, description, distance, Resultdifficulty);
				trails.add(trail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return trails;
	}
	public Trails getTrailsFromName(String trailName) throws SQLException {
		Trails trail = null;
		String selectTrails =
			"SELECT * FROM Trails WHERE TrailName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrails);
			selectStmt.setString(1, trailName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int trailId = results.getInt("TrailId");
				String resultTrailName = results.getString("TrailName");
				String picture = results.getString("Picture");
				String description = results.getString("Description");
				Double distance  = results.getDouble("Distance");
				Trails.Difficulty difficulty = Trails.Difficulty.valueOf(results.getString("Difficulty"));
				trail = new Trails(trailId, resultTrailName, picture, description, distance, difficulty);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return trail;
	}

	public Map<String,Integer> getPeakTraffic(int trailId) throws SQLException {
		Map<String, Integer> top5 = new HashMap<String, Integer>();
		String selectTrails =
			"SELECT HOUR(CountTime) as hr, AVG(TotalCount) as peak "
			+ "FROM BikeCounter "
			+ "WHERE BikerTrailId=? "
			+ "GROUP BY hr "
			+ "ORDER BY peak "
			+ "DESC LIMIT 5;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrails);
			selectStmt.setInt(1, trailId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String temp = results.getString("hr");
				if(temp.length() < 2) {
					temp = "0" + temp;
				}
				String hr = temp+":00:00 - "+ String.valueOf(Integer.valueOf(temp)+1)+":00:00";
				Integer peak = results.getInt("peak");
				top5.put(hr, peak);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return top5;
	}
	/**
	 * Get all Trails records filtering by difficulty.
	 * This runs a SELECT statement and returns a list of matching Trails.
	 */
	public List<Trails> allTrailsByDifficulty() throws SQLException {
		List<Trails> trails = new ArrayList<Trails>();
		String selectTrails =
				"SELECT * " +
				"FROM Trails " +
				"ORDER BY Difficulty";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrails);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int trailId = results.getInt("TrailId");
				String trailName = results.getString("TrailName");
				String picture = results.getString("Picture");
				String description = results.getString("Description");
				Double distance  = results.getDouble("Distance");
				Trails.Difficulty Resultdifficulty = Trails.Difficulty.valueOf(results.getString("Difficulty"));
				Trails trail = new Trails(trailId, trailName, picture, description, distance, Resultdifficulty);

				trails.add(trail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return trails;
	}
	public List<Trails> allTrailsByName() throws SQLException {
		List<Trails> trails = new ArrayList<Trails>();
		String selectTrails =
				"SELECT * " +
				"FROM Trails " +
				"ORDER BY TrailName";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrails);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int trailId = results.getInt("TrailId");
				String trailName = results.getString("TrailName");
				String picture = results.getString("Picture");
				String description = results.getString("Description");
				Double distance  = results.getDouble("Distance");
				Trails.Difficulty Resultdifficulty = Trails.Difficulty.valueOf(results.getString("Difficulty"));
				Trails trail = new Trails(trailId, trailName, picture, description, distance, Resultdifficulty);
				trails.add(trail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return trails;
	}
}