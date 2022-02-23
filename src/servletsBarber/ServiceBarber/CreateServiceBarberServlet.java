package servletsBarber.ServiceBarber;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Publication;
import entities.ServiceBarber;
import entities.User;
import logic.Admin;
import logic.PublicationBarber;
import logic.ServicesBarber;

/**
 * Servlet implementation class CreateServiceBarberServlet
 */
@WebServlet("/CreateServiceBarberServlet")
public class CreateServiceBarberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServiceBarberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Barber/ServiceBarber/CreateServiceBarber.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServiceBarber newServiceBarber = new ServiceBarber();
		User user = (User)request.getSession().getAttribute("user");
		int serviceId = Integer.parseInt(request.getParameter("service_id"));
		
		newServiceBarber.setBarberId(user.getUserId());
		newServiceBarber.setServiceId(serviceId);
		
		
		try {
			String destPage = "WEB-INF/Barber/ServiceBarber/ServiceBarberManagement.jsp";
			int idNewServiceBarber = ServicesBarber.add(newServiceBarber);
			request.setAttribute("serviceBarberList", ServicesBarber.getByBarberId(user.getUserId()));			
			if(idNewServiceBarber != -1) {
				String message = "El Servicio se agregó correctamente";
				request.setAttribute("successMessage", message);
			}
			else {
				String message = "Ocurrió un error en el registro";
				request.setAttribute("errorMessage", message);
			}
 
 
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }				
	}
}
