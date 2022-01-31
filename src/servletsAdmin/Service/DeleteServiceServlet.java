package servletsAdmin.Service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Service;
import logic.ServiceAdmin;


/**
 * Servlet implementation class DeleteServiceServlet
 */
@WebServlet("/DeleteServiceServlet")
public class DeleteServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

		Service service = new Service();
		Integer id = Integer.parseInt(request.getParameter("idService"));
		service.setServiceId(id);
		
		String message = ServiceAdmin.delete(service)?"Servicio eliminado":"No se elimino el servicio";
		request.setAttribute("deletionMessage", message);
		
		request.setAttribute("servicesList", ServiceAdmin.getAll());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Admin/Services/ServicesManagement.jsp");
		
        dispatcher.forward(request, response);}
		
		catch(Exception ex) {
            throw new ServletException(ex);
	}}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
