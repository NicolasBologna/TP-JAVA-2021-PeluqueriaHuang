package servletsBarber.Turn;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Turn;
import entities.User;
import logic.Turns;

/**
 * Servlet implementation class ListTurnsServlet
 */
@WebServlet("/ListTurnsServlet")
public class ListTurnsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ListTurnsServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User)request.getSession().getAttribute("user");
		
		RequestDispatcher dispatcher;
		if (user != null) {
			LinkedList<Turn> barberTurns = Turns.getByBarberId(user.getUserId());
			
			request.setAttribute("turnsList",barberTurns);
			dispatcher = request.getRequestDispatcher("WEB-INF/Barber/Turns/TurnsList.jsp");	
		}else {
			dispatcher	= request.getRequestDispatcher("WEB-INF/User/Signin.jsp");
		}
			
		dispatcher.forward(request, response);
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
