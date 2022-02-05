package servletsAdmin.Local;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Local;
import logic.LocalAdmin;

/**
 * Servlet implementation class EditLocalServlet
 */
@WebServlet("/EditLocalServlet")
public class EditLocalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditLocalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		int idLocal = Integer.parseInt(id);
		
		request.setAttribute("local", LocalAdmin.getById(idLocal));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Admin/Locals/EditLocal.jsp");
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Local l = new Local();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String coordenates = request.getParameter("coordenates");
	
		
		String address = request.getParameter("address");
		String isEnable = request.getParameter("is_enable");
		
		l.setLocalId(id);
		l.setName(name);
		l.setCoordenates(coordenates);
		l.setAddress(address);
		l.setIsEnable(isEnable.equals("on") ? true : false);
		
		try {
			String destPage = "WEB-INF/Admin/Locals/LocalsManagement.jsp";
			
			Boolean localUpdate = LocalAdmin.update(l);
		
			request.setAttribute("localsList", LocalAdmin.getAll());
        	if (localUpdate== false) {
        		
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
