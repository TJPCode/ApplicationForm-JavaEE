package pkg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	/* When search button is pressed. Sets search value to session attribute that allows also background search after updating database. */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String searchedName = request.getParameter("searchname");	
		
		try {
	        request.getSession().setAttribute("searchedName", searchedName);
	        request.getRequestDispatcher("applicants.jsp").forward(request, response);
        
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
}