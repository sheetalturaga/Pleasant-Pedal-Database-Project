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
public class Friends {
	public String UserName;
	public String Friend;
	
	public Friends(String userName, String friend) {
		super();
		UserName = userName;
		Friend = friend;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getFriend() {
		return Friend;
	}

	public void setFriend(String friend) {
		Friend = friend;
	}
	
	
	

}