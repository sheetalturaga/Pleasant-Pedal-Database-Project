package app.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.*;



public class FavoritesDao {
	protected ConnectionManager connectionManager;
	private static FavoritesDao instance = null;
	protected FavoritesDao() {
		connectionManager = new ConnectionManager();
	}
	public static FavoritesDao getInstance() {
		if(instance == null) {
			instance = new FavoritesDao();
		}
		return instance;
	}

	
	public Favorites create(Favorites favorite) throws SQLException {
		String insertFavorite = "INSERT INTO Favorites(UserName,TrailId) "
				+ "VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFavorite);
			insertStmt.setString(1, favorite.getUser().getUserName());
			insertStmt.setInt(2, favorite.getTrail().getTrailId());
			insertStmt.executeUpdate();
			return favorite;
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
	 * Delete the Favorites instance.
	 * This runs a DELETE statement.
	 */
	public Favorites delete(Favorites favorite) throws SQLException {
		String deleteFavorite = "DELETE FROM Favorites WHERE FavoriteId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFavorite);
			deleteStmt.setInt(1, favorite.getFavoriteId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for Favorite=" + favorite.getFavoriteId());
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
	 * Get the Favorites record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Favorites instance.
	 */
	public Favorites getFavoriteFromFavoriteId(Integer id) throws SQLException {
		String selectFavorite = "SELECT * FROM Favorites WHERE FavoriteId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFavorite);
			selectStmt.setInt(1, id);
			UsersDao userDao = UsersDao.getInstance();
			TrailsDao trailDao = TrailsDao.getInstance();
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer resultId = results.getInt("FavoriteId");
				String resultUsername = results.getString("UserName");
				Users user = userDao.getUsersFromUserName(resultUsername);
				Integer trailId = results.getInt("TrailId");
				Trails trail = trailDao.getTrailFromTrailId(trailId);
				Favorites favorite = new Favorites(resultId, user, trail);
				return favorite;
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
	 * Get the matching Favorites records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Favorites.
	 */
	public List<Favorites> getFavoritesFromUserName(String username) throws SQLException {
		List<Favorites> favorites = new ArrayList<Favorites>();
		String selectFavorites =
			"SELECT * FROM Favorites WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFavorites);
			selectStmt.setString(1, username);
			UsersDao userDao = UsersDao.getInstance();
			TrailsDao trailDao = TrailsDao.getInstance();
			results = selectStmt.executeQuery();
			while(results.next()) {
				Integer id = results.getInt("FavoriteId");
				String resultUsername = results.getString("UserName");
				Users user = userDao.getUsersFromUserName(resultUsername);
				Integer trailId = results.getInt("TrailId");
				Trails trail = trailDao.getTrailFromTrailId(trailId);
				Favorites favorite = new Favorites(id, user, trail);
				favorites.add(favorite);
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
		return favorites;
	}
	
	/**
	 * Get the matching Favorites records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Favorites.
	 */
	public List<Favorites> getFavoritesFromTrailId(Integer trailId) throws SQLException {
		List<Favorites> favorites = new ArrayList<Favorites>();
		String selectFavorites =
			"SELECT * FROM Favorites WHERE TrailId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFavorites);
			selectStmt.setInt(1, trailId);
			UsersDao userDao = UsersDao.getInstance();
			TrailsDao trailDao = TrailsDao.getInstance();
			results = selectStmt.executeQuery();
			while(results.next()) {
				Integer id = results.getInt("FavoriteId");
				String username = results.getString("UserName");
				Users user = userDao.getUsersFromUserName(username);
				Integer resultTrailId = results.getInt("TrailId");
				Trails trail = trailDao.getTrailFromTrailId(resultTrailId);
				Favorites favorite = new Favorites(id, user, trail);
				favorites.add(favorite);
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
		return favorites;
	}
}
