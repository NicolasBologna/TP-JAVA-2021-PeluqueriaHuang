package servletsAdmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.Admin;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String idUser = request.getParameter("id");
		
		String message = Admin.switchUserStatus(Integer.parseInt(idUser))?"Se elimino el usuario":"No se elimino el usuario";
		
		request.setAttribute("deletionMessage", message);
		
		request.setAttribute("lp", Admin.getAllUsers());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Admin/User/UserManagement.jsp");
		
        dispatcher.forward(request, response);
        
	}
}
