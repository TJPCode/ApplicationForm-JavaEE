package pkg.connection;

import java.sql.*;
import java.util.ArrayList;
import pkg.model.Applicant;

public class PersonDAO {
	
	static Connection conn;
	static PreparedStatement pst;
	static ResultSet rs;
	
	/* Inserts person data into database. */
	public static int insertPerson(String firstname, String lastname, String gender, String application) {
		int status = 0;		
		try {
			conn = ConnectionProvider.getCon();
			pst = conn.prepareStatement("INSERT INTO public.person (firstname, lastname, gender, application) values (?,?,?,?)");
			pst.setString(1, firstname);
			pst.setString(2, lastname);
			pst.setString(3, gender);
			pst.setString(4, application);
			status = pst.executeUpdate();
			pst.close();
			conn.close();
		} 
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Error - Cannot execute query: " + ex);
		}
		return status;
	}
	
	/* Gets all applicants from database. */
	public static ArrayList<Applicant> getApplicants() throws SQLException {	
		ArrayList<Applicant> applicants = new ArrayList<Applicant>();		
		try {
			conn = ConnectionProvider.getCon();				
			pst = conn.prepareStatement("SELECT * FROM public.person ORDER BY key");
			rs = pst.executeQuery();
		
			while (rs.next()) {
				applicants.add(new Applicant(rs.getInt("key"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("isread")));				
			}	
			rs.close();
			pst.close();
			
			} finally {
			if (conn != null)
				conn.close();
		}		
		return applicants;
	}
	
	/* Searches applicants from database by first name, last name, full name or part of name. */
	public static ArrayList<Applicant> searchApplicant(String searchedName) throws SQLException {		
		ArrayList<Applicant> applicants = new ArrayList<Applicant>();
		String[] nameParts;
		String namePart1 = searchedName.toLowerCase();
		String namePart2 = "";;
		
		/* Check if searching with first name AND last name. */
		if (searchedName.contains(" ")) {
			nameParts = searchedName.split(" ");
			namePart1 = nameParts[0].toLowerCase();
			namePart2 = nameParts[1].toLowerCase();
		}
		
		try {
			conn = ConnectionProvider.getCon();			
			pst = conn.prepareStatement("SELECT * FROM public.person WHERE LOWER(firstname) LIKE ? OR LOWER(lastname) LIKE ? ORDER BY key");
			pst.setString(1, namePart1 + "%");
			pst.setString(2, namePart1 + "%");
			rs = pst.executeQuery();
			
			while (rs.next()) {
				/* If searching only with first name OR last name. */
				if (namePart1 != "" && namePart2 == "" ) {				
					applicants.add(new Applicant(rs.getInt("key"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("isread")));	
				}
				/* Searching with full name. */
				else {
					String fullname = namePart1 + namePart2;
					String fullnameDb = rs.getString("firstname").toLowerCase() + rs.getString("lastname").toLowerCase();
								
					if (fullname.equals(fullnameDb))
						applicants.add(new Applicant(rs.getInt("key"), rs.getString("firstname"), rs.getString("lastname"), rs.getInt("isread")));																					
				}
			}
			rs.close();
			pst.close();
			
		} finally {
			if (conn != null)
				conn.close();			
		}	
		return applicants;
	}		
		
	/* Updates selected isRead columns with 1 OR 0. */
	public static void updateIsread(Integer[] checkedKeys, int isReadValue) {
		String sqlQuery = "";
		try {
			conn = ConnectionProvider.getCon();			
			if (isReadValue == 1)
				sqlQuery = "UPDATE public.person SET isread=1 WHERE key=?";					
			if (isReadValue == 0)
				sqlQuery = "UPDATE public.person SET isread=0 WHERE key=?";
								
			pst = conn.prepareStatement(sqlQuery);
			for (int key: checkedKeys) {           
				pst.setInt(1, key);
				pst.executeUpdate();
		    }
			pst.close();
			conn.close();
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Error - Cannot execute query: " + ex);
		}	
	}
	
	/* Deletes selected applicant(s) from database. */
	public static void deleteApplicant(Integer[] checkedKeys) {
		try {
			conn = ConnectionProvider.getCon();																	
			pst = conn.prepareStatement("DELETE FROM public.person WHERE key=?");
			for (int key: checkedKeys) {           
				pst.setInt(1, key);
				pst.executeUpdate();
		    }
			pst.close();
			conn.close();
		} 
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Error - Cannot execute query: " + ex);
		}	
	}	
	
	/* Gets applicants application letter from database. */
	public static String[] getApplicationLetter(int key) throws SQLException {	
		String[] applicationParts = new String[3];		
		try {
			conn = ConnectionProvider.getCon();					
			pst = conn.prepareStatement("SELECT firstname, lastname, application FROM public.person WHERE key=?");
			pst.setInt(1, key);		
			rs = pst.executeQuery();
			
			while (rs.next()) {
				applicationParts[0] = rs.getString("firstname");
				applicationParts[1] = rs.getString("lastname");	
				applicationParts[2] = rs.getString("application");	
			}			
			rs.close();
			pst.close();
			
			} finally {
			if (conn != null)
				conn.close();
		}		
		return applicationParts;
	}
}