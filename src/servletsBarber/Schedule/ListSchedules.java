package servletsBarber.Schedule;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import entities.Schedule;
import logic.Schedules;

/**
 * Servlet implementation class ListSchedules
 */
@WebServlet("/ListSchedules")
public class ListSchedules extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListSchedules() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
	
		RequestDispatcher dispatcher;
		if (user != null) {
			LinkedList<Schedule> barberSchedules = Schedules.getAllByBarber(user.getUserId());
			Collections.sort(barberSchedules);
			request.setAttribute("schedulesList",barberSchedules);
			dispatcher = (Schedules.areSchedulesLoaded(user.getUserId())) ? 
					request.getRequestDispatcher("WEB-INF/Barber/Schedules/SchedulesList.jsp") : request.getRequestDispatcher("/CreateScheduleServlet");	
		}else {
			dispatcher	= request.getRequestDispatcher("WEB-INF/User/Signin.jsp");
		}
			
		dispatcher.forward(request, response);
	}

}
