package servletsAdmin.Local;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Local;
import logic.LocalAdmin;

/**
 * Servlet implementation class LocalServlet
 */
@WebServlet("/CreateLocalServlet")
public class CreateLocalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateLocalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Admin/Locals/CreateLocal.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Local newLocal = new Local();
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String coordenates = request.getParameter("coordenates");
		
		newLocal.setName(name);
		newLocal.setAddress(address);
		newLocal.setCoordenates(coordenates);
		
		try {
			String destPage = "WEB-INF/Admin/Locals/CreateLocal.jsp";
        	if (LocalAdmin.checkAddressAvailability(newLocal.getAddress())) {
        		String message = "La dirección ya está en uso";
                request.setAttribute("errorMessage", message);
        	}else {
            	int idNewLocal = LocalAdmin.add(newLocal);
            	if (idNewLocal != -1) {
            		String message = "El local se agregó correctamente";
            		request.setAttribute("successMessage", message);
            	} else {
            		String message = "Hubo un error en el registro.";
                    request.setAttribute("errorMessage", message);
            	}
        	}
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }				
	}
	}


