package app.model;

import java.sql.Date;

public class Users extends Person{
	protected Date DateOfBirth;
	protected String Bio;
	protected String Address;
	protected levelStatus LevelStatus;
	
	public enum levelStatus {
		BEGINNER,
		INTERMEDIATE,
		ADVANCED
	}

	public Users(String userName, String password, String firstName, String lastName, String email, String phoneNumber,
			Date dateOfBirth, String bio, String address, levelStatus levelStatus) {
		super(userName, password, firstName, lastName, email, phoneNumber);
		DateOfBirth = dateOfBirth;
		Bio = bio;
		Address = address;
		LevelStatus = levelStatus;
	}
	
	public Users(String userName, String password, String firstName, String lastName, String email, String bio, String address, levelStatus levelStatus) {
		super(userName, password, firstName, lastName, email);
		Bio = bio;
		Address = address;
		LevelStatus = levelStatus;
	}
	
	// constructor for users who don't enter any personal information or use 
	// getters for only filling in certain information
	public Users(String userName, String password, String firstName, String lastName, String email, String phoneNumber) {
		super(userName, password, firstName, lastName, email);
		DateOfBirth = null;
		Bio = null;
		Address = null;
		LevelStatus = null;
	}

	
//	public Users(String userName, String firstName, String lastName, String email, 
//			String bio, String address, levelStatus levelStatus) {
//		super(userName, firstName, lastName, email);
//		Bio = bio;
//		Address = address;
//		LevelStatus = levelStatus;
//	}
	
	public Users(String userName, String password, String firstName, String lastName, String email, String phoneNumber,
			String bio, String address, levelStatus levelStatus) {
		super(userName, password, firstName, lastName, email, phoneNumber);
		
		Bio = bio;
		Address = address;
		LevelStatus = levelStatus;
	}
	
	
	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(Date dob) {
		DateOfBirth = dob;
	}

	public String getBio() {
		return Bio;
	}

	public void setBio(String bio) {
		Bio = bio;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public levelStatus getLevelStatus() {
		return LevelStatus;
	}

	public void setLevelStatus(levelStatus levelStatus) {
		LevelStatus = levelStatus;
	}

	
	
	
}