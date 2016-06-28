package pkg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pkg.connection.PersonDAO;

public class ApplicantsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	/* Marks applications as read or unread, deletes selected applicants and gets application letter. */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Get selected check boxes. */
		String[] checked = request.getParameterValues("readboxes");
		Integer[] checkedIds = {0};
		if (checked !=  null) {
			checkedIds = new Integer[checked.length];		
		    int i = 0;
		    
		    for(String str : checked) {
		    	checkedIds[i] = Integer.parseInt(str.trim());
		        i++;
		    }
		}

		try {
			/* Mark as read button. */
			String readBtn = request.getParameter("readbtn");
			if (readBtn != null) {
				int isReadValue = 1;
				PersonDAO.updateIsread(checkedIds, isReadValue);
			}
			/* Mark as unread button. */
			String unreadBtn = request.getParameter("unreadbtn");
			if (unreadBtn != null) {
				int isReadValue = 0;
				PersonDAO.updateIsread(checkedIds, isReadValue);
			}	
			/* Delete applicant button. */
			String deleteBtn = request.getParameter("deletebtn");
			if (deleteBtn != null) {
				PersonDAO.deleteApplicant(checkedIds);
			}	
			/* Get application letter button. */
			String applicationBtn = request.getParameter("applicationbtn");	
			if (applicationBtn !=  null) {
				int appKey = Integer.parseInt(applicationBtn);
				String[] text = PersonDAO.getApplicationLetter(appKey);
				request.setAttribute("applicationletter", text); 
			}	
	        request.getRequestDispatcher("applicants.jsp").forward(request, response);
        
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	/* When "Get all" button is pressed. Sets sessions searching attribute to null. Background search is not performed anymore. */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {						
		
		try {				
			request.getSession().setAttribute("searchedName", null);		
		} catch (Exception e) {
			e.printStackTrace();
		}	
		request.getRequestDispatcher("applicants.jsp").forward(request, response);
	}
}