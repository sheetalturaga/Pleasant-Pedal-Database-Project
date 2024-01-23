package app.dal;

import app.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class UsersDao extends PersonDao {
	protected ConnectionManager connectionManager = new ConnectionManager();
	private static UsersDao instance = null;
	protected UsersDao() {
		super();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users users) throws SQLException {
		create(new Person(
				users.getUserName(), users.getPassword(), users.getFirstName(), users.getLastName(),
				users.getEmail()
				));
		
		String insertUser = "INSERT INTO Users(UserName,FirstName,LastName,Email,Bio,Address,LevelStatus) VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, users.getUserName());
			//insertStmt.setString(2, users.getPassword());
			insertStmt.setString(2, users.getFirstName());
			insertStmt.setString(3, users.getLastName());
			insertStmt.setString(4, users.getEmail());
			insertStmt.setString(5, users.getBio());
			insertStmt.setString(6, users.getAddress());
			insertStmt.setString(7, users.getLevelStatus().name());
			insertStmt.executeUpdate();
			return users;
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
	 * Update the DateOfBirth of the Users instance.
	 * This runs a UPDATE statement.
	 */
	
	public Users updateDateOfBirth(Users users, Date dob) throws SQLException {
		String updateUsers = "UPDATE Users SET DateOfBirth=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUsers);
			updateStmt.setDate(1, (java.sql.Date) dob);
			updateStmt.setString(2, users.getUserName());
			updateStmt.executeUpdate();
			users.setDateOfBirth((java.sql.Date) dob);
			return users;
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
	 * Update the Bio of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateBioFromUserName(Users users, String newBio) throws SQLException {
		String updateUsers = "UPDATE Users SET Bio=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUsers);
			updateStmt.setString(1, newBio);
			updateStmt.setString(2, users.getUserName());
			updateStmt.executeUpdate();
			users.setBio(newBio);
			return users;
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
	 * Update the Address of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateAddressFromUsername(Users users, String newAddress) throws SQLException {
		String updateUsers = "UPDATE Users SET Address=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUsers);
			updateStmt.setString(1, newAddress);
			updateStmt.setString(2, users.getUserName());
			updateStmt.executeUpdate();
			users.setAddress(newAddress);
			return users;
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
	 * Update the LevelStatus of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateLevelStatusFromUsername(Users users, Users.levelStatus newLevel) throws SQLException {
		String updateUsers = "UPDATE Users SET LevelStatus=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUsers);
			updateStmt.setString(1, newLevel.name());
			updateStmt.setString(2, users.getUserName());
			updateStmt.executeUpdate();
			users.setLevelStatus(newLevel);
			return users;
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
	 * Delete the Users instance.
	 * This runs a DELETE statement.
	 */
	public Users delete(Users users) throws SQLException {
		String deleteUsers = "DELETE FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUsers);
			deleteStmt.setString(1, users.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + users.getUserName());
			}
			super.delete(users);
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
	 * Get the Users record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Users instance.
	 * (DateOfBirth,Bio,Address,LevelStatus)
	 */
	public Users getUsersFromUserName(String userName) throws SQLException {
		String selectUsers =
			"SELECT * " +
			"FROM Users INNER JOIN Person " +
			"ON Users.UserName = Person.UserName " +
			"WHERE Users.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("PhoneNumber");
				Date dob = results.getDate("DateOfBirth");
				String bio = results.getString("Bio");
				String address = results.getString("Address");
				Users.levelStatus levelStatus = Users.levelStatus.valueOf(
						results.getString("LevelStatus"));
				Users user = new Users(resultUserName, password, firstName, lastName,
						email, phone);
				if(dob!=null) {
					user.setDateOfBirth((java.sql.Date) dob);
				}
				if(bio!=null) {
					user.setBio(bio);
				}
				if(address!=null) {
					user.setAddress(address);
				}
				if(levelStatus!=null) {
					user.setLevelStatus(levelStatus);
				}
				return user;
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
	 * Get a list of Users records by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a list of Users instances.
	 * Don't know if this is applicable, but keeping it just in  case
	 */
	public List<Users> getUsersFromFirstName(String firstName)
			throws SQLException {
		List<Users> listOfUsers = new ArrayList<Users>();
		String selectUsers =
			"SELECT *" +
			"FROM Users INNER JOIN Person " +
			"  ON Users.UserName = Person.UserName " +
			"WHERE Person.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("PhoneNumber");
				Date dob = results.getDate("DateOfBirth");
				String bio = results.getString("Bio");
				String address = results.getString("Address");
				Users.levelStatus levelStatus = Users.levelStatus.valueOf(
						results.getString("LevelStatus"));
				Users user = new Users(userName, password, resultFirstName, lastName,
						email, phone);
				if(dob!=null) {
					user.setDateOfBirth((java.sql.Date) dob);
				}
				if(bio!=null) {
					user.setBio(bio);
				}
				if(address!=null) {
					user.setAddress(address);
				}
				if(levelStatus!=null) {
					user.setLevelStatus(levelStatus);
				}
				listOfUsers.add(user);
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
		return listOfUsers;
	}
	
	/**
	 * Get a list of Users records by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a list of Users instances.
	 * Don't know if this is applicable, but keeping it just in  case
	 */
	public List<Users> getUsersFromLevelStatus(Users.levelStatus levelStatus)
			throws SQLException {
		List<Users> listOfUsers = new ArrayList<Users>();
		String selectUsers =
			"SELECT *" +
			"FROM Users INNER JOIN Person " +
			"  ON Users.UserName = Person.UserName " +
			"WHERE Users.LevelStatus=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, levelStatus.name());
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("PhoneNumber");
				Date dob = results.getDate("DateOfBirth");
				String bio = results.getString("Bio");
				String address = results.getString("Address");
				Users.levelStatus resultLevelStatus = Users.levelStatus.valueOf(
						results.getString("LevelStatus"));
				Users user = new Users(userName, password, firstName, lastName,
						email, phone);
				if(dob!=null) {
					user.setDateOfBirth((java.sql.Date) dob);
				}
				if(bio!=null) {
					user.setBio(bio);
				}
				if(address!=null) {
					user.setAddress(address);
				}
				if(resultLevelStatus!=null) {
					user.setLevelStatus(resultLevelStatus);
				}
				listOfUsers.add(user);
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
		return listOfUsers;
	}
}
