package app.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.*;



public class PersonDao {
	protected ConnectionManager connectionManager;
	private static PersonDao instance = null;

	protected PersonDao() {
		connectionManager = new ConnectionManager();
	}

	public static PersonDao getInstance() {
		if (instance == null) {
			instance = new PersonDao();
		}
		return instance;
	}


	public Person create(Person person) throws SQLException {
		String insertPerson = "INSERT INTO Person(UserName,Password,FirstName,LastName,Email,PhoneNumber) "
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson);
			insertStmt.setString(1, person.getUserName());
			insertStmt.setString(2, person.getPassword());
			insertStmt.setString(3, person.getFirstName());
			insertStmt.setString(4, person.getLastName());
			insertStmt.setString(5, person.getEmail());
			insertStmt.setString(6, person.getPhoneNumber());
			insertStmt.executeUpdate();
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Update the password of the Person instance.
	 * This runs a UPDATE statement.
	 */
	public Person updatePassword(Person person, String newPassword) throws SQLException {
		String updatePerson = "UPDATE Person SET Password=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newPassword);
			updateStmt.setString(2, person.getUserName());
			updateStmt.executeUpdate();
			person.setLastName(newPassword);
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}


	/**
	 * Update the phone number of the Person instance.
	 * This runs a UPDATE statement.
	 */
	public Person updatePhoneNumber(Person person, String newPhone) throws SQLException {
		String updatePerson = "UPDATE Person SET PhoneNumber=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newPhone);
			updateStmt.setString(2, person.getUserName());
			updateStmt.executeUpdate();
			person.setPhoneNumber(newPhone);
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Update the email of the Person instance.
	 * This runs a UPDATE statement.
	 */
	public Person updateEmail(Person person, String newEmail) throws SQLException {
		String updatePerson = "UPDATE Person SET Email=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newEmail);
			updateStmt.setString(2, person.getUserName());
			updateStmt.executeUpdate();
			person.setEmail(newEmail);
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the Person instance.
	 * This runs a DELETE statement.
	 */
	public Person delete(Person person) throws SQLException {
		String deletePerson = "DELETE FROM Person WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setString(1, person.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + person.getUserName());
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	/**
	 * Get the Person record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Person instance.
	 */
	public Person getPersonFromUserName(String userName) throws SQLException {
		String selectPerson = "SELECT * FROM Person WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("PhoneNumber");
				Person person = new Person(resultUserName, password, firstName, lastName, email, phone);
				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

	/**
	 * Get the matching Person records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Person.
	 */
	public List<Person> getPersonsFromFirstName(String firstName) throws SQLException {
		List<Person> person = new ArrayList<Person>();
		String selectPersons =
				"SELECT * FROM Person WHERE FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPersons);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while (results.next()) {
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phone = results.getString("PhoneNumber");
				Person p = new Person(userName, password, resultFirstName, lastName, email, phone);
				person.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return person;
	}

	public boolean validate(Person person) throws ClassNotFoundException, SQLException {
		String selectQuery = "select * from person where username = ? and password = ?;";
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;
		boolean status = false;
		try {

			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setString(1, person.getUserName());
			selectStatement.setString(2, person.getPassword());
			ResultSet rs = selectStatement.executeQuery();

			if (rs.next()) {
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStatement != null) {
				selectStatement.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return status;
	}
}
