package servletsUser.PublicationComments;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dtos.PublicationDetailsDto;
import entities.Comment;
import entities.ServiceBarber;
import entities.User;
import logic.Admin;
import logic.Comments;
import logic.PublicationBarber;
import logic.ServicesBarber;
/**
 * Servlet implementation class PublicationCommentsServlet
 */
@WebServlet("/PublicationCommentsServlet")
public class PublicationCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicationCommentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int publicationId = Integer.parseInt(request.getParameter("id"));
		User user = (User)request.getSession().getAttribute("user");
		
		PublicationDetailsDto dto = PublicationBarber.getByIdWithComments(publicationId);
		
		request.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/User/Signin.jsp");
		if (user != null) {
			dispatcher = request.getRequestDispatcher("WEB-INF/User/PublicationComments/ViewPublicationComments.jsp");	       
		}	
		dispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		String comment = request.getParameter("comment");
		int publicationId = Integer.parseInt(request.getParameter("publicationId"));
		
		
		Comment newComment = new Comment();
		
		newComment.setCreator(user);
		newComment.setPublicationId(publicationId);
		newComment.setText(comment);
		
		try {
			String destPage = "WEB-INF/User/PublicationComments/ViewPublicationComments.jsp";
			int idNewServiceBarber = Comments.add(newComment);
			PublicationDetailsDto dto = PublicationBarber.getByIdWithComments(publicationId);
			request.setAttribute("dto", dto);
			if(idNewServiceBarber != -1) {
				String message = "El comentario se agregó correctamente";
				request.setAttribute("successMessage", message);
			}
			else {
				String message = "Ocurrió un error al añadir el comentario";
				request.setAttribute("errorMessage", message);
			}
 
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }		
	}

}
