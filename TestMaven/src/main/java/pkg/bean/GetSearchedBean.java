package pkg.bean;

import java.util.ArrayList;
import pkg.connection.PersonDAO;
import pkg.model.Applicant;

public class GetSearchedBean{
	
	private ArrayList<Applicant> applicants;
	private String searchValue = null;

    public void getFromDatabase() {
    	 try {		    		 	
    		 	applicants = PersonDAO.searchApplicant(searchValue);	    		 	
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
     
    public ArrayList<Applicant> getApplicants() {
    	getFromDatabase();
    	return applicants;
    }
     
    public void setSearchValue(String value) {
    	this.searchValue = value;
    }     
}