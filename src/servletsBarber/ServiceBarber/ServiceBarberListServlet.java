package servletsBarber.ServiceBarber;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.ServiceBarber;
import entities.User;
import logic.Admin;
import logic.PublicationBarber;
import logic.ServiceAdmin;
import logic.ServicesBarber;

/**
 * Servlet implementation class ServiceBarberListServlet
 */
@WebServlet("/ServiceBarberListServlet")
public class ServiceBarberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceBarberListServlet() {
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
			request.setAttribute("serviceList", ServiceAdmin.getAll());
			request.setAttribute("serviceBarberList", ServicesBarber.getServicesByBarberId(user.getUserId()));
			dispatcher = request.getRequestDispatcher("WEB-INF/Barber/ServiceBarber/ServiceBarberManagement.jsp");
		}	
	
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] services = request.getParameterValues("services");
		User user = (User)request.getSession().getAttribute("user");
		ServicesBarber.deleteAllByBarber(user.getUserId());
		ServicesBarber.addAll(services, user.getUserId());
	
		String destPage = "WEB-INF/Barber/ServiceBarber/ServiceBarberManagement.jsp";
		request.setAttribute("serviceList", ServiceAdmin.getAll());
		request.setAttribute("serviceBarberList", ServicesBarber.getServicesByBarberId(user.getUserId()));
		request.setAttribute("successMessage", "Se actualizaron los servicios");	
 
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
     
	}
}