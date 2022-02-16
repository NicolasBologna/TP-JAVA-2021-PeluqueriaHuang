package servletsBarber.Publication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.PublicationBarber;

/**
 * Servlet implementation class DeletePublicationServlet
 */
@WebServlet("/DeletePublicationServlet")
public class DeletePublicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePublicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			Integer id = Integer.parseInt(request.getParameter("idPublication"));
		
			String message = PublicationBarber.delete(id)?"Publicación eliminado":"No se eliminó la publicación";
			request.setAttribute("deletionMessage", message);
			
			request.setAttribute("publicationsList", PublicationBarber.getAll());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Barber/Publications/PublicationsManagement.jsp");
			
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
