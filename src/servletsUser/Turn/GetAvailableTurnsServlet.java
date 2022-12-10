package servletsUser.Turn;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Schedule;
import logic.Schedules;
import logic.ServicesBarber;
import logic.Turns;

@WebServlet("/GetAvailableTurnsServlet")
public class GetAvailableTurnsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetAvailableTurnsServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int barberId = Integer.parseInt(request.getParameter("idBarber"));
		int idLocal = Integer.parseInt(request.getParameter("idLocal"));
		
		String[] servicesId = request.getParameterValues("services");
		String turnDate = request.getParameter("turn-date");

		Schedule schedule = Schedules.getByLocalBarber(idLocal,barberId);
		LocalTime servicesDuration = ServicesBarber.getTotalDuration(servicesId);
		LinkedList<String> hoursList = Turns.getHoursAvailable(barberId,turnDate, idLocal, servicesDuration,schedule);
		
		request.setAttribute("turn-date", turnDate);
		request.setAttribute("hoursList",hoursList);
		request.setAttribute("servicesId", servicesId);
		request.setAttribute("idLocal", idLocal);
		request.setAttribute("barberId", barberId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/User/Turn/ConfirmTurn.jsp");
        dispatcher.forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
