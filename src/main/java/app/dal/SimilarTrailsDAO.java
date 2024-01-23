package app.dal;

import app.model.*;
import blog.dal.ConnectionManager;
import blog.model.SimilarTrails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class SimilarTrailsDAO extends PersonsDao {

	public SimilarTrails create(SimilarTrails similartrails) throws SQLException {

		create(new SimilarTrails(similartrails.getTrailId(), similartrails.getSimilarTrail()));

		String insertSimilarTrails = "INSERT INTO SimilarTrails(TrailId, SimilarTrail) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSimilarTrails);
			insertStmt.setInt(1, similartrails.getTrailId());
			insertStmt.setString(2, similartrails.getSimilarTrail());
			insertStmt.executeUpdate();
			return similartrails;
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
	
	
	public SimilarTrails updateSimilarTrails(SimilarTrails similartrail) throws SQLException {
		String updateSimilarTrails = "UPDATE SimilarTrails SET District=? WHERE Name=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSimilarTrails);
			updateStmt.setInt(1, similartrail.getTrailId());
			updateStmt.setString(2, similartrail.getSimilarTrail());
			updateStmt.executeUpdate();
			similartrail.setSimilarTrail(updateSimilarTrails);
			return similartrail;
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
	
	
	
		
	public SimilarTrails delete(SimilarTrails similartrails) throws SQLException {
		String deleteSimilartrails = "DELETE FROM Friendships WHERE RequestId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSimilartrails);
			deleteStmt.setInt(1, similartrails.getTrailId());
			deleteStmt.executeUpdate();


			delete(similartrails);

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
	
	
}
