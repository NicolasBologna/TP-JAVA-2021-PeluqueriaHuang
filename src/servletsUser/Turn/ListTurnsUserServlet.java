package servletsUser.Turn;

import java.io.IOException;
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

@WebServlet("/ListTurnsUserServlet")
public class ListTurnsUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ListTurnsUserServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		User user = (User)request.getSession().getAttribute("user");
		
		RequestDispatcher dispatcher;
		if (user != null) {
			
			LinkedList<Turn> userTurns = Turns.getByUserId(user.getUserId());
			
			request.setAttribute("turnsList",userTurns);
			dispatcher = request.getRequestDispatcher("WEB-INF/User/Turn/TurnsList.jsp");	
		}else {
			dispatcher	= request.getRequestDispatcher("WEB-INF/User/Signin.jsp");
		}
			
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
