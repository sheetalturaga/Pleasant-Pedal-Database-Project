// Arjun Patel

import app.model.*;
import app.dal.ConnectionManager;
import app.model.Friends;
import app.model.Friendships;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Administrators table in your
 * MySQL instance. This is used to store {@link Administrators} into your MySQL instance and 
 * retrieve {@link Administrators} from MySQL instance.
 */
public class FriendshipsDao {
	
	protected ConnectionManager connectionManager;
	private static FriendshipsDao instance = null;
	protected FriendshipsDao() {
	
	}
	
	public static FriendshipsDao getInstance() {
		if(instance == null) {
			instance = new FriendshipsDao();
		}
		
		return instance;
	}
	
	public Friendships create(Friendships friendships) {

		create(new Friendships(friendships.getRequestId(), friendships.getUserName(), friendships.getRequestUserName()));

		String insertFriendship = "INSERT INTO Friendship(RequestId, UserName, RequestUserName) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFriendship);
			insertStmt.setInt(1, friendships.getRequestId());
			insertStmt.setString(2, friendships.getUserName());
			insertStmt.setString(3, friendships.getRequestUserName());
			insertStmt.executeUpdate();
			return friendships;
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
	
	
	public Friendships updateFriendships(Friendships friendship, String newUserName, String newRequestUserName) throws SQLException {
		String updateFriendships = "UPDATE Friendships SET UserName=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateFriendships);
			updateStmt.setString(1, newUserName);
			updateStmt.setString(2, friendship.getUserName());
			updateStmt.setString(3, newRequestUserName);
			updateStmt.executeUpdate();
			friendship.setUserName(newUserName);
			return friendship;
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
	
	
	
		
	public Friendships delete(Friendships friendships) throws SQLException {
		String deleteRecommendation = "DELETE FROM Recommendation WHERE RequestId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, friendships.getRequestId());
			deleteStmt.executeUpdate();


			delete(friendships);

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