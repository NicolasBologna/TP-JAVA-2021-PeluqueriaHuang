package servletsAdmin.Service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import logic.LocalAdmin;
import logic.ServiceAdmin;

/**
 * Servlet implementation class ServiceListServlet
 */
@WebServlet("/ServiceListServlet")
public class ServiceListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceListServlet() {
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
			request.setAttribute("servicesList", ServiceAdmin.getAll());
			dispatcher = (ServiceAdmin.areServicesLoaded()) ? request.getRequestDispatcher("WEB-INF/Admin/Services/ServicesManagement.jsp") : request.getRequestDispatcher("WEB-INF/Admin/Services/CreateService.jsp");
		}	
	
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
