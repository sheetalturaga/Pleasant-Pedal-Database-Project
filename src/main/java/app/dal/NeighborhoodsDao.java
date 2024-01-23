package app.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import app.model.Neighborhoods;

public class NeighborhoodsDao {
	protected ConnectionManager connectionManager;
	private static NeighborhoodsDao instance = null;
	protected NeighborhoodsDao() {
		connectionManager = new ConnectionManager();
	}
	public static NeighborhoodsDao getInstance() {
		if(instance == null) {
			instance = new NeighborhoodsDao();
		}
		return instance;
	}

	
	public Neighborhoods create(Neighborhoods neighborhood) throws SQLException {
		String insertNeighborhood = "INSERT INTO Neighborhoods(Name,District) "
				+ "VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNeighborhood);
			insertStmt.setString(1, neighborhood.getName());
			insertStmt.setString(1, neighborhood.getDistrict());
			insertStmt.executeUpdate();
			return neighborhood;
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
	 * Update the District of the Neighborhoods instance.
	 * This runs a UPDATE statement.
	 */
	public Neighborhoods updateDistrict(Neighborhoods neighborhood, String newDistrict) throws SQLException {
		String updateDistrict = "UPDATE Neighborhoods SET District=? WHERE Name=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDistrict);
			updateStmt.setString(1, newDistrict);
			updateStmt.setString(2, neighborhood.getName());
			updateStmt.executeUpdate();
			neighborhood.setDistrict(newDistrict);
			return neighborhood;
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
	public Neighborhoods getNeighborhoodFromName(String name) throws SQLException {
		String selectNeighborhood = "SELECT * FROM Neighborhoods WHERE Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNeighborhood);
			selectStmt.setString(1, name);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultName= results.getString("Name");
				String district = results.getString("District");
				Neighborhoods neighborhood = new Neighborhoods(resultName, district);
				return neighborhood;
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
	 * Delete the Neighborhoods instance.
	 * This runs a DELETE statement.
	 */
	public Neighborhoods delete(Neighborhoods neighborhood) throws SQLException {
		String deleteNeighborhood = "DELETE FROM Neighborhoods WHERE Name=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNeighborhood);
			deleteStmt.setString(1, neighborhood.getName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for Name=" + neighborhood.getName());
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
}

	
