package servletsBarber.Publication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Publication;
import logic.PublicationBarber;

/**
 * Servlet implementation class EditPublicationServlet
 */
@WebServlet("/EditPublicationServlet")
public class EditPublicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPublicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("idPublication");
		int idPublication = Integer.parseInt(id);
		
		request.setAttribute("publication", PublicationBarber.getById(idPublication));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Admin/Publication/EditPublication.jsp");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Publication p = new Publication();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String text = request.getParameter("text");
	
		p.setPublicationId(id);
		p.setText(text);
		
		try {
			String destPage = "WEB-INF/Admin/Publications/PublicationsManagement.jsp";
			
			Boolean serviceUpdate = PublicationBarber.update(p);
		       
			request.setAttribute("publicationList", PublicationBarber.getAll());
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
