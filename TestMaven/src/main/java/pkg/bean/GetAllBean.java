package pkg.bean;

import java.util.ArrayList;
import pkg.connection.PersonDAO;
import pkg.model.Applicant;

public class GetAllBean{
	
	private ArrayList<Applicant> applicants;
	
    public void getFromDatabase() {
    	 try {	
    		 	applicants = PersonDAO.getApplicants();
    		 	   		 	
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
     
    public ArrayList<Applicant> getApplicants() {
    	getFromDatabase();
    	return applicants;
    }   
}