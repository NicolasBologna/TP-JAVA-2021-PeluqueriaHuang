package servletsAdmin.Service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.*;
import entities.Service;
import logic.LocalAdmin;
import logic.ServiceAdmin;

/**
 * Servlet implementation class CreateServiceServlet
 */
@WebServlet("/CreateServiceServlet")
public class CreateServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Admin/Services/CreateService.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Service newService = new Service();
		
		
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));
		String time = request.getParameter("duration");
		Time duration = java.sql.Time.valueOf(time);
		
		newService.setName(name);
		newService.setDescription(description);
		newService.setPrice(price);
		newService.setDuration(duration);
		
		try {
			String destPage = "WEB-INF/Admin/Services/CreateService.jsp";
			
			int idNewService = ServiceAdmin.add(newService);
			if(idNewService != -1) {
				String message = "El servicio se agregó correctamente";
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


