package pkg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pkg.connection.PersonDAO;

public class AppFormServlet extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
         
	/*Gets data from application form and inserts it into database. Sends confirmation about update to applicationform.jsp. */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String gender = request.getParameter("gender");					
		String applicationTxt = request.getParameter("application");
				
		try {
			int status = PersonDAO.insertPerson(firstName, lastName, gender, applicationTxt);
			request.setAttribute("result", status);        
	        request.getRequestDispatcher("applicationform.jsp").forward(request, response);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}											
	}
}