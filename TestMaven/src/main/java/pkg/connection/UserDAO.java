package pkg.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pkg.model.User;

public class UserDAO {

	static Connection conn;
	static PreparedStatement pst;
	static ResultSet rs;
	
	/* Try to get user name and password from database */
	public static User getUser(String username, String password) throws SQLException {	
		User user = null;		
		try {
			conn = ConnectionProvider.getCon();			
			pst = conn.prepareStatement("SELECT * FROM public.user WHERE username=? AND password=?");
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			if (rs.next())
				user = new User(username,password);
			
			rs.close();
			pst.close();
			
			} finally {
			if (conn != null) 
				conn.close();			
		}		
		return user;
	}	
}