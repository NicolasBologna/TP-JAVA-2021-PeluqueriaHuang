package servletsAdmin.Local;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Local;
import logic.Admin;
import logic.LocalAdmin;

/**
 * Servlet implementation class DeleteLocalServlet
 */
@WebServlet("/DeleteLocalServlet")
public class DeleteLocalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLocalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String message = LocalAdmin.switchLocalStatus(Integer.parseInt(id))?"Se elimino la peluquer�a":"No se elimino la peluquer�a";
		
		request.setAttribute("deletionMessage", message);
		
		request.setAttribute("localsList", LocalAdmin.getAll());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Admin/Locals/LocalsManagement.jsp");
		
        dispatcher.forward(request, response);
	}

	


}
