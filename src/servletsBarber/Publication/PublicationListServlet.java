package servletsBarber.Publication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import logic.Admin;
import logic.PublicationBarber;

/**
 * Servlet implementation class LocalListServlet
 */
@WebServlet("/PublicationListServlet")
public class PublicationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicationListServlet() {
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
			request.setAttribute("publicationsList", PublicationBarber.getAll());
			dispatcher = (PublicationBarber.arePublicationsLoaded()) ? request.getRequestDispatcher("WEB-INF/Admin/Publications/PublicationsManagement.jsp") : request.getRequestDispatcher("WEB-INF/Admin/Publications/CreatePublication.jsp");
		}	
	
		dispatcher.forward(request, response);
	}
}