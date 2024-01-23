package app.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import app.model.*;


public class RecommendationsDao {
	protected ConnectionManager connectionManager;
	private static RecommendationsDao instance = null;
	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}

	
	public Recommendations create(Recommendations rec) throws SQLException {
		String insertRecommendation = "INSERT INTO Recommendations(Difficulty,UserName,TrailId) "
				+ "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendation);
			insertStmt.setString(1, rec.getDifficulty().name());
			insertStmt.setString(2, rec.getUser().getUserName());
			insertStmt.setInt(3, rec.getTrail().getTrailId());
			insertStmt.executeUpdate();
			return rec;
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
	 * Delete the Recommendations instance.
	 * This runs a DELETE statement.
	 */
	public Recommendations delete(Recommendations rec) throws SQLException {
		String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, rec.getRecommendationId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for Recommendation=" + rec.getRecommendationId());
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
	 * Get the Recommendations record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Recommendations instance.
	 */
	public Recommendations getRecommendationFromRecommendationId(Integer id) throws SQLException {
		String selectRecommendation = "SELECT * FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1, id);
			UsersDao userDao = UsersDao.getInstance();
			TrailsDao trailDao = TrailsDao.getInstance();
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer resultId = results.getInt("RecommendationId");
				Timestamp created = results.getTimestamp("Created");
				Recommendations.difficulty difficulty = 
						Recommendations.difficulty.valueOf(results.getString("Difficulty"));
				String username = results.getString("UserName");
				Integer trailId = results.getInt("TrailId");
				Recommendations rec = new Recommendations(resultId, created);
				if(difficulty!=null) {
					rec.setDifficulty(difficulty);
				}
				if(username!=null) {
					Users user = userDao.getUsersFromUserName(username);
					rec.setUser(user);
				}
				if(trailId!=null) {
					Trails trail = trailDao.getTrailFromTrailId(trailId);
					rec.setTrail(trail);
				}
				return rec;
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
	 * Get the matching Recommendations records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Recommendations.
	 */
	public List<Recommendations> getRecommendationsFromUserName(String username) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		String selectRecommendations =
			"SELECT * FROM Recommendations WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setString(1, username);
			UsersDao userDao = UsersDao.getInstance();
			TrailsDao trailDao = TrailsDao.getInstance();
			results = selectStmt.executeQuery();
			while(results.next()) {
				Integer id = results.getInt("RecommendationId");
				Timestamp created = results.getTimestamp("Created");
				Recommendations.difficulty difficulty = 
						Recommendations.difficulty.valueOf(results.getString("Difficulty"));
				String resultUsername = results.getString("UserName");
				Integer trailId = results.getInt("TrailId");
				Recommendations rec = new Recommendations(id, created);
				if(difficulty!=null) {
					rec.setDifficulty(difficulty);
				}
				if(resultUsername!=null) {
					Users user = userDao.getUsersFromUserName(resultUsername);
					rec.setUser(user);
				}
				if(trailId!=null) {
					Trails trail = trailDao.getTrailFromTrailId(trailId);
					rec.setTrail(trail);
				}
				recommendations.add(rec);
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
		return recommendations;
	}
	
	/**
	 * Get the matching Recommendations records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Recommendations.
	 */
	public List<Recommendations> getRecommendationsFromTrailId(Integer trailId) throws SQLException {
		List<Recommendations> recommendations = new ArrayList<Recommendations>();
		String selectRecommendations =
			"SELECT * FROM Recommendations WHERE TrailId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendations);
			selectStmt.setInt(1, trailId);
			UsersDao userDao = UsersDao.getInstance();
			TrailsDao trailDao = TrailsDao.getInstance();
			results = selectStmt.executeQuery();
			while(results.next()) {
				Integer id = results.getInt("RecommendationId");
				Timestamp created = results.getTimestamp("Created");
				Recommendations.difficulty difficulty = 
						Recommendations.difficulty.valueOf(results.getString("Difficulty"));
				String username = results.getString("UserName");
				Integer resultTrailId = results.getInt("TrailId");
				Recommendations rec = new Recommendations(id, created);
				if(difficulty!=null) {
					rec.setDifficulty(difficulty);
				}
				if(username!=null) {
					Users user = userDao.getUsersFromUserName(username);
					rec.setUser(user);
				}
				if(resultTrailId!=null) {
					Trails trail = trailDao.getTrailFromTrailId(resultTrailId);
					rec.setTrail(trail);
				}
				recommendations.add(rec);
			}
		}
		catch (SQLException e) {
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
		return recommendations;
	}
}
