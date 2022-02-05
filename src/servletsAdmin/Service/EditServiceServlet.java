package servletsAdmin.Service;

import java.io.IOException;
import java.sql.Time;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Service;

import logic.ServiceAdmin;


/**
 * Servlet implementation class EditServiceServlet
 */
@WebServlet("/EditServiceServlet")
public class EditServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("idService");
		int idService = Integer.parseInt(id);
		
		request.setAttribute("service", ServiceAdmin.getServiceById(idService));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Admin/Services/EditService.jsp");
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Service s = new Service();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String descrip = request.getParameter("description");
		Float price = Float.parseFloat(request.getParameter("price"));
		
		String time = request.getParameter("duration");
		Time duration = java.sql.Time.valueOf(time);
		s.setServiceId(id);
		s.setName(name);
		s.setDescription(descrip);
		s.setPrice(price);
		s.setDuration(duration);
		
		
		try {
			String destPage = "WEB-INF/Admin/Services/ServicesManagement.jsp";
			
			Boolean serviceUpdate = ServiceAdmin.update(s);
		       
			request.setAttribute("servicesList", ServiceAdmin.getAll());
        	if (serviceUpdate== false) {
        		
        		String message = "Hubo un error en la actualizacion.";
                request.setAttribute("errorMessage", message);
        	}
       
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }				
	}

}