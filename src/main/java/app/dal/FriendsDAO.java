package app.dal;

import app.model.*;
import blog.dal.ConnectionManager;
import blog.model.Friends;
import blog.model.Friendships;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class FriendsDAO extends PersonsDao {

	public Friends create(Friends friends) throws SQLException {

		create(new Friends(friends.getFriend(), friends.getFriend()));

		String insertFriendship = "INSERT INTO Friend(RequestId, UserName) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFriendship);
			insertStmt.setString(1, friends.getUserName());
			insertStmt.setString(2, friends.getFriend());
			insertStmt.executeUpdate();
			return friends;
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
	
	
	
	public Friends updateFriends(Friends friend, String newfriend) throws SQLException {
		String updateFriends = "UPDATE Friends SET Friend=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateFriends);
			updateStmt.setString(1, newfriend);
			updateStmt.setString(2, friend.getUserName());
			updateStmt.executeUpdate();
			friend.setFriend(newfriend);
			return friend;
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
	
	
	
	
	
	
		
	public Friends delete(Friendships friends) throws SQLException {
		String deleteRecommendation = "DELETE FROM Friendships WHERE RequestId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, friends.getRequestId());
			deleteStmt.executeUpdate();


			delete(friends);

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
