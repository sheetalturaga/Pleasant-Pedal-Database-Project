package app.model;

import java.sql.Timestamp;

public class Admin extends Person{
	protected Timestamp LastLogin;

	public Admin(String userName, String password, String firstName, String lastName, String email, String phoneNumber,
			Timestamp lastLogin) {
		super(userName, password, firstName, lastName, email, phoneNumber);
		LastLogin = lastLogin;
	}
	
	
	public Admin(String userName, String password, String firstName, String lastName, String email, String phoneNumber) {
		super(userName, password, firstName, lastName, email, phoneNumber);
		LastLogin = new Timestamp(System.currentTimeMillis());
	}


	public Timestamp getLastLogin() {
		return LastLogin;
	}


	public void setLastLogin(Timestamp lastLogin) {
		LastLogin = lastLogin;
	}
	
}