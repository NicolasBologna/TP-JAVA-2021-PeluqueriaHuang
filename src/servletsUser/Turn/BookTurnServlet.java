package servletsUser.Turn;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.LocalAdmin;
import logic.ServiceAdmin;


/**
 * Servlet implementation class BookTurnServlet
 */
@WebServlet("/BookTurnServlet")
public class BookTurnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BookTurnServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("localsList", LocalAdmin.getAll());
		request.setAttribute("servicesList", ServiceAdmin.getAll());
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/User/Turn/BookTurn.jsp");
        dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
