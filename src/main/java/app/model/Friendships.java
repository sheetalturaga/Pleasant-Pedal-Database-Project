// Arjun Patel

package app.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Administrators table in your
 * MySQL instance. This is used to store {@link Administrators} into your MySQL instance and 
 * retrieve {@link Administrators} from MySQL instance.
 */
public class Friendships {

	public Integer RequestId;
	public String UserName;
	public String RequestUserName;
	public Friendships(Integer requestId, String userName, String requestUserName) {
		super();
		RequestId = requestId;
		UserName = userName;
		RequestUserName = requestUserName;
	}
	public Integer getRequestId() {
		return RequestId;
	}
	public void setRequestId(Integer requestId) {
		RequestId = requestId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getRequestUserName() {
		return RequestUserName;
	}
	public void setRequestUserName(String requestUserName) {
		RequestUserName = requestUserName;
	}
	
	
	

}