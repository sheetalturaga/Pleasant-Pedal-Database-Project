package app.dal;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import app.model.*;

import javax.servlet.http.Part;


public class ReviewDAO {
	protected ConnectionManager connectionManager;
	private static ReviewDAO instance = null;
	protected ReviewDAO() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewDAO getInstance() {
		if(instance == null) {
			instance = new ReviewDAO();
		}
		return instance;
	}

	
	public Reviews create(Reviews review) throws SQLException {
		String insertQuery = "INSERT INTO reviews(Rating, UserName, TrailId, TrailName) values(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStatement = null;
		try {
			connection = connectionManager.getConnection();
			insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setFloat(1, review.getRating());
			insertStatement.setString(2, review.getUserName());
			insertStatement.setInt(3, review.getTrail());
			insertStatement.setString(2, review.getTrailName());
			insertStatement.executeUpdate();
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStatement != null) {
				insertStatement.close();
			}
		}
	}


	/**
	 * Delete the Review instance.
	 * This runs a DELETE statement.
	 * @param review
	 * @return
	 */
	public Reviews delete(Reviews review) throws SQLException {
		String deleteQuery = "DELETE FROM reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStatement = null;
		try {
			connection = connectionManager.getConnection();
			deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, review.getReviewId());
			int affectedRows = deleteStatement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No recommendation with the mentioned id" + review.getReviewId());
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStatement != null) {
				deleteStatement.close();
			}
		}
	}
	

	/**
	 * Get the Review record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Review instance.
	 */
	public Reviews getReviewById(Integer id) throws SQLException {
		String selectQuery = "SELECT * FROM reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setInt(1, id);
			/*UsersDao userDao = UsersDao.getInstance();
			TrailsDao trailDao = TrailsDao.getInstance();
			 */
			results = selectStatement.executeQuery();
			if(results.next()) {
				Integer resultId = results.getInt("ReviewId");
				Timestamp created = results.getTimestamp("Created");
				float ratings = results.getFloat(3);
				String username = results.getString("UserName");
				Integer trailId = results.getInt("TrailId");
				String trailName = results.getString("TrailName");
				Reviews review = new Reviews(resultId, created, ratings, username,trailId,trailName);
				return review;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStatement != null) {
				selectStatement.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	/**
	 * Get the matching Reviews records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Reviews.
	 */
	public List<Reviews> getReviewsByUserName(String username) throws SQLException, IOException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectQuery =
			"SELECT * FROM reviews WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setString(1, username);
			/*
			UsersDao userDao = UsersDao.getInstance();
			TrailsDao trailDao = TrailsDao.getInstance();
			 */
			results = selectStatement.executeQuery();

			InputStream inputStream = null;
			ByteArrayOutputStream outputStream = null;
			while(results.next()) {
				Integer resultId = results.getInt("ReviewId");
				Timestamp created = results.getTimestamp("Created");
				float ratings = results.getFloat(3);
				Blob photo = results.getBlob(4);
				String user = results.getString("UserName");
				Integer trailId = results.getInt("TrailId");
				String trailName = results.getString("TrailName");
				
				String image = null;
				if (photo!=null) {
					inputStream = photo.getBinaryStream();
					System.out.println("Some image received");
			
					outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead = -1;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
					}
					byte[] imageBytes = outputStream.toByteArray();
					image = Base64.getEncoder().encodeToString(imageBytes);

					inputStream.close();
					outputStream.close();
				}
				System.out.println(image);
				Reviews review = new Reviews(resultId, created, image, ratings, user,trailId,trailName);
				reviews.add(review);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStatement != null) {
				selectStatement.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return reviews;
	}
	
	/**
	 * Get the matching Review records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Reviews.
	 */
	public List<Reviews> getReviewsByTrailId(int trailId) throws SQLException, IOException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectQuery =
			"SELECT * FROM reviews WHERE TrailId=?;";
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setInt(1, trailId);
			results = selectStatement.executeQuery();
				while(results.next()) {
					Integer resultId = results.getInt("ReviewId");
					Timestamp created = results.getTimestamp("Created");
					float ratings = results.getFloat(3);
					String user = results.getString("UserName");
					int trail = results.getInt("TrailId");
					String trailName = results.getString("TrailName");
					Reviews review = new Reviews(resultId, created, ratings, user, trail,trailName);

					reviews.add(review);
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStatement != null) {
				selectStatement.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return reviews;
	}
}
