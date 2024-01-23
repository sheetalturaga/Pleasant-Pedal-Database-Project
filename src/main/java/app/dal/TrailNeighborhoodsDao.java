package app.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import app.model.*;
import app.model.Trails.Difficulty;

public class TrailNeighborhoodsDao extends TrailsDao {
	protected ConnectionManager connectionManager;
	private static TrailNeighborhoodsDao instance = null;
	protected TrailNeighborhoodsDao() {
		super();
	}
	public static TrailNeighborhoodsDao getInstance() {
		if(instance == null) {
			instance = new TrailNeighborhoodsDao();
		}
		return instance;
	}

	public TrailNeighborhoods create(TrailNeighborhoods trailNeighborhood) throws SQLException {
		String insertTrail = "INSERT INTO trailNeighborhoods(TrailId,Neighborhood) "
				+ "VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTrail);
			insertStmt.setInt(1, trailNeighborhood.getTrailId());
			insertStmt.setString(1, trailNeighborhood.getNeighborhood().getName());
			insertStmt.executeUpdate();
			return trailNeighborhood;
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
	 * Delete the TrailNeighborhoods instance.
	 * This runs a DELETE statement.
	 */
	public TrailNeighborhoods delete(TrailNeighborhoods trailNeighborhood) throws SQLException {
		String deleteTrailNeighborhood = "DELETE FROM trailNeighborhoods WHERE TrailId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTrailNeighborhood);
			deleteStmt.setInt(1, trailNeighborhood.getTrailId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for TrailId=" + trailNeighborhood.getTrailId());
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
	 * Get the matching trailNeighborhoods records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching trailNeighborhoods.
	 */
	public List<TrailNeighborhoods> getTrailNeighborhoodsFromTrailId(int trailId) throws SQLException {
		List<TrailNeighborhoods> trailNeighborhoods = new ArrayList<TrailNeighborhoods>();
		String selectTrailNeighborhoods =
				"SELECT TrailNeighborhoods.TrailId AS TrailId, TrailName, Picture, Description, Location, Distance, Difficulty " +
						"FROM TrailNeighborhoods INNER JOIN Neighborhoods " +
						"  ON TrailNeighborhoods.Neighborhood = Neighborhoods.Name " +
						"WHERE TrailNeighborhoods.TrailId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrailNeighborhoods);
			selectStmt.setInt(1, trailId);
			NeighborhoodsDao neighborhoodDao = NeighborhoodsDao.getInstance();
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultsTrailId = results.getInt("TrailId");
				String trailName = results.getString("TrailName");
				String picture = results.getString("Picture");
				String description = results.getString("Description");
				String location = results.getString("Location");
				Double distance  = results.getDouble("Distance");
				String neighborhoodName = results.getString("Name");
				Neighborhoods neighborhood = neighborhoodDao.getNeighborhoodFromName(neighborhoodName);
				Difficulty difficulty = Difficulty.valueOf(results.getString("Difficulty"));
				TrailNeighborhoods trailNeighborhood = new TrailNeighborhoods(resultsTrailId, trailName, picture, description, location, distance, difficulty, 
						neighborhood);
				trailNeighborhoods.add(trailNeighborhood);
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
		return trailNeighborhoods;
	}

	/**
	 * Get the matching trailNeighborhoods records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching trailNeighborhoods.
	 */
	public List<TrailNeighborhoods> getTrailNeighborhoodsFromNeighborhood(String neighborhood) throws SQLException {
		List<TrailNeighborhoods> trailNeighborhoods = new ArrayList<TrailNeighborhoods>();
		String selectTrailNeighborhoods =
				"SELECT TrailNeighborhoods.TrailId AS TrailId, TrailName, Picture, Description, Location, Distance, Difficulty " +
						"FROM TrailNeighborhoods INNER JOIN Neighborhoods " +
						"  ON TrailNeighborhoods.Neighborhood = Neighborhoods.Name " +
						"WHERE TrailNeighborhoods.Neighborhood=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrailNeighborhoods);
			selectStmt.setString(1, neighborhood);
			NeighborhoodsDao neighborhoodDao = NeighborhoodsDao.getInstance();
			results = selectStmt.executeQuery();
			while(results.next()) {
				int trailId = results.getInt("TrailId");
				String trailName = results.getString("TrailName");
				String picture = results.getString("Picture");
				String description = results.getString("Description");
				String location = results.getString("Location");
				Double distance  = results.getDouble("Distance");
				String neighborhoodName = results.getString("Name");
				Neighborhoods resultsNeighborhood = neighborhoodDao.getNeighborhoodFromName(neighborhoodName);
				Difficulty difficulty = Difficulty.valueOf(results.getString("Difficulty"));
				TrailNeighborhoods trailNeighborhood = new TrailNeighborhoods(trailId, trailName, picture, description, location, distance, difficulty, 
						resultsNeighborhood);
				trailNeighborhoods.add(trailNeighborhood);
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
		return trailNeighborhoods;
	}
}
