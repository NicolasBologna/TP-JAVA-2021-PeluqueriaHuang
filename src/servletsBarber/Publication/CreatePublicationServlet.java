package servletsBarber.Publication;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import entities.Publication;
import entities.User;
import logic.Admin;
import logic.PublicationBarber;

/**
 * Servlet implementation class CreatePublicationServlet
 */
@WebServlet("/CreatePublicationServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class CreatePublicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePublicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Barber/Publications/CreatePublication.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Publication newPublication = new Publication();
		User user = (User)request.getSession().getAttribute("user");
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		
		InputStream inputStream = null; // input stream of the upload file
		
		// obtains the upload file part in this multipart request
        Part filePart = request.getPart("image");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
		
		newPublication.setBarberId(user.getUserId());
		newPublication.setTitle(title);
		newPublication.setText(text);
		newPublication.setImage(inputStream);
		
		
		try {
			String destPage = "WEB-INF/Barber/Publications/PublicationsManagement.jsp";
			int idNewPublication = PublicationBarber.add(newPublication);
			request.setAttribute("publicationsList", PublicationBarber.getByBarberId(user.getUserId()));			
			if(idNewPublication != -1) {
				String message = "La publicaci�n se agreg� correctamente";
				request.setAttribute("successMessage", message);
			}
			else {
				String message = "Ocurri� un error en el registro";
				request.setAttribute("errorMessage", message);
			}
 
 
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
        } catch (Exception ex) {
            throw new ServletException(ex);
        }				
	}
}
