package pkg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pkg.connection.UserDAO;
import pkg.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/* Tries to log in as an administrator and starts session if success. Sends information to login.jsp if failed to login or if connection problems.*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			User user = UserDAO.getUser(username, password);
			
			if (user != null) {
				request.getSession().setAttribute("logged", user);
				response.sendRedirect("adminhome.jsp");
			}
			else {   
				boolean loginFail = true;
				request.setAttribute("loginfail", loginFail);  
		        request.getRequestDispatcher("login.jsp").forward(request, response);
			}				        
		} catch (Exception e) { 
			request.setAttribute("connectionfail", true);
	        request.getRequestDispatcher("login.jsp").forward(request, response);
			e.printStackTrace();
		}				
	}
	
	/* Invalidates session when administrator logs off and redirects to index.jsp. */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		 request.getSession().invalidate();
		 response.sendRedirect("index.jsp");
	}
}