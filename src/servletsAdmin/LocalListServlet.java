package servletsAdmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Local;
import entities.User;
import logic.Admin;
import logic.LocalAdmin;

/**
 * Servlet implementation class LocalListServlet
 */
@WebServlet("/LocalListServlet")
public class LocalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocalListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/User/Signin.jsp");
		if (user != null) {
			request.setAttribute("localsList", LocalAdmin.getAll());
			dispatcher = (LocalAdmin.areLocalsLoaded()) ? request.getRequestDispatcher("WEB-INF/Admin/Locals/LocalsManagement.jsp") : request.getRequestDispatcher("WEB-INF/Admin/Locals/CreateLocal.jsp");
		}	
	
		dispatcher.forward(request, response);
	}
}
