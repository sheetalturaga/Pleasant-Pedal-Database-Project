package app.dal;

import app.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class AdminDao extends PersonDao {
	protected ConnectionManager connectionManager;
	private static AdminDao instance = null;
	protected AdminDao() {
		super();
	}
	public static AdminDao getInstance() {
		if(instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}

	public Admin create(Admin admin) throws SQLException {
		create(new Person(
				admin.getUserName(), admin.getPassword(), admin.getFirstName(), admin.getLastName(),
				admin.getEmail(), admin.getPhoneNumber()
				));

		String insertAdmin = "INSERT INTO Admin(LastLogin) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdmin);
			insertStmt.setTimestamp(1, admin.getLastLogin());
			insertStmt.executeUpdate();
			return admin;
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
	 * Update the LastLogin of the Admin instance.
	 * This runs a UPDATE statement.
	 */
	public Admin updateLastLogin(Admin admin, Timestamp newLastLogin) throws SQLException {
		String updateAdmin = "UPDATE Admin SET LastLogin=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAdmin);
			updateStmt.setTimestamp(1, newLastLogin);
			updateStmt.setString(2, admin.getUserName());
			updateStmt.executeUpdate();
			admin.setLastLogin(newLastLogin);
			return admin;
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
	 * Delete the Admin instance.
	 * This runs a DELETE statement.
	 */
	public Admin delete(Admin admin) throws SQLException {
		String deleteAdmin = "DELETE FROM Admin WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAdmin);
			deleteStmt.setString(1, admin.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + admin.getUserName());
			}
			super.delete(admin);
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
	 * Get the Admin record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Admin instance.
	 */
	public Admin getAdminFromUserName(String userName) throws SQLException {
		String selectAdmin =
			"SELECT * " +
			"FROM Admin INNER JOIN Person " +
			"  ON Admin.UserName = Person.UserName " +
			"WHERE Admin.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdmin);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("PhoneNumber");
				Timestamp lastLogin = results.getTimestamp("LastLogin");
				Admin admin = new Admin(resultUserName, password, firstName, lastName,
						email, phone);
				if(lastLogin != null) {
					admin.setLastLogin(lastLogin);
				}
				return admin;
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
	 * Get a list of Admin records by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a list of Admin instances.
	 * Don't know if this is applicable, but keeping it just in  case
	 */
	public List<Admin> getAdminFromFirstName(String firstName)
			throws SQLException {
		List<Admin> admins = new ArrayList<Admin>();
		String selectAdmin =
			"SELECT *" +
			"FROM Admin INNER JOIN Person " +
			"  ON Admin.UserName = Person.UserName " +
			"WHERE Person.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdmin);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("PhoneNumber");
				Timestamp lastLogin = results.getTimestamp("LastLogin");
				Admin admin = new Admin(userName, password, resultFirstName, lastName,
						email, phone);
				if(lastLogin != null) {
					admin.setLastLogin(lastLogin);
				}
				admins.add(admin);
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
		return admins;
	}
}
