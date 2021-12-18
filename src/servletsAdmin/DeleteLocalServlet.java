package servletsAdmin;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Local local = new Local();
		local.setLocalId(Integer.parseInt(request.getParameter("id")));
		String message = LocalAdmin.delete(local)?"Se elimino la peluquería":"No se elimino la peluquería";
		
		request.setAttribute("deletionMessage", message);
		
		request.setAttribute("ll", LocalAdmin.getAll());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Admin/User/UserManagement.jsp");
		
        dispatcher.forward(request, response);
	}

}
